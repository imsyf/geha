package im.syf.geha.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val THEME_MODE_PREFERENCES_NAME = "theme_mode_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = THEME_MODE_PREFERENCES_NAME
)

class SettingsDataStore(context: Context) {

    private val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")

    val preferenceFlow: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            // Set default value to false
            preferences[IS_DARK_MODE] ?: false
        }

    suspend fun setThemeMode(context: Context, enableDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = enableDarkMode
        }
    }
}
