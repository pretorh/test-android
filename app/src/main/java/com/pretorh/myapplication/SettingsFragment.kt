package com.pretorh.myapplication

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceFragmentCompat

class DataStore(context: Context) : PreferenceDataStore() {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun putBoolean(key: String?, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    override fun getBoolean(key: String?, defValue: Boolean): Boolean = prefs.getBoolean(key, defValue)

    override fun putString(key: String?, value: String?) = prefs.edit().putString(key, value).apply()

    override fun getString(key: String?, defValue: String?): String? = prefs.getString(key, defValue)
}

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        useACustomDataStoreForAllPreferences()
        setPreferencesFromResource(R.xml.prefs, rootKey)
        findPreference<EditTextPreference?>("number")?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    private fun useACustomDataStoreForAllPreferences() {
        preferenceManager.preferenceDataStore = DataStore(context!!)
    }
}
