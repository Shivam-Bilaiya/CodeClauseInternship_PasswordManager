package com.alphacreators.passwordmanager.USER

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alphacreators.passwordmanager.Model.User
import com.alphacreators.passwordmanager.R
import com.alphacreators.passwordmanager.ViewModel.UserViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UserAccount : AppCompatActivity() {

    private val STORE_USER_ID_PREF = "store_user_id"
    private lateinit var userViewModel: UserViewModel
    private lateinit var userName : TextInputEditText
    private lateinit var userEmail : TextInputEditText
    private lateinit var updateButton : MaterialButton
    private lateinit var deleteButton : MaterialButton
    private lateinit var userDataLayout : RelativeLayout
    private var userId : Long = 0
    private lateinit var userEmailLayout : TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)

        val sharedPreferences = getSharedPreferences(STORE_USER_ID_PREF,Context.MODE_PRIVATE)
        userId = sharedPreferences.getLong("userId",0L)
        userViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(UserViewModel::class.java)
        userName = findViewById(R.id.userNameText)
        userEmail = findViewById(R.id.userEmailText)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        userDataLayout = findViewById(R.id.userDataLayout)
        userEmailLayout = findViewById(R.id.userEmailLayout)

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)


        Log.d("keeofndek",userId.toString())

        if (userId != 0L){
            userViewModel.getUserData(userId){
                if (it != null){
                    userName.setText(it.userName)
                    userEmail.setText(it.userEmail)
                    Snackbar.make(userDataLayout,"Data Received Successfully",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_green_dark,null)).setTextColor(resources.getColor(
                        R.color.text_color,null)).show()
                }else{
                    Snackbar.make(userDataLayout,"Oops Something Went Wrong",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_red_dark,null)).setTextColor(resources.getColor(
                        R.color.text_color,null)).show()
                }
            }
        }else{
            Snackbar.make(userDataLayout,"Oops Something Went Wrong ",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_green_dark,null)).setTextColor(resources.getColor(
                R.color.text_color,null)).show()
        }


        updateButton.setOnClickListener {
            updateUserData(userId)
        }

        deleteButton.setOnClickListener {
            deleteUserData(userId)
        }
    }

    private fun updateUserData(userId : Long){
        val name = userName.text.toString()
        val email = userEmail.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
        }
        else{
            if (validateEmail(email)){
                val user = User(name,email)
                userViewModel.updateUserData(userId,user){
                    if (it != null){
                        userName.setText(it.userName)
                        userEmail.setText(it.userEmail)
                        Snackbar.make(userDataLayout,"Data Update Successfully",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_green_dark,null)).setTextColor(resources.getColor(
                            R.color.text_color,null)).show()

                    }else{
                        Snackbar.make(userDataLayout,"Oops Something Went Wrong",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_red_dark,null)).setTextColor(resources.getColor(
                            R.color.text_color,null)).show()
                    }
                }
            }else{
                userEmailLayout.isErrorEnabled = true
                userEmailLayout.error = "Enter Valid Email"
            }
        }
    }

    private fun deleteUserData(userId: Long){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete Account")
        alertDialogBuilder.setMessage("Are you sure you want to delete your account?")

        alertDialogBuilder.setPositiveButton("YES") { dialog, _ ->
            userViewModel.deleteUserData(userId){
                if (it!=false){
                    Log.d("eknfekfne","kebfekfbe $it")
                    Snackbar.make(userDataLayout,"Data Deleted Successfully",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_green_dark,null)).setTextColor(resources.getColor(
                        R.color.text_color,null)).show()
                    finish()
                }else{
                    Log.d("wlnfjwkfj","kebfjbejgb $it")
                    Snackbar.make(userDataLayout,"Oops Something Went Wrong",Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(resources.getColor(android.R.color.holo_green_dark,null)).setTextColor(resources.getColor(
                        R.color.text_color,null)).show()

                }
            }
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("NO") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FFA500"))
        }

        alertDialog.show()

    }

    private fun validateEmail(userEmail : String) : Boolean{
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return emailRegex.matches(userEmail)
    }
}