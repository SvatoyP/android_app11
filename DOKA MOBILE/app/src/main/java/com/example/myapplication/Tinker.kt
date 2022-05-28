package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Tinker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tinker)
    }

    fun uritiner(view: android.view.View) {
        val URIMeepo = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCHwE8LkjTPtBkqsKmAtXSmg"))
        startActivity(URIMeepo)
    }
    fun exit(view: android.view.View) {
        val exit = Intent(this, MainActivity::class.java)
        startActivity(exit)
    }
}