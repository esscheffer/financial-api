package com.scheffer.erik.financial.api

import com.scheffer.erik.financial.api.model.ApiUser
import com.scheffer.erik.financial.api.model.FinancialEntry
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.util.*
import kotlin.collections.HashMap

@Component
class Mailer(private val mailSender: JavaMailSender, private val thymeleaf: TemplateEngine) {

    fun warnFinancialEntriesDue(financialEntriesDue: List<FinancialEntry>, recipients: List<ApiUser>) =
            sendMail("scheffer.erik@gmail.com",
                    recipients.map { it.email },
                    "Financial entries due",
                    "mail/financial-entry-due-warn",
                    HashMap<String, Any>().apply { put("financialEntries", financialEntriesDue) })

    fun sendMail(sender: String, recipients: List<String>, subject: String, template: String, variables: Map<String, Any>) {
        val context = Context(Locale("pt", "BR"))

        variables.forEach { (key, value) -> context.setVariable(key, value) }

        sendMail(sender, recipients, subject, thymeleaf.process(template, context))
    }

    fun sendMail(sender: String, recipients: List<String>, subject: String, message: String) {
        val mimeMessage = mailSender.createMimeMessage()

        with(MimeMessageHelper(mimeMessage, "UTF-8")) {
            setFrom(sender)
            setTo(recipients.toTypedArray())
            setSubject(subject)
            setText(message, true)
        }

        mailSender.send(mimeMessage)
    }
}
