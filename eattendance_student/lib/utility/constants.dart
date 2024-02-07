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
    "https://ffde-2409-40c1-10da-ed53-3962-71c0-a3cf-d610.ngrok-free.app";

const String apiUrl = "$hosturl$endpoint";
