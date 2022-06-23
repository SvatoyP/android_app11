package ru.svatoy.whattowatch.fragments

import android.content.ContentValues
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.svatoy.whattowatch.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import ru.svatoy.whattowatch.*
import ru.svatoy.whattowatch.ThisUser.description
import ru.svatoy.whattowatch.ThisUser.firstURL
import ru.svatoy.whattowatch.ThisUser.secondURL
import ru.svatoy.whattowatch.ThisUser.tridURL


class ProfileFragment : Fragment(){
    lateinit var binding: FragmentProfileBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        val database = Firebase.database
        val myRef = database.getReference("News")

        auth = Firebase.auth
        //changeDiscriptoin()

        Thread{
            val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
            val dIcon = BitmapDrawable(resources, bMap)
            binding.profileImage.setImageDrawable(dIcon)
        }.start()

        binding.profileName.text = auth.currentUser?.displayName




        binding.nSend.setOnClickListener{
            myRef.child(myRef.push().key?:"any").setValue(News(auth.currentUser?.displayName, binding.addNovost.text.toString()))
            binding.addNovost.setText("")
        }


        correctProfile()

        return binding.root
    }


    private fun changeDiscriptoin(){
        val db = Firebase.firestore
        val currentEmail = auth.currentUser?.email

        val docRef = db.collection("users").document(currentEmail.toString())
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val userInfo = documentSnapshot.toObject<ThisUser>()
            Log.d("MyLog","User data updated")
        }

//        binding.url1.text = ThisUser.firstURL
//        binding.url2.text = ThisUser.secondURL
//        binding.url3.text = ThisUser.tridURL
//        binding.opisanie.text = ThisUser.description

        binding.edurl1.hint = binding.url1.text
        binding.edurl2.hint = binding.url2.text
        binding.edurl3.hint = binding.url3.text
        binding.edOpisanie.hint = binding.opisanie.text
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
                val firstURL = binding.edurl1.text
                val secondURL = binding.edurl2.text
                val tridURL = binding.edurl3.text
                val description = binding.edOpisanie.text

                val user = hashMapOf(
                    "name" to auth.currentUser?.displayName.toString(),
                    "email" to auth.currentUser?.email.toString(),
                    "avatarURL" to auth.currentUser?.photoUrl.toString(),
                    "description" to description.toString(),
                    "firstURL" to firstURL.toString(),
                    "secondURL" to secondURL.toString(),
                    "tridURL" to tridURL.toString(),
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
        //changeDiscriptoin()
    }



    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()

    }

}