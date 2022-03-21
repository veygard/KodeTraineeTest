package com.example.kodetraineetest.presentation.viewmodel

import android.app.Application
import android.content.Context
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.domain.repository.GetUsersResult
import com.example.kodetraineetest.domain.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val application: Application,
    private val userUseCases: UserUseCases,
) : BaseViewModel() {
    private val _userState: MutableStateFlow<UserViewModelState?> = MutableStateFlow(null)
    val userState: StateFlow<UserViewModelState?> = _userState

    private val _userOriginalList: MutableLiveData<List<User>?> = MutableLiveData(null)
    private val _userListToShow: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    val userListToShow: MutableStateFlow<List<User>?> = _userListToShow

    private val _positionSet: MutableStateFlow<Set<String>?> = MutableStateFlow(null)
    val positionSet: MutableStateFlow<Set<String>?> = _positionSet

    private val _screenLoadingState: MutableStateFlow<ScreenStates> =
        MutableStateFlow(ScreenStates.Loading)

    val screenLoadingState: StateFlow<ScreenStates>
        get() = _screenLoadingState.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _screenLoadingState.emit(ScreenStates.Loading)
            getUsers()
        }
    }

    fun sortByTabRow(chosen: String, all: String) {
        viewModelScope.launch {
            if (chosen == all) {
                _userListToShow.emit(_userOriginalList.value)
            } else {
                _userListToShow.emit(_userOriginalList.value?.filter { it.position == chosen })
            }
        }
    }

    fun loadingError() {
        viewModelScope.launch {
            _screenLoadingState.emit(ScreenStates.Error)
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            /*задержка для того чтобы показать работу шиммер*/
            delay(1500)

            val result = userUseCases.getUsersUseCase.start()
            when (result) {
                is GetUsersResult.UserList -> {
                    _userState.value = UserViewModelState.GotUsers(result.list)
                    _userOriginalList.value = result.list
                    _userListToShow.value = result.list
                    setupTabRowList()
                    _screenLoadingState.emit(ScreenStates.Ready)
                }
                is GetUsersResult.ConnectionError -> {
                    _userState.value =
                        UserViewModelState.ConnectionError(result.error)
                    _screenLoadingState.emit(ScreenStates.Error)
                }
                else -> {
                    _userState.value = UserViewModelState.ServerError(result.error)
                    _screenLoadingState.emit(ScreenStates.Error)
                }
            }
        }
    }

    fun setupTabRowList() {
        viewModelScope.launch {
            val set = mutableSetOf<String>()
            set.add(application.applicationContext.getString(R.string.detartment_tab_row_all))
            _userOriginalList.value?.map { it.position }?.forEach {
                it?.let { position -> set.add(position) }
            }
            _positionSet.emit(
                set
            )
        }
    }

    override fun clear() {
        _userState.value = null
        _userOriginalList.value = null
        _userListToShow.value = null
    }

}