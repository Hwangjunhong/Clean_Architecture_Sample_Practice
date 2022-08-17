package com.hong.presentation.view

import androidx.lifecycle.viewModelScope
import com.hong.domain.sample.entity.UserEntity
import com.hong.domain.sample.usecase.GetUserUseCase
import com.hong.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Init)
    val state: StateFlow<MainState> get() = _state

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase.invoke()
                .onStart { MainState.IsLoading(true) }
                .onEach { _state.value = MainState.SuccessGetUser(it) }
                .onCompletion { MainState.IsLoading(false) }
                .catch { exception -> Timber.e(exception) }
                .collect()
        }

    }
}


sealed class MainState {
    object Init : MainState()
    data class SuccessGetUser(val userEntity: List<UserEntity>) : MainState()
    data class IsLoading(val isLoading: Boolean) : MainState()
}