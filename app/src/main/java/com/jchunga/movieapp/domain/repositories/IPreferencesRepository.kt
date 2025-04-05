package com.jchunga.movieapp.domain.repositories

import kotlinx.coroutines.flow.Flow

interface IPreferencesRepository {
    suspend fun saveOnboardingState(isCOmpleted: Boolean)
    fun getOnboardingState(): Flow<Boolean>
}