import 'dart:convert';

class ImageModel {
  DateTime dateTime;
  String geoLocation;
  String imageText;
  String aiDignose;
  String expertDignose;
  final String url;

  ImageModel({
    required this.dateTime,
    required this.geoLocation,
    required this.imageText,
    required this.aiDignose,
    this.expertDignose = '',
    required this.url,
  });

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'dateTime': dateTime.millisecondsSinceEpoch,
      'geoLocation': geoLocation,
      'imageText': imageText,
      'aiDignose': aiDignose,
      'expertDignose': expertDignose,
      'url': url,
    };
  }

  factory ImageModel.fromMap(Map<String, dynamic> map) {
    return ImageModel(
      dateTime: DateTime.fromMillisecondsSinceEpoch(map['dateTime'] as int),
      geoLocation: map['geoLocation'] as String,
      imageText: map['imageText'] as String,
      aiDignose: map['aiDignose'] as String,
      expertDignose: map['expertDignose'] as String,
      url: map['url'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory ImageModel.fromJson(String source) =>
      ImageModel.fromMap(json.decode(source) as Map<String, dynamic>);
}
