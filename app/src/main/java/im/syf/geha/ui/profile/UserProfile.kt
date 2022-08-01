package im.syf.geha.ui.profile

import androidx.annotation.DrawableRes
import im.syf.geha.data.DummyUser

data class UserProfile(
    val id: Int,
    val username: String,
    val name: String,
    val company: String,
    val location: String,
    val repository: String,
    val followers: String,
    val following: String,
    @DrawableRes val avatar: Int,
)

fun DummyUser.toUserProfile(): UserProfile = UserProfile(
    id,
    username,
    name,
    company,
    location,
    repository,
    followers,
    following,
    avatar
)
