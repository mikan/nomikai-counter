/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter.web

import com.github.mikan.nomikaicounter.web.ControllerUtil.Companion.createDefaultAttributes
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author mikan
 */
@Controller
class ErrorController : org.springframework.boot.autoconfigure.web.ErrorController {
    companion object {
        const final val VIEW = "error"
        const final val ATTR_MESSAGE = "message"
    }

    override fun getErrorPath(): String? {
        return "/error"
    }

    @RequestMapping("/error")
    fun error(model: Model): String {
        model.addAllAttributes(createDefaultAttributes())
        if (!model.containsAttribute(ATTR_MESSAGE)) {
            model.addAttribute(ATTR_MESSAGE, "Fatal error.")
        }
        return VIEW;
    }
}