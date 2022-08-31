package ru.svatoy.android_app.app.main_window.fragments.helpFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.MainActivity
import ru.svatoy.android_app.app.main_window.dialogs.ExitThemeDialogFragment
import ru.svatoy.android_app.app.main_window.dialogs.GotNewHelp
import ru.svatoy.android_app.app.main_window.fragments.MainPrimaryFragment
import ru.svatoy.android_app.app.registration.activity.fragments.EnterNameFragment
import ru.svatoy.android_app.databinding.FragmentGetHelpUserDataBinding
import ru.svatoy.android_app.databinding.FragmentMainGetHelpBinding


class GetHelpFragmentUserData : Fragment() {
    lateinit var binding: FragmentGetHelpUserDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetHelpUserDataBinding.inflate(inflater)
        val dialogFragment = GotNewHelp()

        binding.getHelpButton2.setOnClickListener{
            if (binding.summ.text.toString().isNotEmpty() && binding.getProblem.text.toString().isNotEmpty()) {
                binding.textInputLayoutGetProblen.error = null
                binding.textInputLayoutSumm.error = null
                dialogFragment.show(requireActivity().supportFragmentManager, GotNewHelp.TAG)}
            else{
                binding.errorTextNumber4.text = "Поле обязательно для заполнения"
                binding.errorTextNumber5.text = "Поле обязательно для заполнения"
                binding.textInputLayoutGetProblen.error = "Поле обязательно для заполнения"
                binding.textInputLayoutSumm.error = "Поле обязательно для заполнения"
            }
        }

        binding.backArrow4.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = GetHelpFragmentUserData()
    }
}