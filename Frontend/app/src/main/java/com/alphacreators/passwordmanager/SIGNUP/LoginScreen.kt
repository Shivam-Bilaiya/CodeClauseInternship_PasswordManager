package com.alphacreators.passwordmanager.SIGNUP

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alphacreators.passwordmanager.Model.User
import com.alphacreators.passwordmanager.PASSWORDS.AllPasswords
import com.alphacreators.passwordmanager.R
import com.alphacreators.passwordmanager.ViewModel.UserViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class LoginScreen : AppCompatActivity() {
    private lateinit var loginText : MaterialTextView
    private lateinit var loginButton : MaterialButton
    private lateinit var continueAccount : MaterialTextView
    private lateinit var nameText : TextInputEditText
    private lateinit var emailText : TextInputEditText
    private lateinit var noAccount : MaterialTextView
    private lateinit var userViewModel: UserViewModel
    private lateinit var loginLayout : ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences
    private val STORE_USER_ID_PREF = "store_user_id"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        supportActionBar?.hide()

        loginText = findViewById(R.id.login);
        loginButton = findViewById(R.id.loginButton)
        continueAccount = findViewById(R.id.continueAccount)
        nameText = findViewById(R.id.loginName);
        emailText = findViewById(R.id.loginEmail)
        noAccount = findViewById(R.id.noAccount)
        loginLayout = findViewById(R.id.loginLayout)
        userViewModel = ViewModelProvider.AndroidViewModelFactory(Application()).create(
            UserViewModel::class.java)
        sharedPreferences = getSharedPreferences(STORE_USER_ID_PREF,Context.MODE_PRIVATE)


        val underlinedText = SpannableString(loginText.text)
        underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        loginText.text = underlinedText

        val text = SpannableString(continueAccount.text)
        text.setSpan(UnderlineSpan(),0,text.length,Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        continueAccount.text = text


        loginButton.setOnClickListener {
            val name = nameText.text.toString()
            val email = emailText.text.toString()

            if (name.isEmpty() || email.isEmpty()){
                Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show()
            }else{
                if (validateEmail(email)){
                    val user = User(name,email)
                    userViewModel.loginUserAccount(user){
                        if (it != null){
                            val userId = it.userId
                            Log.d("lwwknfwkfnw",userId.toString())
                            if (userId != null) {
                                if (userId > 0L)
                                    sharedPreferences.edit().putLong("userId",userId).apply()
                            }
                            Snackbar.make(
                                loginLayout,
                                "User Login Successful ",
                                Snackbar.ANIMATION_MODE_SLIDE
                            ).setBackgroundTint(
                                resources.getColor(
                                    android.R.color.holo_green_dark,
                                    null
                                )
                            )
                                .setTextColor(resources.getColor(R.color.text_color, null)).show()
                            val id = sharedPreferences.getLong("userId",-1)
                            Log.d("MY KEY OF LOGIN ACCOUNT IS " , id.toString())
                            intent = Intent(this@LoginScreen, AllPasswords::class.java)
                            startActivity(intent)
                        }else{
                            Snackbar.make(
                                loginLayout,
                                "User does not exist ",
                                Snackbar.ANIMATION_MODE_SLIDE
                            ).setBackgroundTint(
                                resources.getColor(
                                    android.R.color.holo_red_dark,
                                    null
                                )
                            )
                                .setTextColor(resources.getColor(R.color.text_color, null)).show()
                        }
                    }

                }else{
                    emailText.error = "Please valid enter email id"
                }
            }

        }

        noAccount.setOnClickListener {
            startActivity(Intent(this@LoginScreen, MainActivity::class.java))
        }

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)


    }

    private fun validateEmail(userEmail: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return emailRegex.matches(userEmail)
    }
}