/*
 * //
 * // Description: EasyTraffic
 * //
 * // 1/9/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.utils

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreference constructor(context: Context) {

    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(SHARED_PREFS_NAME, MODE)
    }

    companion object {
        private const val SHARED_PREFS_NAME = "Prefs"
        private const val MODE = Context.MODE_PRIVATE

        //constants for saving in shared prefs
        private const val SIGNED_IN = "signed_in"
        private const val NAME = "name"
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    internal fun clearSharedPrefs() {
        preferences.edit { it.clear() }
    }

    var signedIn: Boolean
        get() = preferences.getBoolean(SIGNED_IN, false)
        set(value) = preferences.edit { it.putBoolean(SIGNED_IN, value) }

    var name: String?
        get() = preferences.getString(NAME, "")
        set(value) = preferences.edit { it.putString(NAME, value) }
}