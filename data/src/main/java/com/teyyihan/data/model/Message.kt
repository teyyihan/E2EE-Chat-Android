package com.teyyihan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var _id: Int? = null,
    var friendID: String,
    var messageBody: String?,
    var date: String,
    var byMe : Boolean
)