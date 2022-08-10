package im.syf.geha.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchDto(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserDto>,
)
