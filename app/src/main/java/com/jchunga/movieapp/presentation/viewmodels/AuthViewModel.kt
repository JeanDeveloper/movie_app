package com.jchunga.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.jchunga.movieapp.domain.entities.user.AuthUser
import com.jchunga.movieapp.domain.usecases.auth.GetAuthStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase
): ViewModel() {
    private val _user = MutableStateFlow<AuthUser?>(getAuthStateUseCase.getCurrentUser())
    val user : StateFlow<AuthUser?> = _user.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(getAuthStateUseCase.isUserAuthenticated())
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    fun refreshAuthState() {
        _user.value = getAuthStateUseCase.getCurrentUser()
        _isAuthenticated.value = getAuthStateUseCase.isUserAuthenticated()
    }

}