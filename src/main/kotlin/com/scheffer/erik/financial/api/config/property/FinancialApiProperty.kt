package com.scheffer.erik.financial.api.config.property

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("financialapi")
class FinancialApiProperty {
    val security = SecurityProperty()
    val mail = Mail()

    class SecurityProperty(var enableHttps: Boolean = false)

    class Mail(var host: String = "", var port: Int = 0, var username: String = "", var password: String = "")
}
