package ru.svatoy.android_app.app.registration.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.MainViewModel
import ru.svatoy.android_app.app.MainViewModelFactory
import ru.svatoy.android_app.app.registration.activity.fragments.StartRegFragment
import ru.svatoy.android_app.app.retrofit.data.PostAuthNumber
import ru.svatoy.android_app.app.retrofit.data.PostOAuth
import ru.svatoy.android_app.app.retrofit.data.PostUserName
import ru.svatoy.android_app.app.retrofit.data.repository.Repositiry
import ru.svatoy.android_app.databinding.MainActivityRegistrationBinding

class MainRegistration:AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: MainActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentsReg, StartRegFragment.newInstance())
            .commit()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


    }

    fun sendPhone(){
        val repository = Repositiry()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val postAuthNumber = PostAuthNumber()
        viewModel.pushPost(postAuthNumber)
        viewModel.myResponseNumber.observe(this, Observer{ responseNumber ->
            if (responseNumber.isSuccessful){
                val tokenDirty = responseNumber.body().toString()
                Log.d("Main", tokenDirty)
                x_auth_token = tokenDirty.replace("x_auth_token=", "")
                Log.d("Main", x_auth_token)
                Log.d("Main", responseNumber.code().toString())
                Log.d("Main", responseNumber.message())
            }else{
                Toast.makeText(this, responseNumber.code(), Toast.LENGTH_LONG).show()
            }
        })
    }

    fun sendSMS(){
        val repository = Repositiry()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val postOAuth = PostOAuth()
        viewModel.pushSMS(postOAuth)
        viewModel.myResponseSMS.observe(this, Observer{ responseSMS ->
            if (responseSMS.isSuccessful){
                Log.d("Main", responseSMS.body().toString())
                Log.d("Main", responseSMS.code().toString())
                Log.d("Main", responseSMS.message())
            }else{
                Toast.makeText(this, responseSMS.code(), Toast.LENGTH_LONG).show()
            }
        })
    }


    fun sendName(){
        val repository = Repositiry()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val postUserName = PostUserName()
        viewModel.pushName(postUserName)
        viewModel.myResponseName.observe(this, Observer{ responseName ->
            if (responseName.isSuccessful){
                Log.d("Main", responseName.body().toString())
                Log.d("Main", responseName.code().toString())
                Log.d("Main", responseName.message())
            }else{
                Toast.makeText(this, responseName.code(), Toast.LENGTH_LONG).show()
            }
        })
    }




    companion object {
        var x_auth_token:String = ""
        var user_id:Int = 0

    }
}
