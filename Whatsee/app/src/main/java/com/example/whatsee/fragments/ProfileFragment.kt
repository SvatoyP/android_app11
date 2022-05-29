package com.example.whatsee.fragments

import android.graphics.drawable.BitmapDrawable
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
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment(){
    lateinit var binding: FragmentProfileBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
        val dIcon = BitmapDrawable(resources, bMap)
        binding.profileImage.setImageDrawable(dIcon)

        binding.textView2.text = auth.currentUser?.displayName



        return binding.root

    }



    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()

    }

}