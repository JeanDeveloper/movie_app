package com.jchunga.movieapp.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.jchunga.movieapp.core.utils.Constants
import com.jchunga.movieapp.domain.repositories.IPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(Constants.SETTING_LABEL)

class PreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : IPreferencesRepository{

    private val ONBOARDING_COMPLETED = booleanPreferencesKey(Constants.ONBOARDING_KEY)

    override suspend fun saveOnboardingState(isCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = isCompleted
        }
    }

    override fun getOnboardingState(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }
    }

}