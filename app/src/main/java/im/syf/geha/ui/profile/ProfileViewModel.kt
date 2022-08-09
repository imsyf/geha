package im.syf.geha.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import im.syf.geha.data.DummyDataSource
import im.syf.geha.data.DummyUser

class ProfileViewModel(
    id: Int,
    dataSource: DummyDataSource,
) : ViewModel() {

    // Get the more appropriately-shaped data model for this screen
    private val userProfile: UserProfile = dataSource.dummyUsers[id]
        .let(DummyUser::toUserProfile)

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        _state.value = State.Success(userProfile)
    }

    sealed class State {
        data class Success(val userProfile: UserProfile) : State()
    }
}
