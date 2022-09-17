package com.linuxias.unittesting

object UserRegistrationUtil {
    private val existedID = listOf("exist-linuxias")

    fun validateRegistrationInput(
        id: String,
        password: String,
        confirmedPassword: String,
        username: String,
        age: Int
    ): Boolean {
        if (id.isEmpty() || password.isEmpty()
            || confirmedPassword.isEmpty() || username.isEmpty())
            return false

        if (id in existedID)
            return false

        if (password != confirmedPassword)
            return false

        if (age <= 0)
            return false

        return true
    }
}