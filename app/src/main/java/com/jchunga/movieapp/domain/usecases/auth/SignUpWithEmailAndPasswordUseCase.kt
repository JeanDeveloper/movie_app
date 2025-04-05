package com.jchunga.movieapp.domain.usecases.auth

import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.user.AuthUser
import com.jchunga.movieapp.domain.repositories.IAuthRepository

class SignUpWithEmailAndPasswordUseCase(
    private val authRepository: IAuthRepository
) {

    suspend operator fun invoke(email: String, password: String, name: String): Either<Exception, AuthUser> {
        return try {
            when (val result = authRepository.signUpWithEmailAndPassword(email, password, name)){
                is Either.Left -> Either.Left(result.value)
                is Either.Right -> {
                    val user = result.value
                    when(val userResult = authRepository.saveUser(user)){
                        is Either.Left -> Either.Left(userResult.value)
                        is Either.Right -> Unit
                    }
                    Either.Right(user)
                }
            }

        } catch (e:Exception){
            Either.Left(e)

        }
    }

}