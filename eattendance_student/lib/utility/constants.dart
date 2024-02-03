const String appName = "eAttendance";

const String appLogo = "assets/images/logo.png";
Map<String, String> createAuthorizationHeaders(String? token) {
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl =
    "https://46b2-2406-b400-d11-87eb-9800-6f0b-31fb-6cd0.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
