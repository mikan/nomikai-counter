/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter.repository

import com.github.mikan.nomikaicounter.domain.Invitation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author mikan
 */
@Repository
interface InvitationRepository : CrudRepository<Invitation, String> {
    fun findInvitationById(id: String): Invitation
}
