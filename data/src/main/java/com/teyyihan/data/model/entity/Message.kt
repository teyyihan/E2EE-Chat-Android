package com.teyyihan.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var _id: Int? = null,
    var fromID: String,
    var messageBody: String?,
    var date: String,
    var byMe : Boolean
)