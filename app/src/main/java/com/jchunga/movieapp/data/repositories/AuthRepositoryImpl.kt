package com.jchunga.movieapp.data.repositories

import com.google.firebase.auth.FirebaseUser
import com.jchunga.movieapp.data.remote.FirebaseSource
import com.jchunga.movieapp.data.remote.FirestoreSource
import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.user.AuthUser
import com.jchunga.movieapp.domain.repositories.IAuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val fbSource: FirebaseSource,
    private val firestoreSource: FirestoreSource
): IAuthRepository {

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Either<Exception, AuthUser> {
        return try {
            when(val result = fbSource.singInWithEmailAndPassword(email, password)){
                is Either.Left -> Either.Left(result.value)
                is Either.Right -> {
                    val user = result.value.toUser()
                    Either.Right(user)
                }
            }
        } catch (e: Exception){
            Either.Left(e)
        }
    }

    override fun getCurrentUser(): AuthUser? {
        if(fbSource.getCurrentUser() == null) return null
        return fbSource.getCurrentUser()!!.toUser()
    }

    override fun isUserAuthenticated(): Boolean {
        return fbSource.isUserAuthenticated()
    }

    override fun signOut() {
        fbSource.signOut()
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        name: String
    ): Either<Exception, AuthUser> {
        return try {
            return when(val result = fbSource.signUpWithEmailAndPassword(email, password, name)) {
                is Either.Left -> Either.Left(result.value)
                is Either.Right -> {
                    val user = result.value.toUser()
                    Either.Right(user)
                }
            }
        } catch (e: Exception){
            Either.Left(e)
        }
    }

    override suspend fun getUser(userId: String): AuthUser? {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: AuthUser): Either<Exception, Boolean> {
        return try {
            firestoreSource.saveUser(user)
        } catch (e: Exception){
            return Either.Left(e)
        }
    }
}

private fun FirebaseUser.toUser() : AuthUser{
    return AuthUser(
        id = this.uid,
        name = this.displayName ?: "",
        userName = this.displayName,
        email = this.email ?: "",
    )
}