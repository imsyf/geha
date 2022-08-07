package im.syf.geha.ui.profile

import androidx.annotation.StringRes
import im.syf.geha.R
import im.syf.geha.ui.profile.list.FollowersListType
import im.syf.geha.ui.profile.list.FollowingListType
import im.syf.geha.ui.profile.list.RepositoryListType

sealed class PageItem(@StringRes val title: Int)

class RepositoryPage(
    username: String,
    itemCount: Int,
) : PageItem(R.string.repository) {
    val repository = RepositoryListType(username, itemCount)
}

class FollowingPage(
    username: String,
    itemCount: Int,
) : PageItem(R.string.following) {
    val following = FollowingListType(username, itemCount)
}

class FollowersPage(
    username: String,
    itemCount: Int,
) : PageItem(R.string.followers) {
    val followers = FollowersListType(username, itemCount)
}
