/*
 * Copyright(C) 2014-2016 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.web

import com.github.mikan.nomikaicounter.domain.service.InvitationService
import com.github.mikan.nomikaicounter.web.ControllerUtil.Companion.createDefaultAttributes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestParam
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest

/**
 * @author mikan
 * @since 2.0
 */
@Controller
class InvitationController @Autowired constructor(private val service: InvitationService) {

    private val log = Logger.getLogger(InvitationController::class.qualifiedName)

    @RequestMapping("/{id}", method = arrayOf(GET))
    fun handleGet(model: Model, @PathVariable("id") id: String, request: HttpServletRequest): String {
        model.addAllAttributes(createDefaultAttributes())
        val invitation = service.find(id) ?: return ControllerUtil.errorView(log, model, "No invitation found.")
        model.addAttribute("invitation", invitation)
        model.addAttribute("urlYes", request.requestURL.toString() + "/yes")
        model.addAttribute("urlNo", request.requestURL.toString() + "/no")
        model.addAttribute("countYes", invitation.posts.filter { p -> p.action == "yes" }.count())
        model.addAttribute("countNo", invitation.posts.filter { p -> p.action == "no" }.count())
        return "invitation"
    }

    @RequestMapping(path = arrayOf("/{id}/yes", "/{id}/no"), method = arrayOf(POST))
    fun handlePost(model: Model, @PathVariable("id") id: String, @RequestParam("name") name: String, @RequestParam("action") action: String,
                   @RequestParam("message") message: String): String {
        model.addAllAttributes(createDefaultAttributes())
        if (service.addPost(id, name, action, message)) {
            return "success"
        } else {
            return ControllerUtil.errorView(log, model, "Update failed. Please contact the administrator.")
        }
    }

    @RequestMapping("/{id}/yes", method = arrayOf(GET))
    fun yes(model: Model, @PathVariable("id") id: String): String {
        model.addAllAttributes(createDefaultAttributes())
        val invitation = service.find(id) ?: return ControllerUtil.errorView(log, model, "No invitation found.")
        model.addAttribute("invitation", invitation)
        return "yes"
    }

    @RequestMapping("/{id}/no", method = arrayOf(GET))
    fun no(model: Model, @PathVariable("id") id: String): String {
        model.addAllAttributes(createDefaultAttributes())
        val invitation = service.find(id) ?: return ControllerUtil.errorView(log, model, "No invitation found.")
        model.addAttribute("invitation", invitation)
        return "no"
    }
}