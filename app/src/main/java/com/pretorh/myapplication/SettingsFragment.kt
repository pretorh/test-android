package com.pretorh.myapplication

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.preference.*

class DataStore(context: Context) : PreferenceDataStore() {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun putBoolean(key: String?, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    override fun getBoolean(key: String?, defValue: Boolean): Boolean = prefs.getBoolean(key, defValue)

    override fun putString(key: String?, value: String?) = prefs.edit().putString(key, value).apply()

    override fun getString(key: String?, defValue: String?): String? = prefs.getString(key, defValue)

    override fun putInt(key: String?, value: Int) = prefs.edit().putInt(key, value).apply()

    override fun getInt(key: String?, defValue: Int): Int = prefs.getInt(key, defValue)
}

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var dataStore: DataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        dataStore = DataStore(context!!)
        Log.d("settings", "number as string = ${dataStore.getString("number", "0")}")

        useACustomDataStoreForAllPreferences()
        setPreferencesFromResource(R.xml.prefs, rootKey)
        setPreferenceToAcceptNumbersOnly()
        setupSeekbarPreference()
    }

    private fun setupSeekbarPreference() {
        val preference = findPreference<SeekBarPreference>("number2")
        preference?.min = 1
        preference?.max = 10
        preference?.showSeekBarValue = true
    }

    private fun setPreferenceToAcceptNumbersOnly() {
        findPreference<EditTextPreference?>("number")?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    private fun useACustomDataStoreForAllPreferences() {
        preferenceManager.preferenceDataStore = dataStore
    }
}
