/*
 * //
 * // Description: GoGreen
 * //
 * // 1/10/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.barcode

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BarcodeScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private lateinit var scannerView: ZXingScannerView

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
        scannerView.setFormats(listOf(BarcodeFormat.QR_CODE))
        setToolbar()
    }

    private fun setToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        scannerView.stopCamera()
        super.onPause()
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

    override fun handleResult(rawResult: Result?) {
        if (rawResult?.text != null) {
            try {
                MaterialDialog(this).show {
                    title(text = "Vehicle Scanned")
                    message(text = rawResult.text)
                    positiveButton(text = "Ok") { finish() }
                    negativeButton(text = "Retry") { scannerView.resumeCameraPreview(this@BarcodeScannerActivity) }
                }
            } catch (e: Exception) {
            }
        } else {
            scannerView.resumeCameraPreview(this)
        }
    }
}