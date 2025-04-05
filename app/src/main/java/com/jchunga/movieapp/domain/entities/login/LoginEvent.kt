package com.jchunga.movieapp.domain.entities.login

sealed class LoginEvent{
    data class Submit(val email:String, val password:String): LoginEvent()
    data object LogOut: LoginEvent()
}
