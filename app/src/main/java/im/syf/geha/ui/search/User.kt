package im.syf.geha.ui.search

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import im.syf.geha.data.DummyUser
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val username: String,
    val name: String,
    val location: String,
    @DrawableRes val avatar: Int,
) : Parcelable {
    companion object {
        val DIFFER = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

fun DummyUser.toUser(): User = User(
    id,
    username,
    name,
    location,
    avatar
)
