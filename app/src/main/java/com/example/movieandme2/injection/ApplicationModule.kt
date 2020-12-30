package com.example.movieandme2.injection

import android.content.Context
import androidx.room.Room
import com.example.movieandme2.data.local.AppDatabase
import com.example.movieandme2.data.local.DataBaseDao
import com.example.movieandme2.data.repository.UserRepository
import com.example.movieandme2.domain.usecase.CreateUserUseCase
import com.example.movieandme2.domain.usecase.GetUserUseCase
import com.example.movieandme2.presentation.main.MainViewModel
import com.example.movieandme2.presentation.main.RegistrationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentaionModule = module {
    factory { MainViewModel(get(), get()) }
    factory { RegistrationViewModel(get(), get()) }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module {
    single { UserRepository(get()) }
    single { createDataBase(androidContext())}
}

fun createDataBase(context: Context): DataBaseDao {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}