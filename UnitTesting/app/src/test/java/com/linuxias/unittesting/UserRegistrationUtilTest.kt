package com.linuxias.unittesting

import org.junit.Test
import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat

class UserRegistrationUtilTest {
    @Test
    fun `Empty id returns false`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "",
            "test-password",
            "test-password",
            "James",
            20
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid id and correctly repeated password returns true`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "linuxias",
            "test-password",
            "test-password",
            "James",
            20
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "exist-linuxias",
            "test-password",
            "test-password",
            "James",
            20
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "linuxias",
            "test-password",
            "none",
            "James",
            20
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "linuxias",
            "",
            "",
            "James",
            20
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty username returns false`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "linuxias",
            "test-password",
            "test-password",
            "",
            20
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `age less 0 returns false`() {
        val result = UserRegistrationUtil.validateRegistrationInput(
            "linuxias",
            "test-password",
            "test-password",
            "",
            20
        )
        assertThat(result).isFalse()
    }
}