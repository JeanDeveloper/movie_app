package com.jchunga.movieapp.domain.entities

import com.jchunga.movieapp.domain.usecases.auth.GetAuthStateUseCase
import com.jchunga.movieapp.domain.usecases.auth.LogOutUseCase
import com.jchunga.movieapp.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.jchunga.movieapp.domain.usecases.auth.SignUpWithEmailAndPasswordUseCase

data class AuthUseCases(
    val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    val getAuthStateUseCase: GetAuthStateUseCase,
    val logOutUseCase: LogOutUseCase,
    val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase
)
