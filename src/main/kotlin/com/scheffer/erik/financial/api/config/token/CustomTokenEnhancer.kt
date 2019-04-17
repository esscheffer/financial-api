package com.scheffer.erik.financial.api.config.token

import com.scheffer.erik.financial.api.security.SystemUser
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import java.util.*

class CustomTokenEnhancer : TokenEnhancer {
    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val systemUser = authentication.principal as SystemUser

        val addInfo = HashMap<String, Any>()
        addInfo["name"] = systemUser.user.name

        (accessToken as DefaultOAuth2AccessToken).additionalInformation = addInfo
        return accessToken
    }
}