package com.teyyihan.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    val friendName: String
)