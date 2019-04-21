package com.scheffer.erik.financial.api.config.property

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("financialapi")
class FinancialApiProperty {
    val security = SecurityProperty()

    class SecurityProperty {
        var enableHttps = false
    }
}