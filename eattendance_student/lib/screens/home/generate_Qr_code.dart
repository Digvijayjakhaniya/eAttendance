import '../../models/attendance_data.dart';
import '../../models/mapping_model.dart';
import '../../repository/session/session_repository.dart';
import '../../utility/utils.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:simple_barcode_scanner/simple_barcode_scanner.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  SessionRepository sessinRepo = Get.put(SessionRepository());
  bool isAttendance = false;
  bool isButtonDisable = false;

  late bool _showBuffer;
  String result = '';
  Map<String, String> queryParameters = {};
  late OverlayEntry _overlayEntry;
  String? duration;
  String? createdAt;
  DateTime now1 = DateTime.now();

  bool overlayShown = false; // Track if overlay has been shown

  @override
  void initState() {
    super.initState();
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
    setState(() {
      _overlayEntry.remove();
      overlayShown = false; // Reset the flag when overlay is removed
      if (isAttendance) {
        showSnackkBar(
            icon: const Icon(Icons.done),
            message: 'Attendance Filled SuccessFully');
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
                      Uri uri = Uri.parse(res);

                      Map<String, String> queryParameters = uri.queryParameters;
                      AttendanceData data = AttendanceData(
                          course: queryParameters['course']!.toString(),
                          subject: queryParameters['subject']!.toString(),
                          sem: queryParameters['sem']!.toString(),
                          dividion: queryParameters['division']!.toString(),
                          faculty: queryParameters['fid']!.toString(),
                          duration: queryParameters['duration']!.toString(),
                          createdAt: queryParameters['createdAt']!.toString());

                      // Your action when the button is pressed
                      Mapping? map = await sessinRepo.isSessionOpen();

                      if (map != null) {
                        showSnackkBar(
                            icon: const Icon(Icons.done),
                            message: 'Session is Active Filling Attendance');
                        isAttendance =
                            await sessinRepo.fillAttendance(map, data);
                      } else {
                        showSnackkBar(
                          icon: const Icon(Icons.not_interested),
                          message: 'Opps! You Are Out Of Time',
                        );
                      }
                      showOverlay(); // Show loading overlay if not shown before
                    } else {
                      showSnackkBar(
                        icon: const Icon(Icons.not_interested),
                        message: 'Invalid Qr Code',
                      );
                    }
                  },
                  child: const Text('Open Scanner'),
                ),
              ],
            ),
          ),
          _showBuffer
              ? _overlayEntry as Widget
              : const SizedBox(), // Use ternary operator
        ],
      ),
    );
  }

  void showOverlay() {
    Overlay.of(context).insert(_overlayEntry);
    Future.delayed(Duration(minutes: int.tryParse(duration ?? '0') ?? 0), () {
      removeOverlay();
    });

    overlayShown = true;
  }
}
