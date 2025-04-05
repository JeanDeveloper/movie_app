package com.jchunga.movieapp.domain.usecases.auth

import com.jchunga.movieapp.domain.repositories.IAuthRepository
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {

    fun getCurrentUser() = authRepository.getCurrentUser()

    fun isUserAuthenticated() = authRepository.isUserAuthenticated()

}