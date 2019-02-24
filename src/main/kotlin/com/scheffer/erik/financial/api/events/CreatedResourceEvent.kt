package com.scheffer.erik.financial.api.events

import org.springframework.context.ApplicationEvent
import javax.servlet.http.HttpServletResponse


class CreatedResourceEvent(source: Any,
                           val response: HttpServletResponse,
                           val id: Long?) : ApplicationEvent(source) {
    companion object {
        private const val serialVersionUID = 1L
    }
}