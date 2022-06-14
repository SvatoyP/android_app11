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
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.whatsee.MainActivity
import com.example.whatsee.R
import com.example.whatsee.databinding.ActivityMainBinding
import com.example.whatsee.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment(){
    lateinit var binding: FragmentProfileBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        auth = Firebase.auth

        Thread{
            val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
            val dIcon = BitmapDrawable(resources, bMap)
            binding.profileImage.setImageDrawable(dIcon)
        }.start()

        binding.profileName.text = auth.currentUser?.displayName

        correctProfile()

        return binding.root
    }


    private fun correctProfile(){
        binding.edProfile.setOnClickListener{
            if (binding.edProfile.text == "Редактировать") {
                binding.edOpisanie.isVisible
                binding.edurl1.isVisible
                binding.edurl2.isVisible
                binding.edurl3.isVisible
                binding.edProfile.text = "Сохранить"
            }else{
                binding.edOpisanie.isGone
                binding.edurl1.isGone
                binding.edurl2.isGone
                binding.edurl3.isGone
                binding.edProfile.text = "Редактировать"
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()

    }

}