package im.syf.geha.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import im.syf.geha.data.SettingsDataStore
import im.syf.geha.data.network.GitHubService
import im.syf.geha.data.network.response.UserDto
import im.syf.geha.ui.common.User
import im.syf.geha.ui.common.toUser
import kotlinx.coroutines.launch

class SearchViewModel(
    private val gitHubService: GitHubService,
    private val settingsDataStore: SettingsDataStore,
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Initial)
    val state: LiveData<State> = _state

    val darkModePreference: LiveData<Boolean> = settingsDataStore.preferenceFlow.asLiveData()

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

    fun toggleThemeMode(context: Context) {
        viewModelScope.launch {
            val enable = darkModePreference.value?.not() ?: true
            settingsDataStore.setThemeMode(context, enable)
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
