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

import com.google.android.gms.maps.model.LatLng

object LocationUtils {

    fun getScooterLocations(): Map<String, LatLng> {
        val markers = mutableMapOf<String, LatLng>()

        markers["Scooter 001"] = LatLng(45.655698, 25.5984169)
        markers["Scooter 002"] = LatLng(45.6492901, 25.5993584)
        markers["Scooter 003"] = LatLng(45.6442627, 25.5933791)
        markers["Scooter 004"] = LatLng(45.653279330, 25.602909196085)
        markers["Scooter 005"] = LatLng(45.654350999, 25.596675)

        return markers.toMap()
    }

    fun getBikeLocations(): Map<String, LatLng> {
        val markers = mutableMapOf<String, LatLng>()

        markers["Bike 1"] = LatLng(45.651670, 25.598042)
        markers["Bike 2"] = LatLng(45.651670, 25.59804)
        markers["Bike 3"] = LatLng(45.6442627, 25.5933791)
        markers["Bike 4"] = LatLng(45.64637156, 25.5963213)
        markers["Bike 5"] = LatLng(45.646371569, 25.5963213)

        return markers.toMap()
    }

    fun getBusLocations(): Map<String, LatLng> {
        val markers = mutableMapOf<String, LatLng>()

        markers["Bus 10"] = LatLng(45.6463715, 25.596321)
        markers["Bus 20"] = LatLng(45.64637156, 25.59632133)
        markers["Bus 25"] = LatLng(45.644233, 25.6035418)
        markers["Bus 40"] = LatLng(45.6559920, 25.6052496)
        markers["Bus 5"] = LatLng(45.6559920, 25.605249)

        return markers.toMap()
    }
}