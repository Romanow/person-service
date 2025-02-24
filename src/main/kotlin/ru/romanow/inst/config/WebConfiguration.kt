package ru.romanow.inst.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
            .allowedOriginPatterns("http://localhost:*")
            .allowedMethods("GET", "POST", "PATCH", "DELETE")
            .exposedHeaders(LOCATION)
    }
}
