package im.syf.geha.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val login: String,
    val avatar_url: String,
    val type: String,
)
