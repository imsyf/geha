package im.syf.geha.ui.common

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import im.syf.geha.data.network.response.UserDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int = 0,
    val username: String,
    val avatarUrl: String,
    val accountType: String,
) : Parcelable {
    companion object {
        val DIFFER = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

fun UserDto.toUser(): User = User(
    username = login,
    avatarUrl = avatar_url,
    accountType = type,
)
