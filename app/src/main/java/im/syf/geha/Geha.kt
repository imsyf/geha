package im.syf.geha

import android.app.Application
import im.syf.geha.data.DummyDataSource
import im.syf.geha.data.network.GitHubClient
import im.syf.geha.data.network.GitHubService

class Geha : Application() {

    val dummyDataSource: DummyDataSource by lazy {
        DummyDataSource.getInstance(this)
    }

    val gitHubService: GitHubService by lazy {
        GitHubClient.gitHubService
    }
}
