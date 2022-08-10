package im.syf.geha.ui.profile.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ListType : Parcelable {
    abstract val username: String
}

@Parcelize
class RepositoryListType(
    override val username: String,
) : ListType()

@Parcelize
class FollowingListType(
    override val username: String,
) : ListType()

@Parcelize
class FollowersListType(
    override val username: String,
) : ListType()
