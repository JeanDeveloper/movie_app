package com.jchunga.movieapp.domain.entities.login

import com.jchunga.movieapp.domain.entities.user.AuthUser

sealed class LoginState {
    data object Idle: LoginState()
    data object Loading: LoginState()
    data class Success(var authUser: AuthUser) : LoginState()
    data class Error(val message: String) : LoginState()
}
