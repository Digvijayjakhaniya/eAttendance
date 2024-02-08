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
const hosturl = "https://0a73-2409-40c1-e-b822-a547-c83f-d588-f551.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
