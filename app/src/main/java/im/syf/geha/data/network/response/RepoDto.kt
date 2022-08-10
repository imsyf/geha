package im.syf.geha.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoDto(
    val name: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int,
    val watchers_count: Int,
    val forks_count: Int,
    val html_url: String,
)
