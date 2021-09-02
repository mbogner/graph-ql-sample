package dev.mbo.graphqlsample.config

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.lang.NonNull
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver

@Configuration
class WebMvcConfig(
    private val webProperties: WebProperties,
) : WebMvcConfigurer {

    override fun addResourceHandlers(@NonNull registry: ResourceHandlerRegistry) {
        val cachePeriodSeconds = webProperties.resources.cache.period.seconds.toInt()
        val staticLocations = webProperties.resources.staticLocations

        val indexLocations = staticLocations.copyOf()
        for (i in staticLocations.indices) {
            indexLocations[i] = staticLocations[i] + "index.html"
        }

        registry.addResourceHandler(
            "/**/*.html",
            "/**/*.txt",
            "/**/*.json",
            "/**/*.js",
            "/**/*.css",
            "/**/*.png",
            "/**/*.jpg",
            "/**/*.jpeg",
            "/**/*.gif",
            "/**/*.map",
            "/**/*.bmp",
            "/**/*.ttf",
            "/**/*.eot",
            "/**/*.svg",
            "/**/*.woff",
            "/**/*.woff2"
        )
            .addResourceLocations(*staticLocations)
            .setCachePeriod(cachePeriodSeconds)

        registry.addResourceHandler("/**")
            .addResourceLocations(*indexLocations)
            .setCachePeriod(cachePeriodSeconds)
            .resourceChain(true)
            .addResolver(object : PathResourceResolver() {
                override fun getResource(
                    @NonNull resourcePath: String,
                    @NonNull location: Resource
                ): Resource? {
                    return if (location.exists() && location.isReadable) location else null
                }
            })
    }

}
