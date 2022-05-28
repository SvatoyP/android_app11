package com.example.whatsee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.whatsee.databinding.ActivityLoginBinding
import com.example.whatsee.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var btSingInButton: SignInButton
    lateinit var mGoogleSingInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        btSingInButton = findViewById(R.id.signInButton)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSingInClient = GoogleSignIn.getClient(this, gso)
        btSingInButton.setOnClickListener(View.OnClickListener { })
    }
}