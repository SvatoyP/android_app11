package ru.svatoy.android_app.app.main_window.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.fragments.MainPrimaryFragment
import ru.svatoy.android_app.app.main_window.fragments.helpFragments.MainGetHelpFragment
import ru.svatoy.android_app.databinding.FragmentMainPrimaryBinding
import ru.svatoy.android_app.databinding.FragmentMainProfileBinding


class MainProfileFragment : Fragment() {
    lateinit var binding: FragmentMainProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainProfileBinding.inflate(inflater)

        curName = binding.profileName.text.toString()


        binding.imageView8.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, ProfileNameEditFragment.newInstance())
                .commit()
        }

        binding.mythemas.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, MyThemFragment.newInstance())
                .commit()
        }

        binding.needHelp.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, MainGetHelpFragment.newInstance())
                .commit()
        }

        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = MainProfileFragment()

        var curName:String = ""
    }
}