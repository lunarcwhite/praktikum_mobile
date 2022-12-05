package com.example.studikasus_rizu

import android.app.Application
import android.content.SharedPreferences

class ShPref : Application() {
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object {
        lateinit var instance: ShPref //letak Salah
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        pref = getSharedPreferences("KEY_DATA", MODE_PRIVATE)
        editor = pref.edit()
    }

    fun getEmail(): String {
        return pref.getString(KEY_EMAIL, "").toString()
    }

    fun getPassword(): String {
        return pref.getString(KEY_PASSWORD, "").toString()
    }
}