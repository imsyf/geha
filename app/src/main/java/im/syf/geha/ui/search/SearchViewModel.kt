package im.syf.geha.ui.search

import androidx.lifecycle.ViewModel
import im.syf.geha.data.DummyDataSource
import im.syf.geha.data.DummyUser

class SearchViewModel(
    dataSource: DummyDataSource,
) : ViewModel() {

    // Get list of dummy users, then map them to this screen data model
    val users: List<User> = dataSource.dummyUsers
        .map(DummyUser::toUser)

    fun onQuery(query: String): List<User> = users.filter {
        val name = it.name.contains(query, true)
        val username = it.username.contains(query, true)
        val location = it.location.contains(query, true)

        name || username || location
    }
}
