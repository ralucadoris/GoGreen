package com.ro.tmg.easytraffic.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ro.tmg.easytraffic.R
import com.ro.tmg.easytraffic.ui.login.models.LoginFormState
import com.ro.tmg.easytraffic.ui.main.MainActivity
import com.ro.tmg.easytraffic.ui.utils.AppSharedPreference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.card_fields.*
import kotlinx.android.synthetic.main.form_input_fields.*

class LoginActivity : AppCompatActivity(), LoginActivityView {

    private val loginPresenter: LoginPresenter = LoginPresenter()
    lateinit var auth:FirebaseAuth
    lateinit var userDatabaseReference:DatabaseReference
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        userDatabaseReference= database!!.reference.child("User");
        loginPresenter.bind(this)

        setListeners()
    }

    private fun registerBtnCLicked() {

        loginPresenter.register(auth,userDatabaseReference,database, name_et.text.toString(), phone_et.text.toString(),
            email_et.text.toString(), password_et.text.toString(),
            card_et.text.toString(), holder_et.text.toString(),
            month_et.text.toString(), year_et.text.toString(), cvv_et.text.toString()
        )

    }


    private fun setListeners() {
        register_btn.setOnClickListener { registerBtnCLicked() }
        login_btn.setOnClickListener { loginBtnClicked() }
        cvv_et.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginBtnClicked()
            }
            false
        }
    }

    private fun loginBtnClicked() {
        loginPresenter.login(auth,
            name_et.text.toString(), phone_et.text.toString(),
            email_et.text.toString(), password_et.text.toString(),
            card_et.text.toString(), holder_et.text.toString(),
            month_et.text.toString(), year_et.text.toString(), cvv_et.text.toString()
        )
    }

    override fun loginFormStateChanged(loginState: LoginFormState) {
        if (loginState.isDataValid) {
            AppSharedPreference(this).signedIn = true
            AppSharedPreference(this).name = name_et.text.toString()
            startMainActivity()
            return
        }
        if (loginState.nameError != null) {
            name_et.error = getString(loginState.nameError!!)
        }
        if (loginState.phoneError != null) {
            phone_et.error = getString(loginState.phoneError!!)
        }
        if (loginState.emailError != null) {
            email_et.error = getString(loginState.emailError!!)
        }
        if (loginState.passwordError != null) {
            password_et.error = getString(loginState.passwordError!!)
        }
        if (loginState.cardNumberError != null) {
            card_et.error = getString(loginState.cardNumberError!!)
        }
        if (loginState.holderError != null) {
            holder_et.error = getString(loginState.holderError!!)
        }
        if (loginState.monthError != null) {
            month_et.error = getString(loginState.monthError!!)
        }
        if (loginState.yearError != null) {
            year_et.error = getString(loginState.yearError!!)
        }
        if (loginState.cvvError != null) {
            cvv_et.error = getString(loginState.cvvError!!)
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        loginPresenter.unbind()
        super.onDestroy()
    }
}