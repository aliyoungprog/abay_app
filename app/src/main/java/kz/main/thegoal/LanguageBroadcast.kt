package kz.main.thegoal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class LanguageBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == LANGUAGE_CHANGED) {
            val curLang = intent.getStringExtra(SHARED_PREF_KEY)
            context?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)?.edit()?.apply {
                putString(SHARED_PREF_KEY, curLang)
            }?.apply()
            setLang(curLang.orEmpty(), context)
        }
    }

    private fun setLang(curLang: String, context: Context?) {
        val activity = context as MainActivity
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(curLang))
        val intent = Intent(activity, MainActivity::class.java)
        activity.finish()
        activity.startActivity(intent)
    }
}