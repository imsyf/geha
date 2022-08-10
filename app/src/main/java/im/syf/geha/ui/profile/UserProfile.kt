package im.syf.geha.ui.profile

import im.syf.geha.data.network.response.UserProfileDto

data class UserProfile(
    val username: String,
    val name: String,
    val avatarUrl: String,
    val company: String,
    val location: String,
    val repository: Int,
    val followers: Int,
    val following: Int,
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
)
