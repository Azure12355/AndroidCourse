package com.lytton.androidcourse.entity

/**
 * @program: AndroidCourse
 * @description:
 * @author: LyttonYang
 * @create: 2024-10-13 10:37
 */
data class UserCredentials (
    val username: String,
    val password: String,
    val remember: Boolean
)