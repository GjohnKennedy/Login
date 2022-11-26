package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AfterLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        val intentValue = intent.getStringExtra("Data")
        findViewById<TextView>(R.id.tv_response).apply {
            text = intentValue.toString()
        }
    }
}