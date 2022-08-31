package ru.svatoy.android_app.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.svatoy.android_app.app.retrofit.data.repository.Repositiry

class MainViewModelFactory(private val repositiry: Repositiry):ViewModelProvider.Factory {

    override fun <T: ViewModel?> create(modelClass: Class<T>):T{
        return MainViewModel(repositiry) as T
    }

}