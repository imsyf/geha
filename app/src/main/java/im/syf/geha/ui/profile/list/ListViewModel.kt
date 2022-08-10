package im.syf.geha.ui.profile.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.geha.data.network.GitHubService
import im.syf.geha.data.network.response.RepoDto
import im.syf.geha.data.network.response.UserDto
import im.syf.geha.ui.common.User
import im.syf.geha.ui.common.toUser
import kotlinx.coroutines.launch

class ListViewModel(
    private val listType: ListType,
    private val gitHubService: GitHubService,
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        fetchData()
    }

    private fun fetchData() {
        _state.value = State.Loading

        viewModelScope.launch {
            _state.value = try {
                when (listType) {
                    is FollowersListType -> {
                        with(gitHubService.getFollowers(listType.username)) {
                            if (isNullOrEmpty()) State.Empty
                            else State.UserData(map(UserDto::toUser))
                        }
                    }
                    is FollowingListType -> {
                        with(gitHubService.getFollowing(listType.username)) {
                            if (isNullOrEmpty()) State.Empty
                            else State.UserData(map(UserDto::toUser))
                        }
                    }
                    is RepositoryListType -> {
                        with(gitHubService.getRepos(listType.username)) {
                            if (isNullOrEmpty()) State.Empty
                            else State.RepoData(map(RepoDto::toRepo))
                        }
                    }
                }
            } catch (e: Exception) {
                State.Error
            }
        }
    }

    sealed class State {
        object Loading : State()
        object Empty : State()
        object Error : State()
        data class UserData(val users: List<User>) : State()
        data class RepoData(val repos: List<Repo>) : State()
    }
}
