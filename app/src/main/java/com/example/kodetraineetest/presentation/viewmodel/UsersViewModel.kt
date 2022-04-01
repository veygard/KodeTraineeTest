package com.example.kodetraineetest.presentation.viewmodel

import android.app.Application
import android.util.Log
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.domain.repository.GetUsersResult
import com.example.kodetraineetest.domain.use_cases.UserUseCases
import com.example.kodetraineetest.presentation.model.ScreenStates
import com.example.kodetraineetest.presentation.model.SnackbarTypes
import com.example.kodetraineetest.presentation.model.SortingTypes
import com.example.kodetraineetest.presentation.model.SortingTypes.ABC
import com.example.kodetraineetest.presentation.model.SortingTypes.BORN_DATE
import com.example.kodetraineetest.util.DepartmentsAccordance
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
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
    private val departmentsAccordance: DepartmentsAccordance
) : ViewModel() {

    private val _userOriginalList: MutableLiveData<List<User>?> = MutableLiveData(null)
    private val _userListToShow: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    val userListToShow: MutableStateFlow<List<User>?> = _userListToShow

    private val _userLisFilteredByTab: MutableStateFlow<List<User>?> = MutableStateFlow(null)

    private val allDepName = application.applicationContext.getString(R.string.department_tab_row_all)
    private val _departmentsSet: MutableStateFlow<Set<String>?> =
        MutableStateFlow(setOf(allDepName))
    val departmentsSet: MutableStateFlow<Set<String>?> = _departmentsSet

    private val _sortedBy = MutableStateFlow(ABC)
    val sortedBy: MutableStateFlow<SortingTypes> = _sortedBy

    private val _showSnackbar: MutableStateFlow<SnackbarTypes?> = MutableStateFlow(null)
    val showSnackbar: StateFlow<SnackbarTypes?>
        get() = _showSnackbar.asStateFlow()

    private val _screenLoadingState: MutableStateFlow<ScreenStates> =
        MutableStateFlow(ScreenStates.Loading)
    val screenLoadingState: StateFlow<ScreenStates>
        get() = _screenLoadingState.asStateFlow()

    private val _filterValue = MutableStateFlow("")

    private val _selectedPositionTabIndex = MutableStateFlow(0)
    val selectedPositionTabIndex:MutableStateFlow<Int> = _selectedPositionTabIndex

    init {
        Log.d("sorting_type", "VM init")
    }

    fun saveTabIndex(position: Int){
        viewModelScope.launch {
            _selectedPositionTabIndex.emit(position)
        }
    }


    fun refresh(isLoadStateNeeded : Boolean = false) {
        viewModelScope.launch {
            _filterValue.emit("")
            if(isLoadStateNeeded) _screenLoadingState.emit(ScreenStates.Loading)
            else {
                _showSnackbar.emit(null) //что бы сработали обсерверы на изменение
                _showSnackbar.emit(SnackbarTypes.Loading)
                Log.d("toast","viewModel.refresh ${_showSnackbar.value}")
            }
            getUsers()
        }
    }

    fun filterUsersBySearch(value: String) {
        viewModelScope.launch {
            val set = mutableSetOf<User>()
            _filterValue.emit(value)

            when {
                value.isEmpty() -> {
                    _userListToShow.emit(_userLisFilteredByTab.value)
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
                    _userListToShow.emit(set.toList())
                }
            }

        }
    }


    fun filterUsersByTabRow(chosen: String, all: String) {
        viewModelScope.launch {
            if (chosen == all) {
                _userListToShow.emit(_userOriginalList.value)
                _userLisFilteredByTab.emit(_userOriginalList.value)
                if(_filterValue.value.isNotEmpty()) { filterUsersBySearch(_filterValue.value) }
            } else {
                _userListToShow.emit(
                    _userOriginalList.value?.filter {
                        it.department == departmentsAccordance.getOriginalName(chosen)
                    })
                _userLisFilteredByTab.emit(_userListToShow.value)
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
            Log.d("sorting_type","sorting sortByType")
            when (type) {
                ABC -> {
                    userListToShow?.let { list ->
                        _userListToShow.emit(
                            list.sortedBy { it.lastName }
                        )
                        sortedBy.emit(ABC)
                    }
                    Log.d("sorting_type","sorting $ABC ")
                }
                BORN_DATE -> {
                    sortedBy.emit(BORN_DATE)
                    Log.d("sorting_type","sorting $BORN_DATE ")
                }
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
            delay(500)

            val result = userUseCases.getUsersUseCase.start()
            when (result) {
                is GetUsersResult.UserList -> {
                    _userOriginalList.value = result.list
                    filterUsersByTabRow(getDepNameByIndex(), all = allDepName)
                    setupTabRowList()
                    _screenLoadingState.emit(ScreenStates.Ready())
                }
                is GetUsersResult.ConnectionError -> {
                    if(_userListToShow.value == null) _screenLoadingState.emit(ScreenStates.Error)
                    else _showSnackbar.emit(SnackbarTypes.ConnectionError)
                }
                else -> {
                    if(_userListToShow.value == null) _screenLoadingState.emit(ScreenStates.Error)
                    else _showSnackbar.emit(SnackbarTypes.ServerError)
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
            _departmentsSet.emit(
                set
            )
        }
    }

    private fun getDepNameByIndex(): String {
        return try {
            departmentsSet.value?.elementAt(_selectedPositionTabIndex.value)!!
        } catch (e: Exception){
            allDepName
        }
    }

    fun clear() {
        _userOriginalList.value = null
        _userListToShow.value = null
    }


}