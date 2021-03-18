package com.uzlahpri.movieapp.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uzlahpri.movieapp.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_start.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firebaseAuth: FirebaseAuth

    companion object {
        fun getLaunchService(from: Context) = Intent(from, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()
        btn_login.setOnClickListener(this)
        tv_login_forgot.setOnClickListener(this)
        tv_login_create.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.tv_login_forgot -> startActivity(Intent(ResetActivity.getLaunchService(this)))
            R.id.tv_login_create -> startActivity(Intent(CreateAccountActivity.getLaunchService(this)))
            R.id.btn_login -> loginEmailPass()
        }
    }

    private fun loginEmailPass() {
        val email = et_login_email.text.toString()
        val password = et_login_password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Insert a complete data", Toast.LENGTH_SHORT).show()
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    startActivity(MainActivity.getLaunchService(this))
                    return@addOnCompleteListener
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
                progress.hide()
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

    }
}