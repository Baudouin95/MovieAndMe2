package com.example.movieandme2.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.movieandme2.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.android.ext.android.inject

class RegistrationActivity: AppCompatActivity() {
    val registrationViewModel : RegistrationViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationViewModel.registrationLiveData.observe(this, Observer {
            when(it){
                is RegistrationSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Successfully Registration")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
                RegistrationError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Error Registration")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })

        create_account.setOnClickListener {
            registrationViewModel.onClickedCreateAccount(email_edit.text.toString(), pass_edit.text.toString() )
        }
    }





}