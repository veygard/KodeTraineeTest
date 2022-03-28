package com.example.kodetraineetest.presentation.xml.viewmodel

import android.app.Application
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.domain.repository.GetUsersResult
import com.example.kodetraineetest.domain.use_cases.UserUseCases
import com.example.kodetraineetest.presentation.common.supports.ScreenStates
import com.example.kodetraineetest.presentation.common.supports.ScreenStates.Loading
import com.example.kodetraineetest.presentation.common.supports.SnackbarTypes
import com.example.kodetraineetest.presentation.common.supports.SortingTypes
import com.example.kodetraineetest.presentation.common.supports.SortingTypes.ABC
import com.example.kodetraineetest.presentation.common.supports.SortingTypes.BORN_DATE
import com.example.kodetraineetest.util.DepartmentsAccordance
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModelXml @Inject constructor(
    private val application: Application,
    private val userUseCases: UserUseCases,
    private val departmentsAccordance: DepartmentsAccordance
) : ViewModel() {

    private val _userOriginalList: MutableLiveData<List<User>?> = MutableLiveData(null)
    private val _userListToShow: MutableLiveData<List<User>?> = MutableLiveData(null)
    val userListToShow: LiveData<List<User>?> = _userListToShow

    private val _userLisFilteredByTab: MutableLiveData<List<User>?> = MutableLiveData(null)

    private val allDepName = application.applicationContext.getString(R.string.department_tab_row_all)
    private val _departmentsSet: MutableLiveData<Set<String>?> =
        MutableLiveData(setOf(allDepName))
    val departmentsSet: LiveData<Set<String>?> = _departmentsSet

    private val _sortedBy = MutableLiveData(ABC)
    val sortedBy: LiveData<SortingTypes> = _sortedBy

    private val _showSnackbar: MutableLiveData<SnackbarTypes?> = MutableLiveData(null)
    val showSnackbar: LiveData<SnackbarTypes?> = _showSnackbar

    private val _screenLoadingState: MutableLiveData<ScreenStates> =
        MutableLiveData(Loading)
    val screenLoadingState: LiveData<ScreenStates> = _screenLoadingState

    private val _filterValue = MutableLiveData("")

    private val _selectedPositionTabIndex = MutableLiveData(0)
    val selectedPositionTabIndex:LiveData<Int> = _selectedPositionTabIndex

    fun saveTabIndex(position: Int){
        viewModelScope.launch {
            _selectedPositionTabIndex.value = position
        }
    }
    fun getDepNameByIndex(): String {
        return try {
           departmentsSet.value?.elementAt(_selectedPositionTabIndex.value)!!
        } catch (e: Exception){
            allDepName
        }
    }

    fun refresh(isLoadStateNeeded : Boolean = false) {
        viewModelScope.launch {
            _filterValue.value = ""
            if(isLoadStateNeeded) _screenLoadingState.value = Loading
            else _showSnackbar.value = SnackbarTypes.Loading
            getUsers()
        }
    }

    fun filterUsersBySearch(value: String) {
        viewModelScope.launch {
            val set = mutableSetOf<User>()
            _filterValue.value = value

            when {
                value.isEmpty() -> {
                    _userListToShow.value = _userLisFilteredByTab.value
                }
                else -> {
                    _userLisFilteredByTab.value?.forEach { user ->
                        when {
                            (user.firstName?.contains(value, ignoreCase = true) == true) ||
                                    (user.lastName?.contains(value, ignoreCase = true) == true) ||
                                    (user.userTag?.contains(value, ignoreCase = true) == true)
                            -> {
                                set.add(user)
                            }
                        }
                    }
                    _userListToShow.value = set.toList()
                }
            }

        }
    }


    fun filterUsersByTabRow(chosen: String, all: String) {
        viewModelScope.launch {
            if (chosen == all) {
                _userListToShow.value = _userOriginalList.value
                _userLisFilteredByTab.value = _userOriginalList.value
                if(_filterValue.value.isNotEmpty()) { filterUsersBySearch(_filterValue.value) }
            } else {
                _userListToShow.value =
                    _userOriginalList.value?.filter {
                        it.department == departmentsAccordance.getOriginalName(chosen)
                    }
                _userLisFilteredByTab.value = _userListToShow.value
                if(_filterValue.value.isNotEmpty()) { filterUsersBySearch(_filterValue.value) }

                when (_sortedBy.value) {
                    ABC -> {
                        sortByType(ABC)
                    }
                }
            }
        }
    }


    fun sortByType(type: SortingTypes) {
        viewModelScope.launch {
            val userListToShow = _userListToShow.value

            when (type) {
                ABC -> {
                    userListToShow?.let { list ->
                        _userListToShow.value=
                            list.sortedBy { it.lastName }

                        _sortedBy.value = ABC
                    }
                }
                BORN_DATE -> {
                    _sortedBy.value = BORN_DATE
                }
            }
        }
    }

    fun loadingError() {
        viewModelScope.launch {
            _screenLoadingState.value = ScreenStates.Error
        }
    }

    fun getUsers() {
        viewModelScope.launch {

            val result = userUseCases.getUsersUseCase.start()
            when (result) {
                is GetUsersResult.UserList -> {
                    _userOriginalList.value = result.list
                    filterUsersByTabRow(getDepNameByIndex(), all = allDepName)
                    setupTabRowList()
                    delay(500)
                    _screenLoadingState.value = ScreenStates.Ready
                }
                is GetUsersResult.ConnectionError -> {
                    if(_userListToShow.value == null) _screenLoadingState.value = ScreenStates.Error
                    else _showSnackbar.value = SnackbarTypes.ConnectionError
                }
                else -> {
                    if(_userListToShow.value == null) _screenLoadingState.value = ScreenStates.Error
                    else _showSnackbar.value = SnackbarTypes.ServerError
                }
            }
        }
    }

    private fun setupTabRowList() {
        viewModelScope.launch {
            val set = mutableSetOf<String>()
            set.add(application.applicationContext.getString(R.string.department_tab_row_all))
            _userOriginalList.value?.map { it.department }?.forEach {
                it?.let { dep -> set.add(departmentsAccordance.getAccordanceName(dep)) }
            }
            _departmentsSet.value = set

        }
    }



    fun clear() {
        _userOriginalList.value = null
        _userListToShow.value = null
    }


}