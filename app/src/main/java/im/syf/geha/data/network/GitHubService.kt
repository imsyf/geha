package im.syf.geha.data.network

import im.syf.geha.data.network.response.RepoDto
import im.syf.geha.data.network.response.SearchDto
import im.syf.geha.data.network.response.UserDto
import im.syf.geha.data.network.response.UserProfileDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GitHubService {

    private val shouldFail
        get() = Random.nextBoolean()

    private suspend fun pre() {
        delay((1000..5000).random().toLong())

        if (shouldFail) {
            throw Exception()
        }
    }

    suspend fun searchUser(query: String): SearchDto {
        lateinit var result: SearchDto

        withContext(Dispatchers.IO) {
            pre()
            result = SearchDto.EXAMPLE
        }

        return result
    }

    suspend fun getUserProfile(login: String): UserProfileDto {
        lateinit var result: UserProfileDto

        withContext(Dispatchers.IO) {
            pre()
            result = UserProfileDto.EXAMPLE
        }

        return result
    }

    suspend fun getRepos(login: String): List<RepoDto> {
        val result = mutableListOf<RepoDto>()

        withContext(Dispatchers.IO) {
            pre()
            result.addMany(RepoDto.EXAMPLE)
        }

        return result
    }

    suspend fun getFollowing(login: String): List<UserDto> {
        val result = mutableListOf<UserDto>()

        withContext(Dispatchers.IO) {
            pre()
            result.addMany(UserDto.EXAMPLE)
        }

        return result
    }

    suspend fun getFollowers(login: String): List<UserDto> {
        val result = mutableListOf<UserDto>()

        withContext(Dispatchers.IO) {
            pre()
            result.addMany(UserDto.EXAMPLE)
        }

        return result
    }

    private fun <T> MutableList<T>.addMany(element: T, howMany: Int = (0..10).random()) {
        addAll(List(howMany) { element })
    }
}
