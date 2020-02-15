/*
 * Copyright(C) 2014-2020 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.web

import com.github.mikan.nomikaicounter.domain.service.InvitationService
import com.github.mikan.nomikaicounter.web.ControllerUtil.Companion.createDefaultAttributes
import com.github.mikan.nomikaicounter.web.ControllerUtil.Companion.isValidText
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestParam
import java.util.logging.Logger

/**
 * Home controller.
 *
 * @author mikan
 * @since 2.0
 */
@Controller
class HomeController @Autowired constructor(private val service: InvitationService) {

    private val log = Logger.getLogger(HomeController::class.qualifiedName)

    @RequestMapping(value = ["/"], method = [GET])
    fun handleGet(model: Model): String {
        model.addAllAttributes(createDefaultAttributes())
        return "home"
    }

    @RequestMapping(value = ["/"], method = [POST])
    fun handlePost(model: Model, @RequestParam("name") name: String, @RequestParam("subject") subject: String,
                   @RequestParam("description") description: String, @RequestParam("deadline") deadline: String):
            String {
        if (!isValidText(name) || !isValidText(subject) || !isValidText(description) || !isValidText(deadline)) {
            return ControllerUtil.errorView(log, model, "Illegal character(s) contained.")
        }
        val created = service.create(name, subject, description, deadline)
        log.info("Invitation created: " + created.id.toString() + " " + created.subject)
        return "redirect:/" + created.id // PRG pattern
    }
}