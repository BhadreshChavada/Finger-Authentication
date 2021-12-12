package com.fingureauthdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.FingureAuthentication.AuthResult
import com.FingureAuthentication.Authentication

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authBtn = findViewById<Button>(R.id.btnAuthVerification)
        authBtn.setOnClickListener {
            authVerification()
        }

    }

    private fun authVerification() {
        Authentication(this, object : AuthResult {
            override fun deviceHaveNotAuth() {
                Toast.makeText(
                    applicationContext,
                    "Device not support",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun userHaveNotAddedFinger() {
                Toast.makeText(
                    applicationContext,
                    "Finger not found",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun authFail() {
                Toast.makeText(
                    applicationContext,
                    "Authentication Fail",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun authSuccessful() {
                Toast.makeText(
                    applicationContext,
                    "Authentication Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun authUsingUserCredentials() {
                Toast.makeText(
                    applicationContext,
                    "Open application Auth screen",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }).displayBiometricPrompt()
    }
}