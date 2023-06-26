package kz.main.thegoal.presentation.fragment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


class SettingsFragment : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SomeText()
        }
    }
}

@Composable
fun SomeText() {
    Text(text = "test")
}
