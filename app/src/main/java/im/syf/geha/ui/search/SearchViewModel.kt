package im.syf.geha.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.geha.data.network.GitHubService
import im.syf.geha.data.network.response.UserDto
import kotlinx.coroutines.launch

class SearchViewModel(
    private val gitHubService: GitHubService,
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Initial)
    val state: LiveData<State> = _state

    fun onQuery(query: String) {
        _state.value = State.Loading

        viewModelScope.launch {
            _state.value = try {
                with(gitHubService.searchUser(query).items) {
                    if (isNullOrEmpty()) State.Empty
                    else State.Success(map(UserDto::toUser))
                }
            } catch (e: Exception) {
                State.Error
            }
        }
    }

    fun reset() {
        _state.value = State.Initial
    }

    sealed class State {
        object Initial : State()
        object Loading : State()
        object Empty : State()
        object Error : State()
        data class Success(val users: List<User>) : State()
    }
}
