package com.alphacreators.passwordmanager.PASSWORDS

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
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
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ShowPassword : AppCompatActivity() {
    private var pId: Long = 0L
    private lateinit var showPasswordLabel: EditText
    private lateinit var showPassword: EditText
    private lateinit var showUpdatePassword: Button
    private lateinit var userViewModel: UserViewModel
    private lateinit var showRelativeLayout: RelativeLayout
    private lateinit var date: MaterialTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_password)


        showPasswordLabel = findViewById(R.id.showPasswordLabel)
        showPassword = findViewById(R.id.showPassword)
        showUpdatePassword = findViewById(R.id.showUpdatePassword)
        showRelativeLayout = findViewById(R.id.showRelativeLayout)
        date = findViewById(R.id.passwordDate)
        userViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(UserViewModel::class.java)

        getPassword()

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)

        showUpdatePassword.setOnClickListener {
            val label = showPasswordLabel.text.toString()
            val pass = showPassword.text.toString()

            if (label.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "All Fields are required", Toast.LENGTH_SHORT).show()
            } else {
                val currentDate = LocalDate.now()
                val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val formattedDate = currentDate.format(dateFormat)
                val password = Password(label, pass, formattedDate)
                Log.d("ioeldm", password.toString())
                userViewModel.updatePassword(pId, password) {
                    if (it != null) {
                        Log.d("qwe456tg", it.toString())
                        Snackbar.make(
                            showRelativeLayout,
                            "Password Update Successfully",
                            Snackbar.ANIMATION_MODE_SLIDE
                        ).setBackgroundTint(
                            resources.getColor(
                                android.R.color.holo_green_dark,
                                null
                            )
                        ).setTextColor(resources.getColor(R.color.text_color, null)).show()
                    } else {
                        Log.d("qwe456tg11", "ksnwdkw")
                        Snackbar.make(
                            showRelativeLayout,
                            "Oops Something Went Wrong",
                            Snackbar.ANIMATION_MODE_SLIDE
                        ).setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, null))
                            .setTextColor(resources.getColor(R.color.text_color, null)).show()
                    }
                }
            }
        }


    }

    private fun getPassword() {
        val passwordId: Long = intent.getLongExtra("passwordId", 0L)
        val passwordLabel: String? = intent.getStringExtra("passwordLabel")
        val password: String? = intent.getStringExtra("password")
        val passwordDate: String? = intent.getStringExtra("passwordDate")

        Log.d("PASSWORDIIIIIIID", passwordId.toString())

        if (passwordId != 0L) {
            pId = passwordId
        }

        showPasswordToUser(passwordLabel, password, passwordDate)
    }

    private fun showPasswordToUser(
        passwordLabel: String?,
        password: String?,
        passwordDate: String?
    ) {
        showPasswordLabel.setText(passwordLabel)
        showPassword.setText(password)
        date.text = passwordDate

    }

    private fun convertStringToDate(dateString: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")
        return try {
            LocalDate.parse(dateString, formatter)
        } catch (e: Exception) {
            null
        }
    }


}