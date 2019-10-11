package com.umb.appudit.features.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.umb.appudit.R
import com.umb.appudit.features.standard.ui.StandardActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.hide()

    }

    fun login(V: View) {
        val intent = Intent(this, StandardActivity::class.java)
        startActivity(intent)
    }
}
