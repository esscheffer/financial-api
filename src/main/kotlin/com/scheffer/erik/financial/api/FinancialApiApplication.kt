package com.scheffer.erik.financial.api

import com.scheffer.erik.financial.api.config.property.FinancialApiProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableConfigurationProperties(FinancialApiProperty::class)
class FinancialApiApplication

fun main(args: Array<String>) {
    runApplication<FinancialApiApplication>(*args)
}

@PostConstruct
fun started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
}
