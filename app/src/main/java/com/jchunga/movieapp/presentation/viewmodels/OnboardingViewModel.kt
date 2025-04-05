package com.jchunga.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.movieapp.domain.usecases.preferences.GetOnboardingStateUseCase
import com.jchunga.movieapp.domain.usecases.preferences.SaveOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingStateUseCase: GetOnboardingStateUseCase,
    private val saveOnboardingUseCase: SaveOnboardingUseCase
) : ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow<Boolean?>(null)
    val onboardingCompleted : StateFlow<Boolean?> = _onBoardingCompleted

    init {
        viewModelScope.launch {
            getOnboardingStateUseCase().collect { isCompleted ->
                _onBoardingCompleted.value = isCompleted
            }
        }
    }

    fun saveOnboardingCompleted(){
        viewModelScope.launch {
            saveOnboardingUseCase(true)
        }
    }

}