package im.syf.geha.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserProfileDto(
    val login: String,
    val name: String?,
    val avatar_url: String,
    val company: String?,
    val location: String?,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val html_url: String,
    val type: String,
)
