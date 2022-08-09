package im.syf.geha.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import im.syf.geha.data.DummyDataSource
import im.syf.geha.data.DummyUser

class SearchViewModel(
    dataSource: DummyDataSource,
) : ViewModel() {

    // Get list of dummy users, then map them to this screen data model
    private val users: List<User> = dataSource.dummyUsers
        .map(DummyUser::toUser)

    private val _state = MutableLiveData<State>(State.Success(users))
    val state: LiveData<State> = _state

    fun onQuery(query: String) {
        val filtered = users.filter {
            val name = it.name.contains(query, true)
            val username = it.username.contains(query, true)
            val location = it.location.contains(query, true)

            name || username || location
        }

        _state.value = State.Success(filtered)
    }

    fun reset() {
        _state.value = State.Success(users)
    }

    sealed class State {
        data class Success(val users: List<User>) : State()
    }
}
