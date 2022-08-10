package im.syf.geha.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val username: String,
    val name: String?,
    val avatar_url: String,
    val company: String?,
    val location: String?,
    val repository: Int,
    val followers: Int,
    val following: Int,
    val url: String,
    val type: String,
)
