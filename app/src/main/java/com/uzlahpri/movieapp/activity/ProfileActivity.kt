package com.uzlahpri.movieapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uzlahpri.movieapp.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        fun getLaunchService(from: Context) = Intent(from, ProfileActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        btn_logout.setOnClickListener(this)
        ib_back_profile.setOnClickListener(this)
        supportActionBar?.hide()

    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_logout -> logOut()
            R.id.ib_back_profile -> startActivity(Intent(MainActivity.getLaunchService(this)))
        }
    }

    private fun logOut() {
        startActivity(Intent(LoginActivity.getLaunchService(this)))
        FirebaseAuth.getInstance().signOut()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(MainActivity.getLaunchService(this)))
    }
}