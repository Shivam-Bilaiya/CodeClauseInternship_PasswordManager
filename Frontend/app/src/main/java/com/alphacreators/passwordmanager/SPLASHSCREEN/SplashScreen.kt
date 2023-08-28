package com.alphacreators.passwordmanager.SPLASHSCREEN

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.alphacreators.passwordmanager.PASSWORDS.AllPasswords
import com.alphacreators.passwordmanager.SIGNUP.MainActivity
import com.alphacreators.passwordmanager.R
import com.google.android.material.snackbar.Snackbar


class SplashScreen : AppCompatActivity() {

    private val STORE_USER_ID_PREF = "store_user_id"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var layout : ConstraintLayout
    private lateinit var imageFilterView: ImageFilterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = getSharedPreferences(STORE_USER_ID_PREF, Context.MODE_PRIVATE)
        imageFilterView = findViewById(R.id.imageFilterView)
        layout = findViewById(R.id.splash)


        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)




        animateImageFilterView(imageFilterView)




        if (!isNetworkAvailable()){
            Snackbar.make(
                layout,
                "Internet is required ",
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

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected

    }

    private fun showBiometricPrompt() {
        val biometricPrompt = createBiometricPrompt()
        val promptInfo = createPromptInfo()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun createBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(this)
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                finish()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                if(checkUserAccountExist()){
                    startActivity(Intent(this@SplashScreen, AllPasswords::class.java))
                }else{
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                }
            }

            override fun onAuthenticationFailed() {
            }
        }

        return BiometricPrompt(this, executor, callback)
    }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock Cipher")
            .setSubtitle("Enter phone screen lock pattern,PIN,password or fingerprint")
            .setDeviceCredentialAllowed(true)
            .build()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun checkUserAccountExist() : Boolean{
        val userId : Long = sharedPreferences.getLong("userId",-1)
        return userId != -1L
    }


    private fun animateImageFilterView(view: ImageFilterView) {
        view.translationY = 500f // Initial position off the screen
        view.alpha = 0f // Start with zero opacity

        view.animate()
            .translationY(0f) // Animate to original Y position
            .alpha(1f) // Fade in
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(1000)
            .withStartAction {
                // Log start of animation
                Log.d("Animation", "Animation started")
            }
            .withEndAction {
                // Log end of animation
                Log.d("Animation", "Animation ended")
                val biometricManager = BiometricManager.from(this)
                when (biometricManager.canAuthenticate()) {
                    BiometricManager.BIOMETRIC_SUCCESS -> {
                        showBiometricPrompt()
                    }
                    BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                        showToast("Biometric hardware is not available on this device.")
                    }
                    BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                        showToast("Biometric hardware is currently unavailable.")
                    }
                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                        showToast("No biometric credentials are enrolled.")
                    }
                    BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                        TODO()
                    }
                    BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                        TODO()
                    }
                    BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                        TODO()
                    }
                }
            }


            .start()
    }

}