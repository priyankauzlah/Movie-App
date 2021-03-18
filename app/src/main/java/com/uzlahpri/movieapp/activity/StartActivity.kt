package com.uzlahpri.movieapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uzlahpri.movieapp.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        fun getLaunchService(from: Context) = Intent(from, StartActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        btn_start.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_start -> startActivity(Intent(LoginActivity.getLaunchService(this)))
        }
    }
}