package ru.svatoy.android_app.app.registration.activity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.svatoy.android_app.app.main_window.CreateThemeActivity
import ru.svatoy.android_app.app.main_window.MainActivity
import ru.svatoy.android_app.app.registration.activity.MainRegistration
import ru.svatoy.android_app.databinding.FragmentProblemBinding


class ProblemFragment : Fragment() {
   lateinit var binding: FragmentProblemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProblemBinding.inflate(inflater)

        binding.nextButton5.setOnClickListener{
                val intent = Intent(activity, CreateThemeActivity::class.java)
                startActivity(intent)
        }

        binding.skipButton.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }

        companion object {
            @JvmStatic
            fun newInstance() = ProblemFragment()
        }
    }