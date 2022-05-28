package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Stray : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stray)
    }

    fun uriStray(view: android.view.View) {
        val URIMeepo = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitch.tv/stray228"))
        startActivity(URIMeepo)
    }
    fun exit(view: android.view.View) {
        val exit = Intent(this, MainActivity::class.java)
        startActivity(exit)
    }
}