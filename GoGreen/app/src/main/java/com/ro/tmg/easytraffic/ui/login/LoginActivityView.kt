/*
 * //
 * // Description: EasyTraffic
 * //
 * // 1/9/2020
 * //
 * // Copyright (c) Rosoftlab Romania. All rights reserved.
 * //
 */
package com.ro.tmg.easytraffic.ui.login

import com.ro.tmg.easytraffic.ui.login.models.LoginFormState

interface LoginActivityView {

    fun loginFormStateChanged(loginState: LoginFormState)
}