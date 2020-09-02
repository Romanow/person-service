package ru.romanow.inst

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfiguration: WebMvcConfigurationSupport() {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api-docs")
    }
}