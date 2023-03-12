package com.app.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class itemdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemdetails)

        val title = findViewById<TextView>(R.id.title1)
        val username = findViewById<TextView>(R.id.username1)
        val password = findViewById<TextView>(R.id.password1)

        title.text = intent.getStringExtra("title");
        username.text = intent.getStringExtra("username");
        password.text = intent.getStringExtra("password");

        val imageView = findViewById<View>(R.id.get) as ImageView

        imageView.setOnClickListener{
            onBackPressed()
        }

    }
}