package com.jchunga.movieapp.domain.entities.register

import com.jchunga.movieapp.domain.entities.user.AuthUser

sealed class RegisterState {
    data object Idle: RegisterState()
    data object Loading: RegisterState()
    data class Success(var user: AuthUser) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
