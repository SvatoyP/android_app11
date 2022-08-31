package ru.svatoy.android_app.app.registration.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.databinding.FragmentStartRegBinding


class StartRegFragment : Fragment() {

    lateinit var binding: FragmentStartRegBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartRegBinding.inflate(inflater)

        binding.nextButton6.setOnClickListener{
            activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentsReg, NumberRegFragment.newInstance())
                .commitNow()
        }



        return binding.root
    }



    companion object {
        @JvmStatic
        fun newInstance() = StartRegFragment()
    }
}