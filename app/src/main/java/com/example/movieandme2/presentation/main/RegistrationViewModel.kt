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

class RegistrationViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel(){
    val registrationLiveData: MutableLiveData<RegistrationStatus> = MutableLiveData()

    fun onClickedCreateAccount(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val user: User? = getUserUseCase.invoke(emailUser)
            val RegistrationStatus: RegistrationStatus = if(user == null){
                createUserUseCase.invoke(User(emailUser, password))
                RegistrationSuccess(emailUser)
            } else{
                RegistrationError
            }
            withContext(Dispatchers.Main){
                registrationLiveData.value = RegistrationStatus
            }
        }
    }




}