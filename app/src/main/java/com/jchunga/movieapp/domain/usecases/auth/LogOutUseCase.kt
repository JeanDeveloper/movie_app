package com.jchunga.movieapp.domain.usecases.auth

import com.jchunga.movieapp.domain.repositories.IAuthRepository

class LogOutUseCase(
    private val authRepository: IAuthRepository
) {
    operator fun invoke(){
        authRepository.signOut()
    }
}