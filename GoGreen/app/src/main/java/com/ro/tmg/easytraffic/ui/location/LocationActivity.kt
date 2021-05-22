/*
 * //
 * // Description: GoGreen
 * //
 * // 1/10/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.location

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ro.tmg.easytraffic.R
import com.ro.tmg.easytraffic.ui.barcode.BarcodeScannerActivity
import com.ro.tmg.easytraffic.ui.main.MainActivity
import kotlinx.android.synthetic.main.location_activity.*

class LocationActivity : AppCompatActivity() {

    private lateinit var locationType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        setToolbar()
        getBundle()
        initializeMap()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getBundle() {
        locationType = intent.extras?.getString(MainActivity.BUNDLE_NAME, MainActivity.SCOOTER) ?: MainActivity.SCOOTER
    }

    private fun initializeMap() {
        (mapFragment as SupportMapFragment).getMapAsync {
            it.apply {
                uiSettings.isRotateGesturesEnabled = true
                uiSettings.isTiltGesturesEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
                isMyLocationEnabled = true

                animateUserLocation(it)
                addLocations(it)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun animateUserLocation(map: GoogleMap) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(45.6560564, 25.5672784), 11F))
    }

    private fun addLocations(map: GoogleMap) {
        when (locationType) {
            MainActivity.SCOOTER -> setScooterLocations(map)
            MainActivity.BIKE -> setBikeLocations(map)
            MainActivity.BUS -> setBusLocations(map)
        }

        map.setOnInfoWindowClickListener {
            openScanScreen()
        }
    }


    private fun setScooterLocations(googleMap: GoogleMap) {
        for (location in LocationUtils.getScooterLocations()) {
            googleMap.addMarker(MarkerOptions().title(location.key).position(location.value))
        }
    }

    private fun setBikeLocations(googleMap: GoogleMap) {
        for (location in LocationUtils.getBikeLocations()) {
            googleMap.addMarker(MarkerOptions().title(location.key).position(location.value))
        }
    }

    private fun setBusLocations(googleMap: GoogleMap) {
        for (location in LocationUtils.getBusLocations()) {
            googleMap.addMarker(MarkerOptions().title(location.key).position(location.value))
        }
    }

    private fun openScanScreen() {
        startActivity(Intent(this, BarcodeScannerActivity::class.java))
    }
}