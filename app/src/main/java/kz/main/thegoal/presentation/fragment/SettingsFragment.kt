package kz.main.thegoal.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kz.main.thegoal.R
import kz.main.thegoal.SHARED_PREF_DEFAULT_LANG
import kz.main.thegoal.SHARED_PREF_KEY
import kz.main.thegoal.SHARED_PREF_NAME
import kz.main.thegoal.presentation.ds.SettingsItem

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        val sharedPref =
            requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val curLang = sharedPref.getString(SHARED_PREF_KEY, SHARED_PREF_DEFAULT_LANG)

        setContent {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                SettingsItem(
                    settingName = getString(R.string.settings_language),
                    context = requireActivity(),
                    curLang
                )
            }
        }
    }
}
