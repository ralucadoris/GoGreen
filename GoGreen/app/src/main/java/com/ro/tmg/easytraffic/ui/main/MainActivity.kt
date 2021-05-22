/*
 * //
 * // Description: GoGreen
 * //
 * // 1/10/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ro.tmg.easytraffic.R
import com.ro.tmg.easytraffic.ui.location.LocationActivity
import com.ro.tmg.easytraffic.ui.login.LoginActivity
import com.ro.tmg.easytraffic.ui.utils.AppSharedPreference
import com.ro.tmg.easytraffic.ui.utils.PermissionUtils
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val permissionRequestCode = 100
    private lateinit var appSharedPreference: AppSharedPreference

    companion object {
        const val BUNDLE_NAME = "bundle"
        const val SCOOTER = "scooter"
        const val BIKE = "bike"
        const val BUS = "bus"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        appSharedPreference = AppSharedPreference(this)
        setUserName()
        setListeners()
    }

    private fun setUserName() {
        userName_tv.text = "Hello " + appSharedPreference.name
    }

    private fun setListeners() {
        scooter_btn.setOnClickListener { openLocationScreen(SCOOTER) }
        bike_btn.setOnClickListener { openLocationScreen(BIKE) }
        bus_btn.setOnClickListener { openLocationScreen(BUS) }
        logout_btn.setOnClickListener { logoutUser() }
    }

    private fun openLocationScreen(location: String) {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
            (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION), permissionRequestCode)
        } else {
            startActivity(Intent(this, LocationActivity::class.java).putExtra(BUNDLE_NAME, location))
        }
    }

    private fun logoutUser() {
        appSharedPreference.clearSharedPrefs()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionRequestCode) {
            if (PermissionUtils.verifyPermissionsResponse(grantResults) != -1) {
                PermissionUtils.showPermissionsSettingsDialog(this)
            }
        }
    }
}