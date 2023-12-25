package kz.main.thegoal

import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import kz.main.thegoal.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
        initAppsFlyer()
    }

    private fun initAppsFlyer() {
        AppsFlyerLib.getInstance().start(this, APPS_FLYER_KEY, object : AppsFlyerRequestListener {
            override fun onSuccess() {
                Log.d("appsFlyer", "Launch sent successfully")
            }
            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d("appsFlyer", "Error description: $errorDesc")
            }
        })
    }
}
