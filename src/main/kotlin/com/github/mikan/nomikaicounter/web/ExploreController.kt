/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter.web

import com.github.mikan.nomikaicounter.service.InvitationService
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
class ExploreController @Autowired constructor(val service: InvitationService) {

    @RequestMapping("/explore", method = arrayOf(GET))
    fun home(model: Model): String {
        model.addAllAttributes(createDefaultAttributes())
        model.addAttribute("invitations", service.findAll(50))
        return "explore";
    }
}