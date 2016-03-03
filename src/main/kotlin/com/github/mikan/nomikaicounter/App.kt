/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Spring Application class.
 *
 * @author mikan
 * @since 2.0
 * @see "https://kotlinlang.org/docs/tutorials/spring-boot-restful.html"
 */
@SpringBootApplication
open class App {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(App::class.java, *args)
        }
    }
}