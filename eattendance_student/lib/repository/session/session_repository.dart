import 'dart:convert';
import 'dart:developer';

import 'package:eattendance_student/models/token_manager.dart';

import '../auth/auth_repository.dart';
import '../../models/mapping_model.dart';
import '../../utility/constants.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class SessionRepository {
  static SessionRepository get instance => Get.find();
  final AuthenticationRepository authRepo = Get.find();

  Future<Mapping?> isSessionOpen() async {
    try {
      final response = await http.get(
          Uri.parse(
              "$apiUrl/session/isSession/${authRepo.student.value!.studentId}"),
          headers: createAuthorizationHeaders(await TokenManager.getToken()));
      if (response.statusCode == 200) {
        final json = jsonDecode(response.body);
        log(response.body.toString());
        return Mapping.fromJson(json);
      } else {
        return null;
      }
    } catch (_) {
      return null;
    }
  }

  Future<bool> fillAttendance(Mapping map) async {
    final response = await http.get(
        Uri.parse(
            "$apiUrl/session/fillAttendance/${map.mapId}/${authRepo.student.value!.studentId}"),
        headers: createAuthorizationHeaders(await TokenManager.getToken()));
    log(response.body.toString());
    if (response.statusCode == 200) {
      log(response.body.toString());
      return bool.parse(response.body);
    } else {
      log(response.statusCode.toString());
      throw Exception("Unable To Fill Attendance");
    }
  }
}
