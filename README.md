
# **Tamara Android SDK Example App**

This repository provides a working example of how to integrate the **Tamara Android SDK** into an Android application. The example demonstrates key functionalities and serves as a reference for developers using the Tamara SDK.

---

## **Features**
- Example integration of the Tamara Android SDK.
- Demonstrates payment processing and other core features.
- Configurable environment for testing.

---

## **Requirements**
- **Android Studio**: Version 2022.1.1 or higher.
- **Minimum Android SDK Version**: 21 (Android 5.0 Lollipop).
- **Target Android SDK Version**: 34.

---

## **Setup Instructions**

### **1. Download the Example App**
- Download the project as a ZIP file from the provided link or GitHub repository.
- Extract the ZIP file to a local directory.

### **2. Open the Project in Android Studio**
- Launch Android Studio.
- Open the project directory.
- Let Gradle sync automatically.

### **3. Configure Your SDK Credentials**
- Open `AppConst.kt` located in the `src/main/java/com/yourpackage/` directory.
- Update the following fields with your actual credentials:
```kotlin
object AppConst {
    const val AUTH_TOKEN = "your-auth-token" // Replace with your API Token
    const val API_URL = "https://api-sandbox.tamara.co" // Replace with the API URL for sandbox or production
    const val PUBLIC_KEY = "your-public-key" // Replace with your Public Key
    const val NOTIFICATION_WEB_HOOK_URL = "https://your-webhook-url.com" // Replace with your webhook URL
    const val NOTIFICATION_TOKEN = "your-notification-token" // Replace with your notification token
}
```

### **4. Verify Tamara SDK Version**
The `build.gradle` file specifies the Tamara SDK version being used. If needed, update the following line in `build.gradle` to use the desired version:
```gradle
implementation "co.tamara.merchant:android-sdk:1.1.0"
```
> **Note:** Ensure you use the latest compatible version of the Tamara SDK for your app.

### **5. Build and Run the App**
- Connect a physical Android device or start an emulator.
- Click the **Run** button in Android Studio to install and test the app.

---

## **Repository Structure**
```plaintext
├── README.md              # Documentation file
├── build.gradle           # Project-level Gradle configuration
├── app/
│   ├── src/               # Application source code
│   ├── build/             # Build output directory (ignored)
│   ├── google-services.json # Firebase configuration (ignored)
│   ├── proguard-rules.pro # ProGuard rules for release builds
├── gradlew                # Gradle wrapper script
├── tamara-example.jks     # Keystore file (ignored)
```

---

## **Usage Instructions**
1. Navigate through the app to understand the integration flow.
2. Refer to `MainActivity.kt` for implementation details of the Tamara SDK.
3. Experiment with different configurations in `AppConst.kt` to adapt the app to your environment.

---

## **Important Notes**
- **Do not hardcode sensitive credentials** (e.g., `AUTH_TOKEN`, `PUBLIC_KEY`) in production apps. This example is for testing purposes only.
- Use secure methods, such as encrypted storage or server-side configurations, for production environments.

---

## **Support**
For issues or questions about the Tamara SDK, contact [Tamara Integration Support](integrations@tamara.co).

---
