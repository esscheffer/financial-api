package com.scheffer.erik.financial.api.resource

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/tokens")
class TokenResource {

    @DeleteMapping("/revoke")
    fun revoke(req: HttpServletRequest, resp: HttpServletResponse) =
            with(resp) {
                val cookie = Cookie("refreshToken", null).apply {
                    isHttpOnly = true
                    secure = true
                    path = req.contextPath + "/oauth/token"
                    maxAge = 0
                }

                addCookie(cookie)
                status = HttpStatus.NO_CONTENT.value()
            }
}