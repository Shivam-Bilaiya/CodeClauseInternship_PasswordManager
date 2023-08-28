package com.alphacreators.passwordmanager.SIGNUP

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alphacreators.passwordmanager.Model.User
import com.alphacreators.passwordmanager.PASSWORDS.AllPasswords
import com.alphacreators.passwordmanager.R
import com.alphacreators.passwordmanager.ViewModel.UserViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var nameLayout: TextInputLayout
    private lateinit var nameText: TextInputEditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var emailText: TextInputEditText
    private lateinit var signUp: MaterialButton
    private lateinit var textView: MaterialTextView
    private lateinit var imageView: ImageView
    private lateinit var userViewModel: UserViewModel
    private val STORE_USER_ID_PREF = "store_user_id"
    private lateinit var signUpText: MaterialTextView
    private lateinit var sign : MaterialTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        nameLayout = findViewById(R.id.nameLayout)
        nameText = findViewById(R.id.nameText)
        emailLayout = findViewById(R.id.emailLayout)
        emailText = findViewById(R.id.emailText)
        signUp = findViewById(R.id.signUpButton)
        textView = findViewById(R.id.alreadyAccount)
        signUpText = findViewById(R.id.signUpTitle)
        sign = findViewById(R.id.signupText)
        userViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(UserViewModel::class.java)

        val underlinedText = SpannableString(signUpText.text)
        underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        signUpText.text = underlinedText

        val text = SpannableString(sign.text)
        text.setSpan(UnderlineSpan(),0,text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        sign.text = text


        signUp.setOnClickListener {
            saveUser()
        }

        textView.setOnClickListener{
            showLoginScreen();
        }

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)

    }

    private fun showLoginScreen() {
        startActivity(Intent(this, LoginScreen::class.java))
    }


    private fun saveUser() {
        val layout: RelativeLayout = findViewById(R.id.signupLayout)
        val userName = nameText.text.toString()
        val userEmail = emailText.text.toString()

        if (userName.isEmpty()) {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show()
        } else if (userEmail.isEmpty()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
        } else {
            if (validateEmail(userEmail)) {
                val user = User(userName, userEmail)
                Log.d("kenfekfnekfne", user.toString())
                userViewModel.addNewUser(user) {
                    Log.d("lenfefnekfne", "wfnkenfkenf")
                    if (it != null) {
                        if (it.userId != 0L) {
                            val sharedPreferences =
                                getSharedPreferences(STORE_USER_ID_PREF, Context.MODE_PRIVATE)
                            sharedPreferences.edit().putLong("userId", it.userId!!).apply()
                            Snackbar.make(
                                layout,
                                "User Registration Successful ",
                                Snackbar.ANIMATION_MODE_SLIDE
                            ).setBackgroundTint(
                                resources.getColor(
                                    android.R.color.holo_green_dark,
                                    null
                                )
                            )
                                .setTextColor(resources.getColor(R.color.text_color, null)).show()
                            intent = Intent(this@MainActivity, AllPasswords::class.java)
                            startActivity(intent)
                        } else {
                            Snackbar.make(
                                layout,
                                "Something is went wrong",
                                Snackbar.ANIMATION_MODE_SLIDE
                            ).setBackgroundTint(
                                resources.getColor(
                                    android.R.color.holo_red_dark,
                                    null
                                )
                            )
                                .setTextColor(resources.getColor(R.color.text_color, null)).show()
                        }

                    } else {
                        Snackbar.make(
                            layout,
                            "Oops Something went wrong",
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
                emailText.error = "Please enter valid email"
            }

        }


    }

    private fun validateEmail(userEmail: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return emailRegex.matches(userEmail)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }



}