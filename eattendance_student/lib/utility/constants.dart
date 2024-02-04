const String appName = "eAttendance";

const String appLogo = "assets/images/logo.png";
Map<String, String> createAuthorizationHeaders(String? token) {
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl =
    "https://158f-2409-40c1-100f-eb6-1509-4dc8-6625-b7f5.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
