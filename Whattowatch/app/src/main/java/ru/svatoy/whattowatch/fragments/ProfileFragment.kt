package ru.svatoy.whattowatch.fragments

import android.content.ContentValues
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import ru.svatoy.whattowatch.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import ru.svatoy.whattowatch.ThisUser


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



        changeDiscriptoin()
        correctProfile()

        return binding.root
    }


    private fun changeDiscriptoin(){
        val db = Firebase.firestore
        val currentEmail = auth.currentUser?.email


        val docRef = db.collection("users").document(currentEmail.toString())
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val userObj = documentSnapshot.toObject<ThisUser>()
        }

        binding.url1.text = ThisUser.firstURL
        binding.url2.text = ThisUser.secondURL
        binding.url3.text = ThisUser.tridURL
        binding.opisanie.text = ThisUser.description
    }

    private fun correctProfile(){
        val db = Firebase.firestore
        val currentEmail = auth.currentUser?.email

        binding.edProfile.setOnClickListener{

            if (binding.edProfile.text == "Редактировать") {
                binding.edOpisanie.isVisible = true
                binding.edurl1.isVisible = true
                binding.edurl2.isVisible = true
                binding.edurl3.isVisible = true
                binding.edProfile.text = "Сохранить"
            }else{
                val firstURL = binding.url1.text
                val secondURL = binding.url2.text
                val tridURL = binding.url3.text
                val description = binding.opisanie.text

                val user = hashMapOf(
                    "name" to auth.currentUser?.displayName.toString(),
                    "email" to auth.currentUser?.email.toString(),
                    "avatarURL" to auth.currentUser?.photoUrl.toString(),
                    "description" to description,
                    "firstURL" to firstURL,
                    "secondURL" to secondURL,
                    "tridURL" to tridURL,
                )

                db.collection("users").document(currentEmail.toString())
                    .set(user)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }

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