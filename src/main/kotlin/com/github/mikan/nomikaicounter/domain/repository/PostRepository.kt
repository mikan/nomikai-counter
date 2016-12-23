/*
 * Copyright(C) 2014-2016 mikan All rights reserved.
 */
package com.github.mikan.nomikaicounter.domain.repository

/**
 * @author mikan
 */
interface PostRepository {
    fun addPost(id: String, name: String, action: String, message: String): Boolean
}