package im.syf.geha.ui.profile

import androidx.lifecycle.ViewModel
import im.syf.geha.data.DummyDataSource
import im.syf.geha.data.DummyUser

class ProfileViewModel(
    id: Int,
    dataSource: DummyDataSource,
) : ViewModel() {

    // Get the more appropriately-shaped data model for this screen
    val userProfile: UserProfile = dataSource.dummyUsers[id]
        .let(DummyUser::toUserProfile)
}
