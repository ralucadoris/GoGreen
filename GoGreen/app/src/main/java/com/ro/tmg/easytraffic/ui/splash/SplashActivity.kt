/*
 * //
 * // Description: EasyTraffic
 * //
 * // 1/9/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ro.tmg.easytraffic.R
import com.ro.tmg.easytraffic.ui.login.LoginActivity
import com.ro.tmg.easytraffic.ui.main.MainActivity
import com.ro.tmg.easytraffic.ui.utils.AppSharedPreference

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        checkIfIsSignedIn()
    }

    private fun checkIfIsSignedIn() {
        AppSharedPreference(this).apply {
            if (!signedIn) {
                startScreen(getLoginRunnable())
            } else {
                startScreen(getMainRunnable())
            }
        }
    }

    private fun startScreen(runnable: Runnable) {
        handler.postDelayed(runnable, 2000)
    }

    private fun getLoginRunnable(): Runnable {
        return Runnable {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun getMainRunnable(): Runnable {
        return Runnable {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onStop() {
        runnable?.let { handler.removeCallbacks(it) }
        super.onStop()
    }
}