package com.scheffer.erik.financial.api

import com.scheffer.erik.financial.api.config.property.FinancialApiProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FinancialApiProperty::class)
class FinancialApiApplication

fun main(args: Array<String>) {
    runApplication<FinancialApiApplication>(*args)
}
