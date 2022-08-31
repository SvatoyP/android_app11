package ru.svatoy.android_app.app.main_window.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.MainActivity
import ru.svatoy.android_app.databinding.FragmentMainPrimaryBinding
import ru.svatoy.android_app.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {
    lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater)


        binding.backArrow7.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }
}