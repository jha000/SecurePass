# SecurePass

A password manager app that allows users to save their login credentials securely and access them using biometric authentication. The app also includes offline backup features with import and export capabilities, as well as a splash screen with Lottie animation, circular image display, and image picker.

## Technologies Used

- Android Studio - Integrated Development Environment (IDE) used to build the Android app
- Kotlin - Programming language used to write the app's code
- XML: The app uses XML to define the user interface and layout of each screen.
- Room - Android's built-in SQLite database library used to store user login credentials
- BiometricPrompt - Android's biometric authentication library used to authenticate users
- Material Design - Design language used to create the app's user interface

## Dependencies

Here's a list of dependencies used in the app:

```python
dependencies {
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    implementation 'androidx.biometric:biometric:1.2.0'
    implementation 'androidx.room:room-runtime:2.4.0'
    kapt 'androidx.room:room-compiler:2.4.0'
    implementation 'com.airbnb.android:lottie:4.1.0'
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.github.dhaval2404:imagepicker:1.7.6'
}

```

## Installation

Download the APK file from the Google Drive link provided and install it directly on your Android device by following these steps:

To install the app on your device, follow these steps:
- Google Drive link https://drive.google.com/file/d/1LQdhzWxDciMgkhk3ZstS15Z4ojkSkT-Y/view?usp=sharing
- Enable "Unknown Sources" in your device's settings.
- Tap on the downloaded APK file to install the app.

Alternatively, you can clone the repository: git clone https://github.com/your-username/your-repo.git
Open the project in Android Studio
Build and run the app on your device


## Usage

To use the app:

- Open the app on your device
- Authenticate using your biometric credentials
- Add your login credentials for any service or website you want to save
- Access your saved login credentials whenever you need them by authenticating using your biometric credentials
- To backup your data offline, go to the backup option and select "Export". You can export your data to a file and  save it to your device.
- To restore your data from a backup file, go to the backup option and select "Import". You can import your data from a file stored on your device.
- Enjoy the app's Lottie animated splash screen, circular image display, and image picker features.

## Reasons for not implementing online backup

- One of the key features of this app is the ability to backup user data offline. Users can export their data to a file and save it to their device. This ensures that users always have a backup of their data in case of any device failure or loss. 
- However, I have decided not to implement online backup due to cost reasons. Online backup solutions typically
require ongoing fees for storage and bandwidth, which can
add up quickly as more users join the app. To keep the app
free for users and avoid potential privacy and security concerns
associated with storing data on third-party servers, the decision
was made to offer offline backup options only.
