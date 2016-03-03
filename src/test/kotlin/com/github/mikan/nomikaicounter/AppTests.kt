/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(App::class))
@WebAppConfiguration
class AppTests {

    @Test
    fun contextLoads() {
        // do nothing
    }
}