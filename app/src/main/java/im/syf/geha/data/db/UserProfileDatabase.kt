package im.syf.geha.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserProfileEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class UserProfileDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    companion object {

        @Volatile
        private var INSTANCE: UserProfileDatabase? = null

        fun getDatabase(context: Context): UserProfileDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserProfileDatabase::class.java,
                    "geha_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
