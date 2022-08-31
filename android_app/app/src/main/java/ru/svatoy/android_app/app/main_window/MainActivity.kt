package ru.svatoy.android_app.app.main_window

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.fragments.helpFragments.MainGetHelpFragment
import ru.svatoy.android_app.app.main_window.fragments.MainPrimaryFragment
import ru.svatoy.android_app.app.main_window.fragments.NotificationsFragment
import ru.svatoy.android_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragments, MainPrimaryFragment.newInstance())
            .commit()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.bottomNavView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.mainMenuItem -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFragments, MainPrimaryFragment.newInstance())
                        .commit()
                }
                R.id.createThemeItem -> {
                    val intent = Intent(this, CreateThemeActivity::class.java)
                    startActivity(intent)
                }
                R.id.helpItem ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFragments, MainGetHelpFragment.newInstance())
                        .commit()
                }
                R.id.notificationsItem ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFragments, NotificationsFragment.newInstance())
                        .commit()
                }
            }
            true
        }


    }
}