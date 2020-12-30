package com.example.movieandme2.domain.usecase

import com.example.movieandme2.data.repository.UserRepository
import com.example.movieandme2.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(user: User){
        userRepository.createUser(user)
    }
}