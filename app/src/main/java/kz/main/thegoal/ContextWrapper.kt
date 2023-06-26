package kz.main.thegoal

import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

class ContextWrapper(base: Context): android.content.ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, locale: Locale): android.content.ContextWrapper {
            val res = context.resources
            val config = res.configuration
            var curContext = context
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale)
                LocaleList.setDefault(LocaleList(locale))
                config.setLocales(LocaleList(locale))
                curContext = context.createConfigurationContext(config)
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(locale)
                curContext = context.createConfigurationContext(config)
            } else {
                config.locale = locale
                res.updateConfiguration(config, res.displayMetrics)
            }
            return ContextWrapper(curContext)
        }
    }
}
