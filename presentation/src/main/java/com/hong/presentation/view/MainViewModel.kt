package com.hong.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hong.base.presentation.BaseViewModel
import com.hong.base.presentation.util.ioToMain
import com.hong.domain.sample.usecase.GetUserUseCase
import com.hong.presentation.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    private val _userList = MutableLiveData<List<UserData>>()
    val userList: LiveData<List<UserData>> get() = _userList

    init {
        getUser()
    }

    private fun getUser() {
        getUserUseCase()
            .map { it.map(UserData::toPresentationModel) }
            .ioToMain()
            .onErrorComplete()
            .subscribeBy {
                if (it.isEmpty()) return@subscribeBy
                _userList.value = it
            }
            .addToDisposable()

    }
}