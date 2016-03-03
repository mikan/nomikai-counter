/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

/**
 * Invitation.
 *
 * @author mikan
 * @since 2.0
 */
@Document
data class Invitation(@Id var id: ObjectId?, @Field var discussionId: Long?, @Field var author: String,
                      @Field var subject: String, @Field var description: String, @Field var deadline: String,
                      @Field var posts: List<Post>, @Field var participants: List<Long>)
