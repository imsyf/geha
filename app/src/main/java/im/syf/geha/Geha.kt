package im.syf.geha

import android.app.Application
import im.syf.geha.data.network.GitHubClient
import im.syf.geha.data.network.GitHubService

class Geha : Application() {

    val gitHubService: GitHubService by lazy {
        GitHubClient.gitHubService
    }
}
