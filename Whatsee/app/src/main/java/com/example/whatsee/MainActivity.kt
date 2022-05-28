package com.example.whatsee

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.whatsee.databinding.ActivityMainBinding
import com.example.whatsee.fragments.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, MainFragment.newInstance())
            .commit()





        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Основное меню"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)






        binding.mainMenuButton.setOnClickListener {
            supportActionBar?.title = "Основное меню"
            openFrag(MainFragment.newInstance())
            android.R.id.home
            binding.apply {
                drawer.closeDrawer(GravityCompat.START)
            }
        }

        binding.subButton.setOnClickListener {
            supportActionBar?.title = "Подписки"
            openFrag(SubFragment.newInstance())
            android.R.id.home
            binding.apply {
                drawer.closeDrawer(GravityCompat.START)
            }

        }

        binding.profileButton.setOnClickListener {
            supportActionBar?.title = "Профиль"
            openFrag(ProfileFragment.newInstance())
            android.R.id.home
            binding.apply {
                drawer.closeDrawer(GravityCompat.START)
            }
        }
        binding.imageAvatar.setOnClickListener {
            supportActionBar?.title = "Профиль"
            openFrag(ProfileFragment.newInstance())
            android.R.id.home
            binding.apply {
                drawer.closeDrawer(GravityCompat.START)
            }
        }

    }

    private fun openFrag(f: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, f)
            .commit()
    }




    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.main_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                binding.apply {
                    if (drawer.isDrawerOpen(GravityCompat.START)){
                        drawer.closeDrawer(GravityCompat.START)
                    } else {
                        drawer.openDrawer(GravityCompat.START)
                    }
                }
            }




        }
        return true
    }
}



