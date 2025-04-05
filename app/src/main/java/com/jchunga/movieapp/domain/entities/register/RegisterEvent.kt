package com.jchunga.movieapp.domain.entities.register


sealed class RegisterEvent {
    data class Submit(val email:String, val password:String, val name: String): RegisterEvent()

}