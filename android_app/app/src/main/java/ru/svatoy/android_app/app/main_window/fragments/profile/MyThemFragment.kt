package ru.svatoy.android_app.app.main_window.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.databinding.FragmentMainProfileBinding
import ru.svatoy.android_app.databinding.FragmentMyThemBinding


class MyThemFragment : Fragment() {
    lateinit var binding: FragmentMyThemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyThemBinding.inflate(inflater)

        binding.backArrow4.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, MainProfileFragment.newInstance())
                .commit()
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyThemFragment()

    }
}