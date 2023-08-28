package com.alphacreators.passwordmanager.retrofit

import com.alphacreators.passwordmanager.Model.Password
import com.alphacreators.passwordmanager.Model.User
import retrofit2.Call
import retrofit2.http.*


interface CommunicationAPI {

    // JSON - JAVASCRIPT OBJECT NOTATION

    @POST("/users/add_new_user_data")
    fun addNewUser(@Body user : User) : Call<User>

    @GET("/users/get_user_data/{userId}")
    fun getUserData(@Path("userId") userId : Long) : Call<User>

    @PUT("/users/update_user_data/{userId}")
    fun updateUserData(@Path("userId") userId: Long , @Body user : User) : Call<User>

    @DELETE("/users/delete_user_data/{userId}")
    fun deleteUserData(@Path("userId")userId: Long) : Call<Boolean>

    @POST("/users/add_new_user_password/{userId}")
    fun addNewPassword(@Path("userId") userId: Long, @Body password: Password) : Call<Password>

    @GET("/users/get_user_password_list/{userId}")
    fun getAllPassword(@Path("userId") userId: Long) : Call<List<Password>>

    @PUT("/passwords/update_user_password/{passwordId}")
    fun updateUserPassword(@Path("passwordId") passwordId : Long , @Body password: Password) : Call<Password>

    @DELETE("/passwords/delete_user_password/{passwordId}")
    fun deleteUserPassword(@Path("passwordId") passwordId: Long) : Call<Boolean>

    fun deleteAllUserPassword(@Path("userId") userId: Long ) : Call<Boolean>

    @POST("/users/login_user_data")
    fun loginUser(@Body user : User) : Call<User>


}