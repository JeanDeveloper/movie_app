package com.jchunga.movieapp.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.jchunga.movieapp.domain.entities.Either
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
){

    suspend fun singInWithEmailAndPassword(email:String, password:String) : Either<Exception, FirebaseUser> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return Either.Right(authResult.user!!)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    suspend fun signUpWithEmailAndPassword(email:String, password    :String, name: String) : Either<Exception, FirebaseUser>{
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            firebaseUser?.let {
                val profileUpdate = userProfileChangeRequest {
                    displayName = name
                }
                it.updateProfile(profileUpdate).await()
            }
            return Either.Right(firebaseUser!!)
        } catch (e: Exception){
            Either.Left(e)
        }
    }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun isUserAuthenticated(): Boolean  = firebaseAuth.currentUser != null

    fun signOut() {
        firebaseAuth.signOut()
    }

}