package im.syf.geha.ui.profile.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ListType : Parcelable {
    abstract val username: String
    abstract val itemCount: Int
}

@Parcelize
class RepositoryListType(
    override val username: String,
    override val itemCount: Int,
) : ListType()

@Parcelize
class FollowingListType(
    override val username: String,
    override val itemCount: Int,
) : ListType()

@Parcelize
class FollowersListType(
    override val username: String,
    override val itemCount: Int,
) : ListType()
