package ru.svatoy.whattowatch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.svatoy.whattowatch.News
import ru.svatoy.whattowatch.NewsAdapter
import ru.svatoy.whattowatch.R
import ru.svatoy.whattowatch.databinding.FragmentMainBinding
import ru.svatoy.whattowatch.databinding.FragmentProfileBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        val database = Firebase.database
        val myRef = database.getReference("News")


        onChangeListener(myRef)
        initRcView()

        return binding.root
    }

    private fun initRcView() = with(binding){
        adapter = NewsAdapter()
        rcViewNews.layoutManager = LinearLayoutManager(activity)
        rcViewNews.adapter = adapter
    }

    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<News>()
                for(s in snapshot.children){
                    val news = s.getValue(News::class.java)
                    if (news != null)list.add(news)
                }
                adapter.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}