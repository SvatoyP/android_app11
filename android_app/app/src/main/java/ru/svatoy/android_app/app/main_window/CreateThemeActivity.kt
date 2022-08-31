package ru.svatoy.android_app.app.main_window

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import ru.svatoy.android_app.app.main_window.dialogs.ExitThemeDialogFragment
import ru.svatoy.android_app.app.main_window.dialogs.GotNewThemeDialog
import ru.svatoy.android_app.databinding.ActivityCreateThemeBinding
import ru.svatoy.android_app.databinding.ActivityMainBinding

class CreateThemeActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countSymbols()
        onBackPressed()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.enterProblem.setOnClickListener {
            countSymbols()
        }
        binding.enterProblem1.setOnClickListener {
            countSymbols()
        }
        binding.conlay.setOnClickListener{
            countSymbols()
        }


        onBackPressed()

        binding.backArrow2.setOnClickListener{
            countSymbols()
            onBackPressed()
            val dialogFragment = ExitThemeDialogFragment()
            dialogFragment.show(supportFragmentManager, ExitThemeDialogFragment.TAG)
        }

    }
    fun countSymbols() {
        val string = binding.enterProblem.text.toString()

        binding.textView19.text = string.codePointCount(0, string.length).toString() + "/1000 символов"

    }
    override fun onBackPressed(){
        countSymbols()
    }

}