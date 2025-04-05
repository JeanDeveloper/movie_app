package com.jchunga.movieapp.domain.usecases.preferences

import com.jchunga.movieapp.domain.repositories.IPreferencesRepository
import javax.inject.Inject

class SaveOnboardingUseCase @Inject constructor(
    private val preferencesRepository: IPreferencesRepository
) {
    suspend operator fun invoke(isCompleted: Boolean){
        preferencesRepository.saveOnboardingState(isCompleted)
    }
}