import 'dart:convert';

import '../../models/batch_model.dart';
import '../../models/course_model.dart';

class Student {
  int studentId;
  String studentEnrollment;
  String studentName;
  String studentEmail;
  String studentDivision;
  String studentPassword;
  Course studentCourse;
  Batch studentBatch;
  Student({
    required this.studentId,
    required this.studentEnrollment,
    required this.studentName,
    required this.studentEmail,
    required this.studentDivision,
    required this.studentPassword,
    required this.studentCourse,
    required this.studentBatch,
  });

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'studentId': studentId,
      'studentEnrollment': studentEnrollment,
      'studentName': studentName,
      'studentEmail': studentEmail,
      'studentDivision': studentDivision,
      'studentPassword': studentPassword,
      'studentCourse': studentCourse.toMap(),
      'studentBatch': studentBatch.toMap(),
    };
  }

  factory Student.fromMap(Map<String, dynamic> map) {
    return Student(
      studentId: map['studentId'] as int,
      studentEnrollment: map['studentEnrollment'] as String,
      studentName: map['studentName'] as String,
      studentEmail: map['studentEmail'] as String,
      studentDivision: map['studentDivision'] as String,
      studentPassword: map['studentPassword'] as String,
      studentCourse:
          Course.fromMap(map['studentCourse'] as Map<String, dynamic>),
      studentBatch: Batch.fromMap(map['studentBatch'] as Map<String, dynamic>),
    );
  }

  String toJson() => json.encode(toMap());

  factory Student.fromJson(String source) =>
      Student.fromMap(json.decode(source) as Map<String, dynamic>);
}
