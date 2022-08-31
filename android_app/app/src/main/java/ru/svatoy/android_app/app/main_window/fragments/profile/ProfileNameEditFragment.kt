package ru.svatoy.android_app.app.main_window.fragments.profile

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.fragments.profile.MainProfileFragment.Companion.curName
import ru.svatoy.android_app.databinding.FragmentProfileNameEditBinding


class ProfileNameEditFragment : Fragment() {
    lateinit var binding: FragmentProfileNameEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileNameEditBinding.inflate(inflater)

        binding.enterName.hint = curName

        binding.nextButton10.setOnClickListener{
            if (binding.enterName.text.toString().length < 31 and binding.enterName.text.toString().length != null) {
                newName = binding.enterName.text.toString()
                Toast.makeText(activity,"Новое имя сохранено", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragments, MainProfileFragment.newInstance())
                    .commit()
            }else {
                binding.errorTextNumber6.text = "Поле обязательно для заполнения"
            }
        }

        binding.backArrow15.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragments, MainProfileFragment.newInstance())
                .commit()
        }
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = ProfileNameEditFragment()
        var newName:String = ""
    }
}