package com.teyyihan.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    val friendUsername: String,
    val body: String,
    val date: Long,
    val byMe: Boolean
)