import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:simple_barcode_scanner/simple_barcode_scanner.dart';

import '../../models/attendance_data.dart';
import '../../models/mapping_model.dart';
import '../../repository/session/session_repository.dart';
import '../../utility/utils.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> with WidgetsBindingObserver {
  final SessionRepository sessionRepo = Get.put(SessionRepository());
  bool isAttendance = false;
  bool isButtonDisable = false;
  int ispause = 0;
  late bool _showBuffer;
  String result = '';
  Map<String, String> queryParameters = {};
  late OverlayEntry _overlayEntry;
  String? duration;
  String? createdAt;

  bool overlayShown = false;

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    switch (state) {
      case AppLifecycleState.resumed:
        log("app in resumed");
        break;
      case AppLifecycleState.inactive:
        log("app in inactive");
        break;
      case AppLifecycleState.paused:
        log("app in paused");
        ispause = ispause + 1;
        break;
      case AppLifecycleState.detached:
        log("app in detached");
        break;
      case AppLifecycleState.hidden:
        break;
    }
  }

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _showBuffer = false;
    _overlayEntry = createOverlayEntry();
  }

  OverlayEntry createOverlayEntry() {
    return OverlayEntry(
      builder: (context) => Scaffold(
        body: Container(
          width: MediaQuery.of(context).size.width,
          height: MediaQuery.of(context).size.height,
          color: Colors.white,
          child: const Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                CircularProgressIndicator(),
                Text(
                  'Please wait while we mark your attendance.',
                  style: TextStyle(fontSize: 14),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void removeOverlay() {
    WidgetsBinding.instance.removeObserver(this);
    setState(() {
      _overlayEntry.remove();
      overlayShown = false;
      if (isAttendance) {
        showSnackkBar(
          icon: const Icon(Icons.done),
          message: 'Attendance Filled Successfully',
        );
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                ElevatedButton(
                  onPressed: () async {
                    var res = await Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => const SimpleBarcodeScannerPage(),
                      ),
                    );

                    setState(() {
                      if (res is String) {
                        result = res;
                        Uri uri = Uri.parse(result);
                        queryParameters = uri.queryParameters;

                        duration = queryParameters['duration'];
                        createdAt = queryParameters['createdAt'];
                      }
                    });

                    if (!overlayShown &&
                        duration != null &&
                        duration!.isNotEmpty) {
                      showOverlay(res);
                    } else {
                      showSnackkBar(
                        icon: const Icon(Icons.not_interested),
                        message: 'Invalid QR Code',
                      );
                    }
                  },
                  child: const Text('Open Scanner'),
                ),
              ],
            ),
          ),
          _showBuffer ? _overlayEntry as Widget : const SizedBox(),
        ],
      ),
    );
  }

  void showOverlay(String res) async {
    DateTime currentDate = DateTime.now();
    DateTime createdAtTime = DateTime(
      currentDate.year,
      currentDate.month,
      currentDate.day,
      int.parse(createdAt!.split(":")[0]),
      int.parse(createdAt!.split(":")[1]),
      int.parse(createdAt!.split(":")[2]),
    );

    int durationInMinutes = int.tryParse(duration ?? '0') ?? 0;
    DateTime endTime = createdAtTime.add(Duration(minutes: durationInMinutes));

    Overlay.of(context).insert(_overlayEntry);

    Duration difference = currentDate.difference(endTime).abs();
    int remainingSeconds = difference.inSeconds;

    Future.delayed(Duration(seconds: remainingSeconds), () {
      removeOverlay();
    });

    Mapping? map = await sessionRepo.isSessionOpen();

    if (map != null) {
      showSnackkBar(
        icon: const Icon(Icons.done),
        message: 'Session is Active Filling Attendance',
      );

      log(ispause.toString());
      log('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');

      if (remainingSeconds > 30) {
        Future.delayed(Duration(seconds: remainingSeconds - 40), () async {
          if (ispause > 1) {
            showSnackkBar(
              icon: const Icon(Icons.not_interested),
              message:
                  'Could not mark attendance successfully because you have switched the application ',
            );
            log(ispause.toString());
            log('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
            log('Attendance is not filled ');
            ispause = 0;
          } else {
            isAttendance =
                await sessionRepo.fillAttendance(map, getAttendanceData(res));
            ispause = 0;
          }
        });
      } else {
        if (ispause > 1) {
          showSnackkBar(
            icon: const Icon(Icons.not_interested),
            message:
                'Could not mark attendance successfully because you have switched the application',
          );
        } else {
          isAttendance =
              await sessionRepo.fillAttendance(map, getAttendanceData(res));
          ispause = 0;
        }
      }
    } else {
      showSnackkBar(
        icon: const Icon(Icons.not_interested),
        message: 'Oops! You Are Out Of Time',
      );
    }

    log(queryParameters.toString());
    overlayShown = true;
  }

  AttendanceData getAttendanceData(String res) {
    Uri uri = Uri.parse(res);
    return AttendanceData(
      course: uri.queryParameters['course']!,
      subject: uri.queryParameters['subject']!,
      sem: uri.queryParameters['sem']!,
      dividion: uri.queryParameters['division']!,
      faculty: uri.queryParameters['fid']!,
      duration: uri.queryParameters['duration']!,
      createdAt: uri.queryParameters['createdAt']!,
    );
  }
}
