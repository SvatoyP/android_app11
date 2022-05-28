package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Invoker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoker)
    }

    fun uriinvoker(view: android.view.View) {
        val URIInvoker = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCBmZnRuK2cbdijnJFTRp3Ug"))
        startActivity(URIInvoker)
    }
    fun exit(view: android.view.View) {
        val exit = Intent(this, MainActivity::class.java)
        startActivity(exit)
    }
}