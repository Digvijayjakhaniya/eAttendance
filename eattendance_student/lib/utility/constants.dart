const String appName = "eAttendance";

const String appLogo = "assets/images/logo.png";
Map<String, String> createAuthorizationHeaders(String? token,
    {bool contentType = false}) {
  if (contentType) {
    return {
      'Authorization': 'Bearer $token',
      "Content-Type": "application/json"
    };
  }
  return {"Authorization": 'Bearer $token'};
}

const endpoint = "/api/v1";

// HOST URL
const hosturl =
    "https://b329-2409-40c1-30-224a-8c9c-2dfd-af57-7261.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
