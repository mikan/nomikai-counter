/*
 * Copyright(C) 2014-2016 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.domain.repository

import com.github.mikan.nomikaicounter.domain.model.Invitation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author mikan
 */
@Repository
interface InvitationRepository : CrudRepository<Invitation, String> {
    fun findInvitationById(id: String): Invitation
}
