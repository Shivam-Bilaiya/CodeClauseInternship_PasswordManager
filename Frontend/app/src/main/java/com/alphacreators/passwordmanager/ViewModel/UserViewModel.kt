package com.alphacreators.passwordmanager.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alphacreators.passwordmanager.Model.Password
import com.alphacreators.passwordmanager.Model.User
import com.alphacreators.passwordmanager.REPO.UserPasswordRepository

class UserViewModel : ViewModel() {


    private val userPasswordRepository: UserPasswordRepository? = UserPasswordRepository()
    private var userPasswordList: MutableLiveData<List<Password>>? =
        MutableLiveData<List<Password>>()


    fun addNewUser(user: User, callback: (user: User?) -> Unit) {
        Log.d("leflenfef", "lfnlene")
        userPasswordRepository?.addNewUser(user) {
            if (it != null) {
                Log.d("eklfkenf", "elflen")
                callback.invoke(it)
            } else {
                Log.d("enfkenfke", "elnflenf")
                callback.invoke(null)
            }
        }
    }

    fun getUserData(userId: Long, callback: (user: User?) -> Unit) {
        userPasswordRepository?.getUserData(userId) {
            if (it != null) {
                Log.d("kefnekfmw;dk", "wlnfwlnwknwkd")
                callback.invoke(it)
            } else {
                Log.d("knfkefkebf", "nkenfkenfken")
                callback.invoke(null)
            }
        }
    }


    fun addUserPassword(userId: Long, password: Password, callback: (password: Password?) -> Unit) {
        userPasswordRepository?.addUserPassword(userId, password) {
            if (it != null) {
                Log.d("elnlenfeln", "lenflenf")
                callback.invoke(it)
            } else {
                Log.d("wkfbwkfbwjf", "pfjoeolefne")
                callback.invoke(null)
            }
        }
    }


    fun getAllUserPassword(userId: Long): MutableLiveData<List<Password>>? {
        Log.d("MY VANSHIKA USER ID IS THIS ", userId.toString())

        return if (userPasswordRepository?.getAllUserPasswords(userId) != null) {
            Log.d(";lwkdlwfjlwj", userPasswordRepository.getAllUserPasswords(userId).toString())
            userPasswordRepository.getAllUserPasswords(userId);
        } else {
            null
        }

    }


    fun updatePassword(
        passwordId: Long,
        password: Password,
        callback: (password: Password?) -> Unit
    ) {
        userPasswordRepository?.updateUserPassword(passwordId, password) {
            if (it != null) {
                Log.d("knwk1nwk1wn", it.toString())
                callback.invoke(it)
            } else {
                Log.d("wldnwld", "efnelfm")
                callback.invoke(null)
            }
        }
    }

    fun deleteUserPassword(passwordId: Long, callback: (result: Boolean?) -> Unit) {
        userPasswordRepository?.deleteUserPassword(passwordId) {
            if (it != false) {
                Log.d("wihihieinien1", it.toString())
                callback.invoke(true)
            } else {
                Log.d("wihihieinien2", "kwnek")
                callback.invoke(false)
            }
        }
    }

    fun updateUserData(userId: Long, user: User, callback: (user: User?) -> Unit){
        userPasswordRepository?.updateUser(userId, user){
            if (it != null){
                Log.d("UPDATE_USER_DATA",it.toString())
                callback.invoke(it)
            }else{
                Log.d("UN_UPDATE_USER_DATA","kenfkefne")
                callback.invoke(null)
            }
        }
    }

    fun deleteUserData(userId: Long , callback: (result: Boolean?) -> Unit){
        userPasswordRepository?.deleteUser(userId){
            if (it!=null){
                Log.d("dlhwidhwidh","lelejf")
                callback.invoke(true)
            }else{
                Log.d("lenfekfnef","elnfklengf")
                callback.invoke(false)
            }
        }
    }


    fun loginUserAccount(user: User, callback: (user: User?) -> Unit){
        userPasswordRepository?.loginUserAccount(user){
            if (it != null){
                Log.d("lejfeknewknfewk",it.toString())
                callback.invoke(it)
            }else{
                Log.d(";jflrjlejlerwl;","e;flefjelf")
                callback.invoke(null)
            }
        }
    }
}