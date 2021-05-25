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

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ro.tmg.easytraffic.R
import com.ro.tmg.easytraffic.ui.login.models.LoginFormState
import com.ro.tmg.easytraffic.ui.main.MainActivity
import java.security.AccessController.getContext
import java.sql.SQLOutput

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

    internal fun login(auth: FirebaseAuth,
                       name: String, phone: String, email: String, password: String, cardNr: String, holder: String,
                       month: String, year: String, cvv: String) {
        val loginFormState = LoginFormState().apply {
            isDataValid = true

            if (!isFieldValid(email)) {
                emailError = R.string.invalid_field
                isDataValid = false
            }
            if (!isPasswordValid(password)) {
                passwordError = R.string.invalid_password
                isDataValid = false
            }
        }

        if(loginFormState.isDataValid){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful)
                    view?.loginFormStateChanged(loginFormState)
            }

        }


    }

    internal fun register(auth: FirebaseAuth,databaseReference:DatabaseReference,database: FirebaseDatabase,
                          name: String, phone: String, email: String, password: String, cardNr: String, holder: String,
                          month: String, year: String, cvv: String) {

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

        if(loginFormState.isDataValid) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser;

                    val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                    currentUserDb.child("Name").setValue(name)
                    currentUserDb.child("Phone").setValue(phone)
                    currentUserDb.child("Email").setValue(email)
                    currentUserDb.child("Card number").setValue(cardNr)
                    currentUserDb.child("Holder").setValue(holder)
                    currentUserDb.child("Month").setValue(month)
                    currentUserDb.child("Year").setValue(year)
                    currentUserDb.child("CVV").setValue(cvv)

                } else {
                    println(it.exception)
                }
            }
            view?.loginFormStateChanged(loginFormState)
        }

    }

    // A placeholder username validation check
    fun isFieldValid(username: String): Boolean {
        return username.isNotBlank()
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}