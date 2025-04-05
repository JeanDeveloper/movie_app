package com.jchunga.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.movieapp.domain.entities.AuthUseCases
import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.login.LoginState
import com.jchunga.movieapp.domain.entities.register.RegisterEvent
import com.jchunga.movieapp.domain.entities.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCases
) : ViewModel(){

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> = _state

    fun signUp(email:String, password:String, name: String) = viewModelScope.launch {
        _state.value = RegisterState.Loading
        when(val result = authUseCase.signUpWithEmailAndPasswordUseCase(email, password, name)){
            is Either.Right -> {
                _state.value = RegisterState.Success(result.value)
            }
            is Either.Left -> {
                _state.value = RegisterState.Error(result.value.toString())
            }
        }
    }

    fun resetState(){
        _state.value = RegisterState.Idle
    }

    fun onEvent(event: RegisterEvent){
        when(event) {
            is RegisterEvent.Submit -> {
                signUp(event.email, event.password, event.name)
            }
        }
    }

}