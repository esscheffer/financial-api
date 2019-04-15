package com.scheffer.erik.financial.api.token

import org.apache.catalina.util.ParameterMap
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class RefreshTokenCookiePreProcessorFilter : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        var req = request as HttpServletRequest

        if ("/oauth/token".equals(req.requestURI, ignoreCase = true)
                && "refresh_token" == req.getParameter("grant_type")
                && req.cookies != null) {
            for (cookie in req.cookies) {
                if (cookie.name == "refreshToken") {
                    val refreshToken = cookie.value
                    req = MyServletRequestWrapper(req, refreshToken)
                }
            }
        }

        chain.doFilter(req, response)
    }

    internal class MyServletRequestWrapper(request: HttpServletRequest, private val refreshToken: String)
        : HttpServletRequestWrapper(request) {

        override fun getParameterMap() =
                ParameterMap(request.parameterMap).apply {
                    this["refresh_token"] = arrayOf(refreshToken)
                    isLocked = true
                }
    }
}