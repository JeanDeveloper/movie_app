package com.jchunga.movieapp.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.movieapp.domain.entities.AuthUseCases
import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.login.LoginEvent
import com.jchunga.movieapp.domain.entities.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val authUseCase: AuthUseCases
) : ViewModel(){

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    private fun login(email:String, password:String){
        try {
            viewModelScope.launch {
                _state.value = LoginState.Loading
                when(val result = authUseCase.signInWithEmailAndPasswordUseCase(email, password)){
                    is Either.Left -> {
                        _state.value = LoginState.Error(result.value.toString())
                    }
                    is Either.Right -> {
                        _state.value = LoginState.Success(authUser = result.value)
                    }
                }
            }
        } catch (e: Exception) {
            _state.value = LoginState.Error(e.toString())
        }
    }

    private fun logOut(){
        authUseCase.logOutUseCase()
    }

    fun resetState(){
        _state.value = LoginState.Idle
    }

    fun onEvent(event: LoginEvent){
        when(event) {
            is LoginEvent.Submit -> {
                login(event.email, event.password)
            }
            is LoginEvent.LogOut -> {
                logOut()
            }
        }
    }

}

