package com.alphacreators.passwordmanager.PASSWORDS

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphacreators.passwordmanager.ADAPTER.PasswordAdapter
import com.alphacreators.passwordmanager.Model.Password
import com.alphacreators.passwordmanager.R
import com.alphacreators.passwordmanager.USER.UserAccount
import com.alphacreators.passwordmanager.ViewModel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class AllPasswords : AppCompatActivity(), PasswordItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private val passwordAdapter: PasswordAdapter = PasswordAdapter()
    private lateinit var userViewModel: UserViewModel
    private val STORE_USER_ID_PREF = "store_user_id"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_passwords)

        sharedPreferences = getSharedPreferences(STORE_USER_ID_PREF, Context.MODE_PRIVATE)

        val userId = sharedPreferences.getLong("userId", 0L)
        Log.d("kwnfkenkef", userId.toString())

        recyclerView = findViewById(R.id.passwordLayout)
        userViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(UserViewModel::class.java)
        recyclerView.adapter = passwordAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        if (userId != 0L) {
            Log.d("wjwjfeinfeinfewnkf", userId.toString())
            userViewModel.getAllUserPassword(userId)?.observe(this) {
                if (it != null) {
                    passwordAdapter.setAllPasswords(this, it.toMutableList(), this)
                }

            }
        }


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layout: LinearLayout = findViewById(R.id.allPasswordLayout)
                val position = viewHolder.adapterPosition
                val password = passwordAdapter.getListOfPasswords()[position]

                if (direction == ItemTouchHelper.LEFT) {
                    intent = Intent(applicationContext, ShowPassword::class.java)
                    intent.putExtra("passwordId", password.passwordId)
                    intent.putExtra("passwordLabel", password.passwordLabel)
                    intent.putExtra("password", password.password)
                    intent.putExtra("passwordDate", password.passwordDate)
                    startActivity(intent)
                } else if (direction == ItemTouchHelper.RIGHT) {
                    password.passwordId?.let { it ->
                        userViewModel.deleteUserPassword(it) {
                            if (it != false) {
                                Log.d("elnfekfnekfnef", "eknfkenfkenfe $it")
                                passwordAdapter.getListOfPasswords().remove(password)
                                passwordAdapter.notifyItemRemoved(position)
                                Snackbar.make(
                                    layout,
                                    "Password deleted successfully",
                                    Snackbar.ANIMATION_MODE_SLIDE
                                )
                                    .setBackgroundTint(
                                        resources.getColor(
                                            android.R.color.holo_green_dark,
                                            null
                                        )
                                    )
                                    .setTextColor(resources.getColor(R.color.text_color, null))
                                    .show()
                            } else {
                                Log.d("qf612te72y3843u", "wkbjb3jrb3jrb $it")
                                Snackbar.make(
                                    layout,
                                    "Oops Something went wrong",
                                    Snackbar.ANIMATION_MODE_SLIDE
                                )
                                    .setBackgroundTint(
                                        resources.getColor(
                                            android.R.color.holo_red_dark,
                                            null
                                        )
                                    )
                                    .setTextColor(resources.getColor(R.color.text_color, null))
                                    .show()
                            }
                        }
                    }

                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(Color.parseColor("#4CAF50"))
                    .addSwipeLeftActionIcon(R.drawable.edit)
                    .addSwipeLeftLabel("Edit Password")
                    .setIconHorizontalMargin(TypedValue.COMPLEX_UNIT_DIP, 16)
                    .addSwipeRightBackgroundColor(Color.parseColor("#FF0000"))
                    .addSwipeRightActionIcon(R.drawable.delete)
                    .addSwipeRightLabel("Delete Password")
                    .create()
                    .decorate()



                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)







        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_add_password -> {
                    val intent = Intent(this, NewPassword::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_user_account -> {
                    val intent = Intent(this, UserAccount::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logoutUser -> {
                    showLogoutDialog()
                    true
                }
                else -> false
            }
        }

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(
            this,
            R.color.background_color
        )


    }

    override fun onResume() {
        super.onResume()
        sharedPreferences = getSharedPreferences(STORE_USER_ID_PREF, Context.MODE_PRIVATE)
        val userId = sharedPreferences.getLong("userId", 0L)
        userViewModel.getAllUserPassword(userId)?.observe(this) {
            if (it != null) {
                passwordAdapter.setAllPasswords(this, it.toMutableList(), this)
            }

        }

    }

    override fun itemClick(view: View, password: Password) {
        val intent = Intent(this, ViewPassword::class.java)
        intent.putExtra("viewPasswordLabel", password.passwordLabel)
        intent.putExtra("viewPasswordDate", password.passwordDate)
        intent.putExtra("viewPassword", password.password)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
       finishAffinity()

    }

    private fun showLogoutDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Are you sure you want to logout?")
        alertDialog.setIcon(R.drawable.ic_baseline_logout_24)
        alertDialog.setPositiveButton("Yes") { _, _ ->
            sharedPreferences.edit().clear().apply()
            finishAffinity() // Close the activity or handle logout flow
        }
        alertDialog.setNegativeButton("No") { _, _ ->
        }


        val dialog = alertDialog.create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                ?.setTextColor(resources.getColor(R.color.YesBtnColor, null))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                ?.setTextColor(resources.getColor(R.color.NoBtnColor, null))
        }

        dialog.show()
    }
}







