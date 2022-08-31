package ru.svatoy.android_app.app.main_window.fragments.helpFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.MainActivity
import ru.svatoy.android_app.app.main_window.fragments.MainPrimaryFragment
import ru.svatoy.android_app.databinding.FragmentMainGetHelpBinding

class MainGetHelpFragment : Fragment() {
    lateinit var binding: FragmentMainGetHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainGetHelpBinding.inflate(inflater)

        binding.getHelpButton1.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, GetHelpFragmentUserData.newInstance())
                .commit()
        }

        binding.backArrow4.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainGetHelpFragment()
    }
}