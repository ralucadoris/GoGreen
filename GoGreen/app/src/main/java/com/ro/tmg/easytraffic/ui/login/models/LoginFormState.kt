package com.ro.tmg.easytraffic.ui.login.models

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    var isDataValid: Boolean = false,
    var nameError: Int? = null,
    var phoneError: Int? = null,
    var emailError: Int? = null,
    var passwordError: Int? = null,
    var cardNumberError: Int? = null,
    var holderError: Int? = null,
    var monthError: Int? = null,
    var yearError: Int? = null,
    var cvvError: Int? = null
)
