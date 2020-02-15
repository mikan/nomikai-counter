/*
 * Copyright(C) 2014-2020 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.web

import com.github.mikan.nomikaicounter.domain.service.InvitationService
import com.github.mikan.nomikaicounter.web.ControllerUtil.Companion.createDefaultAttributes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET

/**
 * @author mikan
 * @since 2.0
 */
@Controller
class ExploreController @Autowired constructor(private val service: InvitationService) {

    @RequestMapping("/explore", method = [GET])
    fun home(model: Model): String {
        model.addAllAttributes(createDefaultAttributes())
        model.addAttribute("invitations", service.findAll(50))
        return "explore"
    }
}