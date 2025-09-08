# NeoToast
Simple Toast library for Compose Multiplatform targeting Android, iOS, and JVM (Desktop).

This library is implemented using the method in https://proandroiddev.com/how-to-show-toasts-in-compose-multiplatform-android-ios-desktop-with-expect-actual-85c630d46d06

## Setup
```kt
// build.gradle.kts

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("id.neotica:toasty:0.3.1") // Check for the latest version
        }
    }
}
```

### Android
On your MainActivity, add setActivityProvider
```kt
import id.neotica.toast.setActivityProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setActivityProvider { this } // add this

        setContent {
            App()
        }
    }
}
```

### JVM
On your `main.kt`, add setComposeWindowProvider
```kt
import id.neotica.toast.setComposeWindowProvider

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Notes",
    ) {
        setComposeWindowProvider { window } // add this
        App()
    }
}
```

## Usage
To use this after the setup, 
1. just add a mutable variable of `ToastManager()`
2. call the variable just like the regular toast. 
3. you can also add the duration, by calling `ToastDuration` enum, which I provides with two option: SHORT and LONG. The default value is SHORT.

```kt
@Composable
fun SomeView() {
    val toastManager by remember { mutableStateOf(ToastManager()) } // 1. add this

    Button(
        onClick = {
            toastManager.showToast("This is toast.") // 2. Implement just like regular toast
            toastManager.showToast("Note clicked: ${note.id}", ToastDuration.LONG) // 3. You can also add duration 
        }
    ) { Text("Demo") }
}
```
