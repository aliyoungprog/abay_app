package kz.main.thegoal

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.main.thegoal.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //@RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        ) // make only light mode
        setContentView(binding.root)
        //setLang()
        val navView: BottomNavigationView = binding.bottomNavView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        val dialog = object : DialogFragment() {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                return activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setTitle(R.string.messages)
                        .setMessage(R.string.no_messages)
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton(R.string.come_back) { dialog, id ->
                            dialog.cancel()
                        }
                    builder.create()
                } ?: throw IllegalStateException("Activity cannot be null")
            }
        }

        binding.header.headerBell.setOnClickListener {
            val manager = supportFragmentManager

            val transaction: FragmentTransaction = manager.beginTransaction()
            dialog.show(transaction, "dialog")
        }
    }

//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun setLang() {
//        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
//        val curLang = sharedPref.getString("lang", "ru")
//        //val resources = resources
//        applicationContext.resources.configuration.setLocales(LocaleList(Locale(curLang)))
//        applicationContext.resources.updateConfiguration(applicationContext.resources.configuration, applicationContext.resources.displayMetrics)
//    }

//    override fun attachBaseContext(newBase: Context?) {
//        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
//        val curLang = sharedPref.getString("lang", "ru")
//        val context = newBase?.let { ContextWrapper.wrap(it, Locale(curLang)) }
//        super.attachBaseContext(context)
//    }
}
