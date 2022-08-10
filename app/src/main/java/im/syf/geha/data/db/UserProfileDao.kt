package im.syf.geha.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Query("SELECT * FROM user_profile")
    fun getUserProfiles(): Flow<List<UserProfileEntity>>

    @Query("SELECT * FROM user_profile WHERE username = :username")
    fun getUserProfile(username: String): Flow<UserProfileEntity>

    @Query("SELECT EXISTS(SELECT * FROM user_profile WHERE username = :username)")
    fun isProfileSaved(username: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userProfile: UserProfileEntity)

    @Delete
    suspend fun delete(userProfile: UserProfileEntity)
}
