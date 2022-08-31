package ru.svatoy.android_app.app.registration.activity.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.registration.activity.MainRegistration
import ru.svatoy.android_app.databinding.FragmentCodeSMSBinding


class CodeSMSFragment : Fragment() {
    lateinit var binding: FragmentCodeSMSBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeSMSBinding.inflate(inflater)



        binding.textView6.text = binding.textView6.text.toString() + NumberRegFragment.enterNumberSend


        binding.pinView.setOnClickListener {
            binding.nextButton2.isVisible = true
            binding.pinView.requestFocus()
            val inputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }


        binding.imageView3.setOnClickListener{
            activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentsReg, NumberRegFragment.newInstance())
                .commitNow()
        }

        binding.nextButton2.setOnClickListener{
            if (binding.pinView.text.toString().length == 6) {
                enterSMSCode = binding.pinView.text.toString()
                (activity as MainRegistration).sendSMS()
                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentsReg, EnterNameFragment.newInstance())
                    .commitNow()
            }
            else {
                binding.errorTextNumber2.text = "Введите код из смс"
            }
        }

        binding.politicConf2.setOnClickListener{
            uriPolitConf()
        }



        return binding.root
    }

    fun uriPolitConf() {
        val URIPolitcConf = Intent(Intent.ACTION_VIEW, Uri.parse("https://pravoe-delo.su"))
        startActivity(URIPolitcConf)
    }


    companion object {
        var enterSMSCode:String = ""

        @JvmStatic
        fun newInstance() = CodeSMSFragment()
    }
}