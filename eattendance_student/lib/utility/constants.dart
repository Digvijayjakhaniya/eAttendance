const String appName = "eAttendance";

const String appLogo = "assets/images/logo.png";
Map<String, String> createAuthorizationHeaders(String? token) {
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl =
    "https://884d-2406-b400-d11-87eb-d967-98f1-8371-e8e7.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
