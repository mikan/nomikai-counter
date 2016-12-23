/*
 * Copyright(C) 2014-2016 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Configuration

/**
 * @author mikan
 * @since 2.0
 */
@Configuration
open class ServletInitializer : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(App::class)
    }
}