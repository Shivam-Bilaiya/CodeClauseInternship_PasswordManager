package com.alphacreators.passwordmanager.PASSWORDS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.alphacreators.passwordmanager.R
import com.google.android.material.textview.MaterialTextView

class ViewPassword : AppCompatActivity() {


    private lateinit var viewPasswordLabel : EditText
    private lateinit var viewPassword : EditText
    private lateinit var viewPasswordDate : MaterialTextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_password)

        viewPasswordLabel = findViewById(R.id.viewPasswordLabel)
        viewPassword = findViewById(R.id.viewPassword)
        viewPasswordDate = findViewById(R.id.viewPasswordDate)



        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)

        getData()
    }

    private fun getData(){
        val passwordLabel = intent.getStringExtra("viewPasswordLabel")
        val passwordDate = intent.getStringExtra("viewPasswordDate")
        val password = intent.getStringExtra("viewPassword")

        Log.d("wjfwnwf", "$passwordLabel $passwordDate $password")

        viewPasswordLabel.setText(passwordLabel)
        viewPasswordDate.text = passwordDate
        viewPassword.setText(password)
    }
}