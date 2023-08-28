package com.alphacreators.passwordmanager.Model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

data class Password(
    @SerializedName("passwordLabel") var passwordLabel: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("passwordDate") var passwordDate : String? = null
){
    @SerializedName("passwordId") var passwordId: Long? = 0
    override fun toString(): String {
        return "Password(passwordLabel=$passwordLabel, password=$password, passwordDate=$passwordDate, passwordId=$passwordId)"
    }


}