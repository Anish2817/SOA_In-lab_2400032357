SIMPLE JWT GENERATION - SPRING TOOL SUITE

Requirements:
- JDK 17 or later
- Spring Tool Suite (STS)
- Internet connection on first Maven build (to download dependencies)

Import into STS:
1. Extract SimpleJwtSpringBoot.zip.
2. Open Spring Tool Suite.
3. Select File > Import.
4. Choose Maven > Existing Maven Projects, then Next.
5. Browse to the extracted SimpleJwtSpringBoot folder.
6. Select pom.xml, then Finish.
7. Wait for Maven dependencies to download.
8. Right-click SimpleJwtApplication.java > Run As > Spring Boot App.

Test using Postman:
- Method: POST
- URL: http://localhost:8080/api/jwt/generate
- Body > raw > JSON:
  {
    "username": "prudhvi",
    "role": "USER"
  }

Expected response:
  {
    "token": "eyJ..."
  }

The token is a signed JWT and expires after one hour. It contains:
- subject: username
- role: supplied role
- issued-at time
- expiration time

JWT structure:
header.payload.signature

Important:
This is a classroom demo. The endpoint accepts the username and role supplied by the caller,
so it is NOT secure authentication on its own. In a real application, verify the user's
identity and role before issuing a token, and store the secret outside source control.
