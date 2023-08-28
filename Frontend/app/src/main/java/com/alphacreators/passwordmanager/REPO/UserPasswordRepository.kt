package com.alphacreators.passwordmanager.REPO

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alphacreators.passwordmanager.Model.Password
import com.alphacreators.passwordmanager.Model.User
import com.alphacreators.passwordmanager.retrofit.CommunicationAPI
import com.alphacreators.passwordmanager.retrofit.Retrofit_Instance
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class UserPasswordRepository {

    private val retrofit = Retrofit_Instance.getRetrofitInstance()
    private val communicationAPI = retrofit.create(CommunicationAPI::class.java)
    private var userPasswordList  : MutableLiveData<List<Password>>? = MutableLiveData<List<Password>>()


    fun addNewUser(user: User, callback:(user : User?) -> Unit){
        Log.d("retrofit_instance",retrofit.toString())
        Log.d("COMMUNICATIONAPI",communicationAPI.toString())
        Log.d("elnfeknfe","enfknef")
        val call = communicationAPI.addNewUser(user)
        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("KWNFKWFN",data.toString())
                        callback.invoke(data)
                    }else{
                        Log.d("ekfnkefn","kengken")
                        callback.invoke(null)
                    }
                }else{
                    Log.d("kwnk","null")
                    callback.invoke(null)
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("wjbwjbedbf","klenfke")
                callback.invoke(null)
            }

        })
    }


   fun getUserData(userId : Long , callback: (user: User?) -> Unit){
       val call = communicationAPI.getUserData(userId)
       call.enqueue(object : Callback<User>{

           override fun onResponse(call: Call<User>, response: Response<User>) {
               if (response.isSuccessful){
                   val data = response.body()
                   if (data != null){
                       Log.d("KENFENFKEN",data.toString())
                       callback.invoke(data)
                   }else{
                       Log.d("kenf","kenfke")
                       callback.invoke(null)
                   }
               }else{
                   Log.d("wknfkwnf","kenfke")
                   callback.invoke(null)
               }
           }


           override fun onFailure(call: Call<User>, t: Throwable) {
               Log.d("kwndvkdv","wkdnkwndwdnw")
               callback.invoke(null)
           }

       })
   }


    fun getAllUserPasswords(userId: Long) : MutableLiveData<List<Password>>?{
        Log.d("lwndlwfnkwnf",userId.toString())
        val call = communicationAPI.getAllPassword(userId)
        call.enqueue(object : Callback<List<Password>>{

            override fun onResponse(
                call: Call<List<Password>>,
                response: Response<List<Password>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("wkhnwkn3ir",data.toString())
                        userPasswordList?.value = data
                    }else{
                        Log.d("ejfekf","elkfneknf")
                        userPasswordList?.value = null
                    }
                }else{
                    Log.d("ekfnekfne","elfnel")
                    userPasswordList?.value = null
                }
            }


            override fun onFailure(call: Call<List<Password>>, t: Throwable) {
                Log.d("elkfnekn","leflefnlfn")
                userPasswordList?.value = null
            }

        })

        return userPasswordList
    }

    fun addUserPassword(userId : Long, password: Password, callback: (password: Password?) -> Unit){
        Log.d("elfelfnelfnelgne","${password.passwordDate} $userId")
        val call = communicationAPI.addNewPassword(userId,password)
        call.enqueue(object : Callback<Password>{

            override fun onResponse(call: Call<Password>, response: Response<Password>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("wknwkfnwk",data.toString())
                        callback.invoke(data)
                    }else{
                        Log.d("eknfkefnekn","knfekn")
                        callback.invoke(null)
                    }
                }else{
                    Log.d("elnfkenfefe","eknfkenfe")
                    callback.invoke(null)
                }
            }


            override fun onFailure(call: Call<Password>, t: Throwable) {
                Log.d("ekbfjefbjfbe","kenkefnkefn")
                callback.invoke(null)
            }

        })
    }

    fun updateUserPassword(passwordId : Long, password: Password, callback: (password: Password?) -> Unit){
        Log.d("knfiefifhe",passwordId.toString())
        Log.d("wknkwnfek",password.toString())
        val call = communicationAPI.updateUserPassword(passwordId, password)
        call.enqueue(object : Callback<Password>{

            override fun onResponse(call: Call<Password>, response: Response<Password>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("kenfkenfe","lenflnef")
                        callback.invoke(data)
                    }else{
                        Log.d("kenfkenfe1","lenflnef")
                        callback.invoke(null)
                    }
                }else{
                    Log.d("kenfkenfe2","lenflnef")
                    callback.invoke(null)
                }
            }


            override fun onFailure(call: Call<Password>, t: Throwable) {
                Log.d("kenfkenfe3","lenflnef")
                callback.invoke(null)
            }

        })
    }

    fun deleteUserPassword(passwordId: Long,callback: (result: Boolean?) -> Unit){
        val call = communicationAPI.deleteUserPassword(passwordId)
        call.enqueue(object : Callback<Boolean>{

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != false){
                        Log.d("nfpwmwfwjb",data.toString())
                        callback.invoke(true)
                    }else{
                        Log.d("nfpwmwfw1","kwnfknfkefkwndw")
                        callback.invoke(false)
                    }
                }else{
                    Log.d("nfpwmwfw2","kwnfknfkefkwndw")
                    callback.invoke(false)
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d("nfpwmwfw3","kwnfknfkefkwndw")
                callback.invoke(false)
            }

        })
    }

    fun updateUser(userId : Long, user: User, callback: (user: User?) -> Unit){
        val call = communicationAPI.updateUserData(userId,user)
        call.enqueue(object : Callback<User>{

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("yuio-iokbik",data.toString())
                        callback.invoke(data)
                    }else{
                        Log.d("yuio-iokbi1k1","ldwlnfkwnfke")
                        callback.invoke(null)
                    }
                }else{
                    Log.d("yuio-iokbi1k2","ldwlnfkwnfke")
                    callback.invoke(null)
                }
            }


            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("yuio-iokbi1k3","ldwlnfkwnfke")
                callback.invoke(null)
            }

        })
    }

    fun deleteUser(userId : Long,callback: (result: Boolean?) -> Unit){
        val call = communicationAPI.deleteUserData(userId)
        call.enqueue(object : Callback<Boolean>{

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("iuuytr",data.toString())
                        callback.invoke(true)
                    }else{
                        Log.d("iuuytr1","lnflenfe")
                        callback.invoke(false)
                    }
                }else{
                    Log.d("iuuytr2","lnflenfe")
                    callback.invoke(false)
                }
            }


            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d("iuuytr3","lnflenfe")
                callback.invoke(false)
            }

        })
    }

    fun deleteAllUserPasswords(userId: Long , callback: (result: Boolean?) -> Unit){
        val call = communicationAPI.deleteAllUserPassword(userId)
        call.enqueue(object : Callback<Boolean>{

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        Log.d("yuryreiyriutru",data.toString())
                        callback.invoke(true)
                    }else{
                        Log.d("yuryreiyriutru1","lnflenfe")
                        callback.invoke(false)
                    }
                }else{
                    Log.d("yuryreiyriutru2","lnflenfe")
                    callback.invoke(false)
                }
            }


            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d("yuryreiyriutru3","lnflenfe")
                callback.invoke(false)
            }

        })
    }

    fun loginUserAccount(user: User, callback: (user: User?) -> Unit){
        val call = communicationAPI.loginUser(user)
        call.enqueue(object:Callback<User>{

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val data = response.body()
                        Log.d("wknfkwnfw111",data.toString())
                        callback.invoke(data)
                    }else{
                        Log.d("wknfkwnfw1113","l;fkwnfkwn")
                        callback.invoke(null)
                    }
                }else{
                    Log.d("wknfkwnfw1114","wfwifbwbfejb")
                    callback.invoke(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
               Log.d("wknfkwnfw1115","lngkewngwekgn")
                callback.invoke(null)
            }

        })
    }



}


