package com.scheffer.erik.financial.api.config

import com.scheffer.erik.financial.api.config.property.FinancialApiProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl


@Configuration
class MailConfig(private val property: FinancialApiProperty) {

    @Bean
    fun mailSender(): JavaMailSender =
            JavaMailSenderImpl().apply {
                host = property.mail.host
                port = property.mail.port
                username = property.mail.username
                password = property.mail.password
                with(javaMailProperties) {
                    put("mail.transport.protocol", "smtp")
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                    put("mail.smtp.connectiontimeout", 10000)
                    put("mail.smtp.ssl.trust", "smtp.gmail.com")
                    put("mail.debug", "true")
                }
            }
}
