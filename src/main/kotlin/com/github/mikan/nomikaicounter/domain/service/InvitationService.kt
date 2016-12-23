/*
 * Copyright(C) 2014-2016 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.domain.service

import com.github.mikan.nomikaicounter.domain.model.Invitation
import com.github.mikan.nomikaicounter.domain.repository.InvitationRepository
import com.github.mikan.nomikaicounter.domain.repository.MongoCustomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Invitation service.
 *
 * @author mikan
 * @since 2.0
 */
@Service
class InvitationService @Autowired constructor(private val repository: InvitationRepository,
                                               private val customRepository: MongoCustomRepository) {

    fun create(author: String, subject: String, description: String, deadline: String): Invitation {
        return repository.save(Invitation(null, author, subject, description, deadline, emptyList()))
    }

    fun find(id: String): Invitation? {
        return repository.findInvitationById(id)
    }

    fun findAll(limit: Int): List<Invitation>? {
        return customRepository.findAll(limit)
    }

    fun addPost(id: String, name: String, action: String, message: String): Boolean {
        return customRepository.addPost(id, name, action, message)
    }
}
