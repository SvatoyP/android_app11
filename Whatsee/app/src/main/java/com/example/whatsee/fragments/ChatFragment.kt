package com.example.whatsee.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.whatsee.R
import com.example.whatsee.User
import com.example.whatsee.UserAdapter
import com.example.whatsee.databinding.FragmentChatBinding
import com.example.whatsee.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth


class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    lateinit var adapter: UserAdapter
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater)

        auth = Firebase.auth

        val database = Firebase.database
        val myRef = database.getReference("message")
        binding.bSend.setOnClickListener{
            myRef.child(myRef.push().key?:"any").setValue(User(auth.currentUser?.displayName, binding.edMessage.text.toString()))
        }
        onChangeListener(myRef)
        initRcView()

        return binding.root
    }

    private fun initRcView() = with(binding){
        adapter = UserAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }



    private fun onChangeListener(dRef:DatabaseReference){
        dRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                for(s in snapshot.children){
                    val user = s.getValue(User::class.java)
                    if (user != null)list.add(user)
                }
                adapter.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    companion object {
        @JvmStatic
        fun newInstance() = ChatFragment()

    }
}

