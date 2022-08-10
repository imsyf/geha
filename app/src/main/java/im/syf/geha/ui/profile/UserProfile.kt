package im.syf.geha.ui.profile

import im.syf.geha.data.db.UserProfileEntity
import im.syf.geha.data.network.response.UserProfileDto

data class UserProfile(
    val username: String,
    val name: String?,
    val avatarUrl: String,
    val company: String?,
    val location: String?,
    val repository: Int,
    val followers: Int,
    val following: Int,
    val url: String,
    val type: String,
)

fun UserProfile.toUserProfileEntity(): UserProfileEntity = UserProfileEntity(
    username,
    name,
    avatar_url = avatarUrl,
    company,
    location,
    repository,
    followers,
    following,
    url,
    type,
)

fun UserProfileDto.toUserProfile(): UserProfile = UserProfile(
    username = login,
    name,
    avatarUrl = avatar_url,
    company,
    location,
    repository = public_repos,
    followers,
    following,
    url = html_url,
    type,
)

fun UserProfileEntity.toUserProfile(): UserProfile = UserProfile(
    username,
    name,
    avatarUrl = avatar_url,
    company,
    location,
    repository,
    followers,
    following,
    url,
    type,
)
