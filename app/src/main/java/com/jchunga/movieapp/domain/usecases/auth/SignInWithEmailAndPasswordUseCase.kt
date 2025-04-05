package com.jchunga.movieapp.domain.usecases.auth

import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.user.AuthUser
import com.jchunga.movieapp.domain.repositories.IAuthRepository

class SignInWithEmailAndPasswordUseCase(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Either<Exception, AuthUser> {
        return authRepository.signInWithEmailAndPassword(email, password)
    }
}