package ru.svatoy.android_app.app.registration.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.databinding.FragmentEnterNameBinding
import ru.svatoy.android_app.databinding.FragmentGuidBinding


class GuidFragment : Fragment() {
    lateinit var binding: FragmentGuidBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuidBinding.inflate(inflater)

        binding.textView9.text = binding.textView9.text.toString() + EnterNameFragment.enterNameSend + "!"

        binding.nextButton4.setOnClickListener{
                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentsReg, ProblemFragment.newInstance())
                    .commitNow()
            }




        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = GuidFragment()
    }
}