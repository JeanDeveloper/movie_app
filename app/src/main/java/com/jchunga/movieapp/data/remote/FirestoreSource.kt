package com.jchunga.movieapp.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.domain.entities.user.AuthUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreSource @Inject constructor(
    private val db: FirebaseFirestore
){

    fun saveUser(user:AuthUser): Either<Exception, Boolean> {
        return try {
            db.collection("users")
                .document(user.id)
                .set(user)
                .addOnSuccessListener {
                    Either.Right(true)
                }
                .addOnFailureListener { e ->
                    Either.Left(e)
                }
            Either.Right(true)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    suspend fun getFavoriteMovies(userId: String) : Either<Exception, List<DetailMovie>> {
        return try {
            val response = db.collection("users")
                .document(userId)
                .collection("favorites")
                .get()
                .await()

            val movies = response.documents.mapNotNull { it.toObject(DetailMovie::class.java) }

            Either.Right(movies)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    suspend fun addFavoriteMovie(userId: String, movie: DetailMovie): Either<Exception, Boolean> {
        return try {
            val resp = db.collection("users")
                .document(userId)
                .collection("favorites")
                .document(movie.id.toString())
                .set(movie)
                .await()

            Either.Right(true)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    suspend fun removeFavoriteMovie(userId: String, movieId: Long): Either<Exception, Boolean> {
        return try {
            db.collection("users")
                .document(userId)
                .collection("favorites")
                .document(movieId.toString())
                .delete()
                .await()

            Either.Right(true)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    suspend fun isMovieFavorite(userId: String, movieId: Long) : Either<Exception, Boolean> {
        return try {
            val document = db.collection("users")
                .document(userId)
                .collection("favorites")
                .document(movieId.toString())
                .get()
                .await()

            Log.i("FirestoreSource", "isMovieFavorite: ${document.exists()}")

            Either.Right(document.exists())

        } catch (e: Exception){
            Either.Left(e)
        }
    }

}