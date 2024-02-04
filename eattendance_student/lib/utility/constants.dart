const String appName = "eAttendance";

const String appLogo = "assets/images/logo.png";
Map<String, String> createAuthorizationHeaders(String? token, {bool contentType = false}) {
  if(contentType) {
    return {'Authorization': 'Bearer $token',"Content-Type": "application/json"};
  }
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl =
    "https://1a0d-2409-40c1-4010-66e-5d16-3cd6-c927-a369.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
