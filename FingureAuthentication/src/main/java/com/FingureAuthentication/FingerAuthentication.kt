package com.FingureAuthentication

import android.annotation.TargetApi
import android.os.Build
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

/**
Created by Bhadresh on 12,December,2021
 */

public class FingerAuthentication(
    val contextCompat: FragmentActivity,
    val authResult: AuthResult,
    val title: String = "Biometric login",
    val subtitle: String = "Log in using your biometric credential"
) {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @TargetApi(Build.VERSION_CODES.P)
    fun displayBiometricPrompt() {

        if (!BiometricUtils.isSdkVersionSupported) {
            authResult.deviceHaveNotAuth()
            return
        }
        if (!BiometricUtils.isHardwareSupported(contextCompat)) {
            authResult.deviceHaveNotAuth()
            return
        }
        if (!BiometricUtils.isFingerprintAvailable(contextCompat)) {
            authResult.userHaveNotAddedFinger()
            return
        }
        if (!BiometricUtils.isBiometricPromptEnabled) {
            authResult.deviceHaveNotAuth()
            return
        }

        executor = ContextCompat.getMainExecutor(contextCompat)
        biometricPrompt = BiometricPrompt(contextCompat, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    authResult.authFail()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    authResult.authSuccessful()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    authResult.authUsingUserCredentials()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setNegativeButtonText("Use account password")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}