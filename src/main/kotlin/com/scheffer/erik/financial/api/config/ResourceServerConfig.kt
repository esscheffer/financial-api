package com.scheffer.erik.financial.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler

@Profile("oauth-security")
@Configuration
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/categories").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.stateless(true)
    }

    @Bean
    fun createExpressionHandler(): MethodSecurityExpressionHandler {
        return OAuth2MethodSecurityExpressionHandler()
    }
}
