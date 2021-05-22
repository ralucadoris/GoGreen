/*
 * //
 * // Description: GoGreen
 * //
 * // 1/10/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import com.afollestad.materialdialogs.MaterialDialog
import com.ro.tmg.easytraffic.R

object PermissionUtils {

    fun verifyPermissionsResponse(grantResults: IntArray): Int {
        if (grantResults.isEmpty()) {
            return -1
        }

        for (i in grantResults.indices) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return i
            }
        }

        return -1
    }

    fun showPermissionsSettingsDialog(activity: Activity, negativeBtnCallback: (() -> Unit)? = null) {
        if (activity.isFinishing || activity.isDestroyed) {
            return
        }

        MaterialDialog(activity)
            .show {
                title(R.string.permission_denied_title)
                message(R.string.permission_denied_msg)
                positiveButton(R.string.permission_denied_settings_button) {
                    activity.startActivity(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", activity.packageName, null))
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                }
                negativeButton(R.string.permission_denied_cancel_btn) {
                    negativeBtnCallback?.invoke()
                }
                cancelable(false)
            }
    }
}