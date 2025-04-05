package com.jchunga.movieapp.domain.usecases.preferences

import com.jchunga.movieapp.domain.repositories.IPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnboardingStateUseCase @Inject constructor(
    private val preferencesRepository: IPreferencesRepository
) {
    operator fun invoke() : Flow<Boolean>{
        return preferencesRepository.getOnboardingState()
    }
}