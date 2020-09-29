package com.teyyihan.data.local.abstraction

import androidx.paging.PagingSource
import com.teyyihan.data.model.entity.Message

interface MessageLocalDataSource {

    suspend fun insertMessage(message: Message): Long

}