package im.syf.geha

import android.app.Application
import im.syf.geha.data.SettingsDataStore
import im.syf.geha.data.db.UserProfileDao
import im.syf.geha.data.db.UserProfileDatabase
import im.syf.geha.data.network.GitHubClient
import im.syf.geha.data.network.GitHubService

class Geha : Application() {

    val gitHubService: GitHubService by lazy {
        GitHubClient.gitHubService
    }

    val settingsDataStore: SettingsDataStore by lazy {
        SettingsDataStore(this)
    }

    val userProfileDao: UserProfileDao by lazy {
        UserProfileDatabase.getDatabase(this).userProfileDao()
    }
}
