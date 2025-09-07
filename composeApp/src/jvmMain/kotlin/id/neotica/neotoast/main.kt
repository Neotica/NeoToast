package id.neotica.neotoast

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import id.neotica.toast.setComposeWindowProvider

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "NeoToast",
    ) {
        setComposeWindowProvider {
            window
        }
        App()
    }
}