package ru.svatoy.android_app.app.registration.activity.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.MainViewModel
import ru.svatoy.android_app.app.main_window.MainActivity
import ru.svatoy.android_app.app.registration.activity.MainRegistration
import ru.svatoy.android_app.databinding.FragmentNumberRegBinding


class NumberRegFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentNumberRegBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumberRegBinding.inflate(inflater)

        binding.imageView2.setOnClickListener{
                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentsReg, StartRegFragment.newInstance())
                    .commitNow()
        }


        binding.nextButton.setOnClickListener{
            if (binding.enterPhoneNumber.text.toString().length == 11) {
                enterNumberSend = binding.enterPhoneNumber.text.toString()
                (activity as MainRegistration).sendPhone()
                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentsReg, CodeSMSFragment.newInstance())
                    .commitNow()
            }
            else {
                binding.errorTextNumber.text = "Введите номер телефона"
            }
        }

        binding.politicConf.setOnClickListener{
            uriPolitConf()
        }



        return binding.root
    }

    fun uriPolitConf() {
        val URIPolitcConf = Intent(Intent.ACTION_VIEW, Uri.parse("https://pravoe-delo.su"))
        startActivity(URIPolitcConf)
    }


    companion object {
       var enterNumberSend:String = ""

        @JvmStatic
        fun newInstance() = NumberRegFragment()
    }
}