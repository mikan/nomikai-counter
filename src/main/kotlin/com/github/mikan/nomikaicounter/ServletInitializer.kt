/*
 * Copyright(C) 2014-2020 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
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