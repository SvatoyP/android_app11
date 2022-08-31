package ru.svatoy.android_app.app

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.registration.activity.MainRegistration
import ru.svatoy.android_app.databinding.MainActivityRegistrationBinding
import ru.svatoy.android_app.databinding.SplashActivityBinding
import java.util.concurrent.TimeUnit


class SpalashActivity: AppCompatActivity() {

    lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.avAsd.alpha = 0f
        binding.avAsd.animate().setDuration(2000).alpha(1f).withEndAction{
            val intent = Intent(this, MainRegistration::class.java)
            startActivity(intent)
            finish()
        }


    }
}