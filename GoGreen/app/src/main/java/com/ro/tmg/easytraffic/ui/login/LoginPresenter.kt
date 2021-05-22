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

import com.ro.tmg.easytraffic.R
import com.ro.tmg.easytraffic.ui.login.models.LoginFormState

class LoginPresenter {

    private var view: LoginActivityView? = null

    internal fun bind(view: LoginActivityView) {
        check(this.view == null) { "view already bound" }
        this.view = view
    }

    internal fun unbind() {
        checkNotNull(this.view) { "view already unbound" }
        this.view = null
    }

    internal fun login(name: String, phone: String, email: String, password: String, cardNr: String, holder: String, month: String, year: String, cvv: String) {
        val loginFormState = LoginFormState().apply {
            isDataValid = true

            if (!isFieldValid(name)) {
                nameError = R.string.invalid_field
                isDataValid = false
            }
            if (!isFieldValid(phone)) {
                phoneError = R.string.invalid_field
                isDataValid = false
            }
            if (!isFieldValid(email)) {
                emailError = R.string.invalid_field
                isDataValid = false
            }
            if (!isPasswordValid(password)) {
                passwordError = R.string.invalid_password
                isDataValid = false
            }
            if (!isFieldValid(cardNr)) {
                cardNumberError = R.string.invalid_field
                isDataValid = false
            }
            if (!isFieldValid(holder)) {
                holderError = R.string.invalid_field
                isDataValid = false
            }
            if (!isFieldValid(month)) {
                monthError = R.string.invalid_field
                isDataValid = false
            }
            if (!isFieldValid(year)) {
                yearError = R.string.invalid_field
                isDataValid = false
            }
            if (!isFieldValid(cvv)) {
                cvvError = R.string.invalid_field
                isDataValid = false
            }
        }

        view?.loginFormStateChanged(loginFormState)
    }

    // A placeholder username validation check
    private fun isFieldValid(username: String): Boolean {
        return username.isNotBlank()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}