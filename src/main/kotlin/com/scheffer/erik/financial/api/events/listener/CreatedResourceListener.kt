package com.scheffer.erik.financial.api.events.listener

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Component
class CreatedResourceListener : ApplicationListener<CreatedResourceEvent> {
    override fun onApplicationEvent(event: CreatedResourceEvent) {
        val locationUri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(event.id).toUri()
        event.response.setHeader("Location", locationUri.toASCIIString())
    }
}