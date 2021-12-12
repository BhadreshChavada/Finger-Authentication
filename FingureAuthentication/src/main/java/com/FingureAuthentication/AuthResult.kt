package com.FingureAuthentication


/**
Created by Bhadresh on 12,December,2021
 */

interface AuthResult {

    fun deviceHaveNotAuth()

    fun userHaveNotAddedFinger()

    fun authFail()

    fun authSuccessful()

    fun authUsingUserCredentials()

}

