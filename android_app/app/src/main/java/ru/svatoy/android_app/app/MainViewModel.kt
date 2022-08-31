package ru.svatoy.android_app.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.svatoy.android_app.app.retrofit.data.*
import ru.svatoy.android_app.app.retrofit.data.repository.Repositiry

class MainViewModel(private val repositiry: Repositiry): ViewModel() {

    val myResponseNumber: MutableLiveData<Response<PostAuthNumberResponse>> = MutableLiveData()
    val myResponseSMS: MutableLiveData<Response<PostOAuthResponse>> = MutableLiveData()
    val myResponseName: MutableLiveData<Response<PostUserNameResponse>> = MutableLiveData()

    fun pushPost(postAuthNumber: PostAuthNumber){
        viewModelScope.launch {
            val responseNumber:Response<PostAuthNumberResponse> = repositiry.pushPost(postAuthNumber)
            myResponseNumber.value = responseNumber
        }
    }

    fun pushSMS(postOAuth: PostOAuth){
        viewModelScope.launch {
            val responseSMS: Response<PostOAuthResponse> = repositiry.pushSMS(postOAuth)
            myResponseSMS.value = responseSMS
        }
    }

    fun pushName(postUserName: PostUserName){
        viewModelScope.launch {
            val responseName: Response<PostUserNameResponse> = repositiry.pushName(postUserName)
            myResponseName.value = responseName
        }
    }
}