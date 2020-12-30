package com.example.movieandme2.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieandme2.domain.entity.User
import com.example.movieandme2.domain.usecase.CreateUserUseCase
import com.example.movieandme2.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){
    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //createUserUseCase.invoke(User("test"))
            val user: User? = getUserUseCase.invoke(emailUser)
            val loginStatus: LoginStatus = if(user != null && user.password == password){
                LoginSuccess(emailUser)
            } else{
                LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }


}