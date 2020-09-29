package com.teyyihan.data.local.implementation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation

@Dao
interface FriendDao {

//    @Query("SELECT friend_table.friendUsername,friend_table.friendPublicKey,friend_table.sharedSecretKey,message_table.body,message_table.date,message_table.byMe FROM friend_table LEFT JOIN message_table ON friend_table.friendUsername = message_table.friendUsername")
    @Query("SELECT friend_table.friendUsername,friend_table.friendPublicKey,friend_table.sharedSecretKey,message_table.body,message_table.date,message_table.byMe FROM friend_table LEFT JOIN (SELECT * FROM (SELECT * FROM message_table ORDER BY date ASC) GROUP BY friendUsername ORDER BY date ASC) as message_table ON friend_table.friendUsername = message_table.friendUsername")
    fun getAllFriends(): LiveData<List<FriendRepresentation>>

    @Query("SELECT * FROM friend_table WHERE friendUsername = :username")
    suspend fun getFriend(username: String): Friend

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFriend(friend: Friend): Long


}