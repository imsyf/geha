package im.syf.geha.ui.profile

import androidx.annotation.StringRes
import im.syf.geha.R
import im.syf.geha.ui.profile.list.FollowersListType
import im.syf.geha.ui.profile.list.FollowingListType
import im.syf.geha.ui.profile.list.RepositoryListType

sealed class PageItem(@StringRes val title: Int)

class RepositoryPage(
    username: String,
) : PageItem(R.string.repository) {
    val repository = RepositoryListType(username)
}

class FollowingPage(
    username: String,
) : PageItem(R.string.following) {
    val following = FollowingListType(username)
}

class FollowersPage(
    username: String,
) : PageItem(R.string.followers) {
    val followers = FollowersListType(username)
}
