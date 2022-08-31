package ru.svatoy.android_app.app.registration.activity.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.registration.activity.MainRegistration
import ru.svatoy.android_app.databinding.FragmentEnterNameBinding


class EnterNameFragment : Fragment() {
    lateinit var binding: FragmentEnterNameBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterNameBinding.inflate(inflater)



        binding.nextButton3.setOnClickListener{
            if (binding.enterName.text.toString().length < 30 and binding.enterName.text.toString().length != null) {
                enterNameSend = binding.enterName.text.toString()
                //(activity as MainRegistration).sendName()
                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentsReg, GuidFragment.newInstance())
                    .commitNow()
            }
            else {
                binding.errorTextNumber3.text = "Введите ваше имя"
            }
        }



            return binding.root
        }

        companion object {
            var enterNameSend:String = ""

            @JvmStatic
            fun newInstance() = EnterNameFragment()
        }
    }