package com.example.kodetraineetest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.domain.repository.GetUsersResult
import com.example.kodetraineetest.domain.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _userState: MutableStateFlow<UserViewModelState?> = MutableStateFlow(null)
    val userState: StateFlow<UserViewModelState?> = _userState

    private val _userOriginalList: MutableLiveData<List<User>?> = MutableLiveData(null)
    private val _userListToShow: MutableLiveData<List<User>?> = MutableLiveData(null)
    val userListToShow: LiveData<List<User>?> = _userListToShow

    fun getUsers() {
        viewModelScope.launch {
            val result = userUseCases.getUsersUseCase.start()
            when (result) {
                is GetUsersResult.UserList -> {
                    _userState.value = UserViewModelState.GotUsers(result.list)
                    _userOriginalList.value = result.list
                    _userListToShow.value = result.list
                }
                is GetUsersResult.ConnectionError -> _userState.value =
                    UserViewModelState.ConnectionError(result.error)
                else -> _userState.value = UserViewModelState.ServerError(result.error)
            }
        }
    }

}