package im.syf.geha.ui.profile

import androidx.annotation.StringRes
import im.syf.geha.R
import im.syf.geha.ui.profile.list.FollowersListType
import im.syf.geha.ui.profile.list.FollowingListType
import im.syf.geha.ui.profile.list.RepositoryListType

sealed class PageItem(@StringRes val title: Int)

class RepositoryPage(
    val repository: RepositoryListType,
) : PageItem(R.string.repository)

class FollowingPage(
    val following: FollowingListType,
) : PageItem(R.string.following)

class FollowersPage(
    val followers: FollowersListType,
) : PageItem(R.string.followers)
