package com.scheffer.erik.financial.api.token

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import javax.servlet.http.Cookie

@ControllerAdvice
class RefreshTokenPostProcessor : ResponseBodyAdvice<OAuth2AccessToken> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>) =
            returnType.method?.name == "postAccessToken"

    override fun beforeBodyWrite(body: OAuth2AccessToken?,
                                 returnType: MethodParameter,
                                 selectedContentType: MediaType,
                                 selectedConverterType: Class<out HttpMessageConverter<*>>,
                                 request: ServerHttpRequest,
                                 response: ServerHttpResponse): OAuth2AccessToken? {
        val req = (request as ServletServerHttpRequest).servletRequest
        val resp = (response as ServletServerHttpResponse).servletResponse

        val token = body as DefaultOAuth2AccessToken

        resp.addCookie(createCookieWithRefreshToken(body.getRefreshToken().value, req.contextPath + "/oauth/token"))
        token.refreshToken = null

        return body
    }

    private fun createCookieWithRefreshToken(refreshToken: String, path: String) =
            Cookie("refreshToken", refreshToken).apply {
                isHttpOnly = true
                secure = true
                this.path = path
                maxAge = 2592000
            }
}