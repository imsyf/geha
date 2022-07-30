package im.syf.geha.data

import android.content.Context
import android.content.res.Resources
import im.syf.geha.R

class DummyDataSource(context: Context) {

    val dummyUsers: List<DummyUser> = context.resources.loadDummyData()

    private fun Resources.loadDummyData(): List<DummyUser> {
        val avatars = obtainTypedArray(R.array.avatar)

        val dummyUsers = List(DUMMY_DATA_SIZE) {
            DummyUser(
                id = it,
                username = getStringArray(R.array.username)[it],
                name = getStringArray(R.array.name)[it],
                company = getStringArray(R.array.company)[it],
                location = getStringArray(R.array.location)[it],
                repository = getStringArray(R.array.repository)[it],
                followers = getStringArray(R.array.followers)[it],
                following = getStringArray(R.array.following)[it],
                avatar = avatars.getResourceId(it, -1),
            )
        }

        // To avoid memory leaks
        avatars.recycle()

        return dummyUsers
    }

    companion object {

        private const val DUMMY_DATA_SIZE = 10

        @Volatile
        private var INSTANCE: DummyDataSource? = null

        fun getInstance(context: Context): DummyDataSource {
            return INSTANCE ?: synchronized(this) {
                val instance = DummyDataSource(context)

                INSTANCE = instance
                instance
            }
        }
    }
}
