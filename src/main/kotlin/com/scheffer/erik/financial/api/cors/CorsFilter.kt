package com.scheffer.erik.financial.api.cors

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {
    override fun doFilter(req: ServletRequest?, resp: ServletResponse?, chain: FilterChain?) {
        val request = req as HttpServletRequest
        val response = resp as HttpServletResponse

        response.setHeader("Access-Control-Allow-Origin", "https://erik-financial-api.herokuapp.com")
        response.setHeader("Access-Control-Allow-Credentials", "true")

        if (request.method == "OPTIONS") {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept")
            response.setHeader("Access-Control-Max-Age", "3600")

            response.status = HttpServletResponse.SC_OK
        } else {
            chain?.doFilter(req, resp)
        }
    }
}
