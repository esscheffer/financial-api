package com.scheffer.erik.financial.api.security.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


object PasswordGeneratorApp {
    @JvmStatic
    fun main(args: Array<String>) = println(BCryptPasswordEncoder().encode("admin"))
}