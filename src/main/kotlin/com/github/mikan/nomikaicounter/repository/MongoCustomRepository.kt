/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */
package com.github.mikan.nomikaicounter.repository

import com.github.mikan.nomikaicounter.domain.Invitation
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import java.util.*
import java.util.logging.Logger

/**
 * Hand-coded MongoDB operation for {@link PostRepository}.
 *
 * @author mikan
 * @since 2.0
 */
@Component
class MongoCustomRepository @Autowired constructor(private val mongoTemplate: MongoTemplate) : PostRepository {

    override fun addPost(id: String, name: String, action: String, message: String): Boolean {
        val findQuery = BasicDBObject("_id", ObjectId(id))
        val postItem = BasicDBObject().append("name", name).append("action", action).append("message", message)
        val updateQuery = BasicDBObject("\$push", BasicDBObject("posts", postItem))
        val result = mongoTemplate.getCollection("invitation").update(findQuery, updateQuery)
        Logger.getLogger(MongoCustomRepository::class.qualifiedName).info("Post result: " + result)
        return result.isUpdateOfExisting
    }

    fun findAll(limit: Int): List<Invitation> {
        val list: MutableList<Invitation> = ArrayList()
        val sortOrder: DBObject = BasicDBObject("\$natural", -1);
        mongoTemplate.getCollection("invitation").find().sort(sortOrder).limit(limit).use {
            it.forEach {
                list.add(Invitation(it.get("_id") as ObjectId, -1,
                        it.get("author") as String, it.get("subject") as String, it.get("description") as String,
                        it.get("deadline") as String, emptyList(), emptyList()))
            }
        }
        return list
    }
}