package im.syf.geha.data.network

import im.syf.geha.data.network.response.RepoDto
import im.syf.geha.data.network.response.SearchDto
import im.syf.geha.data.network.response.UserDto
import im.syf.geha.data.network.response.UserProfileDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): SearchDto

    @GET("users/{username}")
    suspend fun getUserProfile(@Path("username") login: String): UserProfileDto

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") login: String): List<RepoDto>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") login: String): List<UserDto>

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") login: String): List<UserDto>
}
