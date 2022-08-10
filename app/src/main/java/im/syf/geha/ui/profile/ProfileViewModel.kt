package im.syf.geha.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.geha.data.network.GitHubService
import im.syf.geha.data.network.response.UserProfileDto
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val username: String,
    private val gitHubService: GitHubService,
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        _state.value = State.Loading

        viewModelScope.launch {
            _state.value = try {
                val userProfile = gitHubService.getUserProfile(username)
                    .let(UserProfileDto::toUserProfile)
                State.Success(userProfile)
            } catch (e: Exception) {
                State.Error
            }
        }
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class Success(val userProfile: UserProfile) : State()
    }
}
