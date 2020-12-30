package com.example.movieandme2.presentation.main

sealed class RegistrationStatus

data class RegistrationSuccess(val email: String): RegistrationStatus()
object RegistrationError: RegistrationStatus()