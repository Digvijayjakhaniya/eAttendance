import '../../models/mapping_model.dart';
import '../../repository/session/session_repository.dart';
import '../../utility/utils.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:provider/provider.dart';

class Home extends StatefulWidget {
  const Home({super.key});

  @override
  State<Home> createState() => _HomeState();
}

class ButtonState extends ChangeNotifier {
  bool _isButtonEnabled = true;

  bool get isButtonEnabled => _isButtonEnabled;

  void disableButton() {
    _isButtonEnabled = false;
    notifyListeners();
  }
}

class _HomeState extends State<Home> {
  bool isButtonDisable = false;
  SessionRepository sessinRepo = Get.put(SessionRepository());
  bool isAttendance = false;
  @override
  void initState() {
    super.initState();
  }

  void disableButton() {
    setState(() {
      isButtonDisable = true;
    });
  }

  @override
  Widget build(BuildContext context) {
    final buttonState = Provider.of<ButtonState>(context);
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          // const SingleChildScrollView(
          //   child: Padding(
          //     padding: EdgeInsets.all(16.0),
          //     // child: TextField(
          //     //   decoration: InputDecoration(labelText: "Enter the OTP"),
          //     // ),
          //   ),
          // ),
          Padding(
            padding: const EdgeInsets.only(left: 115, bottom: 20),
            child: ElevatedButton(
              onPressed: buttonState.isButtonEnabled
                  ? () async {
                      // Your action when the button is pressed
                      Mapping? map = await sessinRepo.isSessionOpen();
                      if (map != null) {
                        showSnackkBar(
                            icon: const Icon(Icons.done),
                            message: 'Session is Active Filling Attendance');
                        isAttendance = await sessinRepo.fillAttendance(map);
                        if (isAttendance) {
                          showSnackkBar(
                              icon: const Icon(Icons.done),
                              message: 'Attendance Filled SuccessFully');
                        }
                        buttonState.disableButton();
                      } else {
                        showSnackkBar(
                          icon: const Icon(Icons.not_interested),
                          message:
                              'Session Is Not Active Please Try again When Session is Active',
                        );
                      }
                    }
                  : null,
              child: const Text('Mark Attendance'),
            ),
          ),

          //create the second OTP module
        ],
      ),
    );
  }
}
