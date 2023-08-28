package com.alphacreators.passwordmanager.ADAPTER

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alphacreators.passwordmanager.Model.Password
import com.alphacreators.passwordmanager.PASSWORDS.PasswordItemClickListener
import com.alphacreators.passwordmanager.R
import com.google.android.material.textview.MaterialTextView

class PasswordAdapter : RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder>() {

    private var listOfAllPasswords = mutableListOf<Password>()
    private lateinit var context: Context
    private lateinit var passwordItemClickListener: PasswordItemClickListener




    inner class PasswordViewHolder(itemView: View,passwordItemClickListener: PasswordItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val passwordLabel: TextView = itemView.findViewById(R.id.passwordLabelLayout)
        val password: TextView = itemView.findViewById(R.id.passwordTextLayout)
        val userPasswordLayout : RelativeLayout = itemView.findViewById(R.id.userPasswordLayout)
        val passwordDate : MaterialTextView = itemView.findViewById(R.id.passwordTextDate)


        init {
            userPasswordLayout.setOnClickListener {
                val index = adapterPosition
                passwordItemClickListener.itemClick(userPasswordLayout,listOfAllPasswords[index])
            }



        }



    }


    fun setAllPasswords(
        context: Context,
        listOfAllPasswords: MutableList<Password>,
        passwordItemClickListener: PasswordItemClickListener

    ) {
        this.listOfAllPasswords = listOfAllPasswords
        Log.d("EFNEFIKFNEKFN",listOfAllPasswords.toString())
        this.context = context
        this.passwordItemClickListener = passwordItemClickListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        Log.d("qwertyui","kenfknefk")
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.password_layout, parent, false)
        return PasswordViewHolder(itemView,passwordItemClickListener)
    }


    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        Log.d("fenfkenfe","lemlnfkenf")
        val userPassword = listOfAllPasswords[position]
        Log.d("ekfekf;w e",userPassword.toString())
        holder.passwordLabel.text = userPassword.passwordLabel
        holder.password.text = "******"
        holder.passwordDate.text = userPassword.passwordDate

        holder.userPasswordLayout.setOnClickListener {
            passwordItemClickListener.itemClick(holder.userPasswordLayout,userPassword)
        }









    }

    override fun getItemCount(): Int {
        Log.d("lmfleff ",listOfAllPasswords.size.toString())
        return listOfAllPasswords.size
    }


    fun getListOfPasswords() : MutableList<Password>{
        return listOfAllPasswords
    }



}