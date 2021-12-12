package com.FingureAuthentication

import android.content.Context
import android.os.Build
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.from

/**
 * Created by Bhadresh on 12,December,2021
 */
object BiometricUtils {
    val isBiometricPromptEnabled: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    /*
     * Condition I: Check if the android version in device is greater than
     * Marshmallow, since fingerprint authentication is only supported
     * from Android 6.0.
     * Note: If your project's minSdkversion is 23 or higher,
     * then you won't need to perform this check.
     *
     * */
    val isSdkVersionSupported: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    /*
     * Condition II: Check if the device has fingerprint sensors.
     * Note: If you marked android.hardware.fingerprint as something that
     * your app requires (android:required="true"), then you don't need
     * to perform this check.
     *
     * */
    fun isHardwareSupported(context: Context?): Boolean {
        val fingerprintManager = from(context!!)
        return fingerprintManager.isHardwareDetected
    }

    /*
     * Condition III: Fingerprint authentication can be matched with a
     * registered fingerprint of the user. So we need to perform this check
     * in order to enable fingerprint authentication
     *
     * */
    fun isFingerprintAvailable(context: Context?): Boolean {
        val fingerprintManager = from(context!!)
        return fingerprintManager.hasEnrolledFingerprints()
    }

}