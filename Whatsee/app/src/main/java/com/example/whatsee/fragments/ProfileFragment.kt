package com.example.whatsee.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
                binding.edOpisanie.isVisible = true
                binding.edurl1.isVisible = true
                binding.edurl2.isVisible = true
                binding.edurl3.isVisible = true
                binding.edProfile.text = "Сохранить"
            }else{
                binding.edOpisanie.isGone = true
                binding.edurl1.isGone = true
                binding.edurl2.isGone = true
                binding.edurl3.isGone = true
                binding.edProfile.text = "Редактировать"
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()

    }

}