package com.teyyihan.data.util

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class LiveSharedPreferences<T>(val sharedPrefs: SharedPreferences,
                                           val key: String,
                                           val defValue: T?) : LiveData<T>() {

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        if (key == this.key) {
            value = defValue?.let { getValueFromPreferences(key, it) }
        }
    }

    abstract fun getValueFromPreferences(key: String, defValue: T): T?

    override fun onActive() {
        super.onActive()
        value = defValue?.let { getValueFromPreferences(key, it) }
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }
}

class SharedPreferenceStringLiveData(sharedPrefs: SharedPreferences, key: String, defValue: String?) :
    LiveSharedPreferences<String?>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: String?): String? =  sharedPrefs.getString(key, defValue)
}


fun SharedPreferences.stringLiveData(key: String, defValue: String?): LiveSharedPreferences<String?> {
    return SharedPreferenceStringLiveData(this, key, defValue)
}