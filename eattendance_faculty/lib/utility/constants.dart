const String appName = "eAttendance Faculty";

const String appLogo = "assets/images/logo.png";

Map<String, String> createAuthorizationHeaders(String? token) {
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl = "";

const String apiUrl = "$hosturl$endpoint";
