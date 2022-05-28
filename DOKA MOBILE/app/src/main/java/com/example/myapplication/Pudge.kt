package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Pudge : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pudge)


    }


    fun uripudge(view: android.view.View) {
        val URIPudge = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/PudgeMad/featured"))
        startActivity(URIPudge)
    }

    fun exit(view: android.view.View) {
        val exit = Intent(this, MainActivity::class.java)
        startActivity(exit)
    }
}