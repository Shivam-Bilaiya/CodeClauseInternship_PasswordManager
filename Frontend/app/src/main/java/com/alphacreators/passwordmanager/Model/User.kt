package com.alphacreators.passwordmanager.Model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("userEmail") var userEmail: String? = null
){
    @SerializedName("userId") var userId : Long? = 0
}