package com.jchunga.movieapp.domain.repositories

import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.user.AuthUser

interface IAuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Either<Exception, AuthUser>
    fun getCurrentUser(): AuthUser?
    fun isUserAuthenticated(): Boolean
    fun signOut()
    suspend fun signUpWithEmailAndPassword(email: String, password: String, name: String): Either<Exception, AuthUser>
    suspend fun getUser(userId: String): AuthUser?
    suspend fun saveUser(user: AuthUser): Either<Exception, Boolean>
}