package im.syf.geha.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GitHubClient {

    private const val BASE_URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val gitHubService: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}
