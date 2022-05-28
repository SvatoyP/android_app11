package com.example.whatsee.fragments

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import com.example.whatsee.MainActivity
import com.example.whatsee.R
import com.example.whatsee.databinding.ActivityMainBinding
import com.example.whatsee.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(){
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root

    }



    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()

    }

}