package com.alphacreators.passwordmanager.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit_Instance {

    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.7:8081").addConverterFactory(
            GsonConverterFactory.create(Gson())).build()
    }
}


/*
* package com.alphacreators.movieproject.retrofit

import android.util.Log
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    init {
        Log.d("CHECKING", "RUNNING OR NOT")
        println("INIT METHOD IS EXECUTED")
    }
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.7:9090")
            .addConverterFactory(GsonConverterFactory.create(Gson())).build()
    }

}*/