package com.alphacreators.passwordmanager.PASSWORDS

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alphacreators.passwordmanager.Model.Password
import com.alphacreators.passwordmanager.R
import com.alphacreators.passwordmanager.ViewModel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class NewPassword : AppCompatActivity() {

    private lateinit var passwordLabel: EditText
    private lateinit var password: EditText
    private lateinit var saveButton: Button
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private val STORE_USER_ID_PREF = "store_user_id"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)


        passwordLabel = findViewById(R.id.passwordLabel)
        password = findViewById(R.id.passwordText)
        saveButton = findViewById(R.id.saveButton)
        sharedPreferences = getSharedPreferences(STORE_USER_ID_PREF,Context.MODE_PRIVATE)
        userViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(UserViewModel::class.java)

        saveButton.setOnClickListener {
            if (passwordLabel.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                savePassword(passwordLabel.text.toString(), password.text.toString())
            }
        }
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
    }

    private fun savePassword(passwordLabel: String, password: String) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let { focusedView ->
            inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
        }
        val layout: RelativeLayout = findViewById(R.id.relativeLayout)
        val userId = sharedPreferences.getLong("userId",0L)
        if (userId == 0L){
            Log.d(";lmflen",userId.toString())
            Snackbar.make(
                layout,
                "Oops Something went wrong",
                Snackbar.ANIMATION_MODE_SLIDE
            )
                .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, null))
                .setTextColor(resources.getColor(R.color.text_color, null)).show()
        }else{
            Log.d("dknvkvkbv",userId.toString())
            val currentDate = LocalDate.now()
            val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedDate = currentDate.format(dateFormat)
            GlobalScope.launch {
                val passwords = Password(passwordLabel, password, formattedDate)
                Log.d("qawsedrft", passwords.toString())
                userViewModel.addUserPassword(userId, passwords) {
                    runOnUiThread {
                        if (it != null) {
                            Snackbar.make(
                                layout,
                                "Password added successfully",
                                Snackbar.ANIMATION_MODE_SLIDE
                            )
                                .setBackgroundTint(
                                    resources.getColor(
                                        android.R.color.holo_green_dark,
                                        null
                                    )
                                )
                                .setTextColor(resources.getColor(R.color.text_color, null)).show()
                        } else {
                            Snackbar.make(
                                layout,
                                "Oops Something went wrong",
                                Snackbar.ANIMATION_MODE_SLIDE
                            )
                                .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, null))
                                .setTextColor(resources.getColor(R.color.text_color, null)).show()


                        }
                    }

                }
            }
        }

    }





}