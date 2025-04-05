package com.jchunga.movieapp.core.utils

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.jchunga.movieapp.core.utils.Constants.BASE_URL
import com.jchunga.movieapp.data.remote.FirebaseSource
import com.jchunga.movieapp.data.remote.FirestoreSource
import com.jchunga.movieapp.data.remote.MovieApi
import com.jchunga.movieapp.data.repositories.AuthRepositoryImpl
import com.jchunga.movieapp.data.repositories.MovieRepositoryImp
import com.jchunga.movieapp.data.repositories.PreferencesRepository
import com.jchunga.movieapp.domain.entities.AuthUseCases
import com.jchunga.movieapp.domain.entities.MoviesUseCases
import com.jchunga.movieapp.domain.repositories.IAuthRepository
import com.jchunga.movieapp.domain.repositories.IPreferencesRepository
import com.jchunga.movieapp.domain.repositories.MovieRepository
import com.jchunga.movieapp.domain.usecases.auth.GetAuthStateUseCase
import com.jchunga.movieapp.domain.usecases.auth.LogOutUseCase
import com.jchunga.movieapp.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.jchunga.movieapp.domain.usecases.auth.SignUpWithEmailAndPasswordUseCase
import com.jchunga.movieapp.domain.usecases.movies.GetCharacters
import com.jchunga.movieapp.domain.usecases.movies.GetDetailMovie
import com.jchunga.movieapp.domain.usecases.movies.GetFavoriteMoviesUseCase
import com.jchunga.movieapp.domain.usecases.movies.GetNotPlayingMovies
import com.jchunga.movieapp.domain.usecases.movies.GetPopularMovies
import com.jchunga.movieapp.domain.usecases.movies.GetUpcomingMovies
import com.jchunga.movieapp.domain.usecases.movies.IsFavoriteMovieUseCase
import com.jchunga.movieapp.domain.usecases.movies.RemoveFavoriteMovieUseCase
import com.jchunga.movieapp.domain.usecases.movies.SaveFavoriteMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseSource(
        firebaseAuth: FirebaseAuth
    ) = FirebaseSource(
        firebaseAuth = firebaseAuth
    )

    @Provides
    @Singleton
    fun provideFirestoreSource(
        firebaseFirebase: FirebaseFirestore,
    ) = FirestoreSource(
        db = firebaseFirebase
    )

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseSource: FirebaseSource,
        firestoreSource: FirestoreSource
    ) : IAuthRepository = AuthRepositoryImpl(firebaseSource, firestoreSource)

    @Provides
    @Singleton
    fun provideMoviesApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi,
        firestoreSource: FirestoreSource
    ) : MovieRepository = MovieRepositoryImp(movieApi, firestoreSource)

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        movieRepository: MovieRepository
    ) = MoviesUseCases(
        getNowPlayingMovies = GetNotPlayingMovies(movieRepository),
        getUpcomingMovies = GetUpcomingMovies(movieRepository),
        getPopularMovies = GetPopularMovies(movieRepository),
        getDetailsMovie = GetDetailMovie(movieRepository),
        getCharacters = GetCharacters(movieRepository),
        getFavoriteMovies = GetFavoriteMoviesUseCase(movieRepository),
        saveFavoriteMovie = SaveFavoriteMovieUseCase(movieRepository),
        removeFavoriteMovie = RemoveFavoriteMovieUseCase(movieRepository),
        isMovieFavorite = IsFavoriteMovieUseCase(movieRepository)
    )

    @Provides
    @Singleton
    fun provideAuthUseCases(
        authRepository: IAuthRepository
    ) = AuthUseCases(
        signInWithEmailAndPasswordUseCase = SignInWithEmailAndPasswordUseCase(authRepository),
        getAuthStateUseCase = GetAuthStateUseCase(authRepository),
        logOutUseCase = LogOutUseCase(authRepository),
        signUpWithEmailAndPasswordUseCase = SignUpWithEmailAndPasswordUseCase(authRepository)
    )

    @Provides
    @Singleton
    fun providePreferencesRepository(
        @ApplicationContext context: Context
    ): IPreferencesRepository {
        return PreferencesRepository(context)
    }

}