package com.alphacreators.passwordmanager.PASSWORDS

import android.view.View
import com.alphacreators.passwordmanager.Model.Password

interface PasswordItemClickListener {
    fun itemClick(view : View, password: Password)
}