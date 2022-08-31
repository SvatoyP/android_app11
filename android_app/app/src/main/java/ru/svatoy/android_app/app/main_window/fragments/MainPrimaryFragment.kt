package ru.svatoy.android_app.app.main_window.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.fragments.profile.MainProfileFragment
import ru.svatoy.android_app.app.registration.activity.fragments.ProblemFragment
import ru.svatoy.android_app.databinding.FragmentCodeSMSBinding
import ru.svatoy.android_app.databinding.FragmentMainPrimaryBinding

class MainPrimaryFragment : Fragment() {
    lateinit var binding: FragmentMainPrimaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPrimaryBinding.inflate(inflater)

        binding.imageView5.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, MainProfileFragment.newInstance())
                .commit()
        }




        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainPrimaryFragment()
    }
}