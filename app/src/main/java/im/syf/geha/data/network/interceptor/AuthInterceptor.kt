package im.syf.geha.data.network.interceptor

import im.syf.geha.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "token ${BuildConfig.GITHUB_TOKEN}")
            .build()

        return chain.proceed(request)
    }
}
