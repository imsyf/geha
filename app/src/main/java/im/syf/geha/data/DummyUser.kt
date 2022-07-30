package im.syf.geha.data

import androidx.annotation.DrawableRes

data class DummyUser(
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
