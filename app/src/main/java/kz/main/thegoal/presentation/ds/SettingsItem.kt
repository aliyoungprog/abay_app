package kz.main.thegoal.presentation.ds

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kz.main.thegoal.LANGUAGE_CHANGED
import kz.main.thegoal.SHARED_PREF_DEFAULT_LANG
import kz.main.thegoal.SHARED_PREF_KEY
import kz.main.thegoal.SHARED_PREF_SECONDARY_LANG

@Composable
fun SettingsItem(
    settingName: String,
    context: Activity,
    currentLang: String?
) {

    val checkState = remember {
        mutableStateOf(!currentLang.equals(SHARED_PREF_DEFAULT_LANG))
    }

    Row(
        modifier =
        Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = settingName,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = checkState.value,
            onCheckedChange = {
                checkState.value = it
                val intent = Intent().apply {
                    action = LANGUAGE_CHANGED
                }
                intent.putExtra(
                    SHARED_PREF_KEY,
                    if (it) SHARED_PREF_SECONDARY_LANG else SHARED_PREF_DEFAULT_LANG
                )
                context.sendBroadcast(intent)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Gray
            )
        )
    }
}