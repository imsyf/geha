package im.syf.geha.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import im.syf.geha.data.db.UserProfileDao
import im.syf.geha.data.db.UserProfileEntity
import im.syf.geha.ui.common.User
import im.syf.geha.ui.common.toUser

class SavedViewModel(
    userProfileDao: UserProfileDao,
) : ViewModel() {

    private val savedProfiles = userProfileDao.getUserProfiles().asLiveData()

    val state: LiveData<State> = savedProfiles.map {
        if (it.isEmpty()) State.Empty
        else State.Success(it.map(UserProfileEntity::toUser))
    }

    sealed class State {
        object Empty : State()
        data class Success(val users: List<User>) : State()
    }
}
