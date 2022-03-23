package com.example.kodetraineetest.presentation.viewmodel

import android.app.Application
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.domain.repository.GetUsersResult
import com.example.kodetraineetest.domain.use_cases.UserUseCases
import com.example.kodetraineetest.presentation.viewmodel.supports.ScreenStates
import com.example.kodetraineetest.presentation.viewmodel.supports.SortingTypes
import com.example.kodetraineetest.presentation.viewmodel.supports.SortingTypes.*
import com.example.kodetraineetest.util.DepartmentsAccordance
import com.example.kodetraineetest.util.extention.toLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val application: Application,
    private val userUseCases: UserUseCases,
    private val departmentsAccordance: DepartmentsAccordance
) : BaseViewModel() {

    private val _userOriginalList: MutableLiveData<List<User>?> = MutableLiveData(null)
    private val _userListToShow: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    val userListToShow: MutableStateFlow<List<User>?> = _userListToShow

    private val _departmentsSet: MutableStateFlow<Set<String>?> =
        MutableStateFlow(setOf(application.applicationContext.getString(R.string.detartment_tab_row_all)))
    val departmentsSet: MutableStateFlow<Set<String>?> = _departmentsSet

    private val _sortedBy = MutableStateFlow(ABC)
    val sortedBy: MutableStateFlow<SortingTypes> = _sortedBy


    private val _screenLoadingState: MutableStateFlow<ScreenStates> =
        MutableStateFlow(ScreenStates.Loading)

    private val _userListWithBDayGroups: MutableStateFlow<Map<String, MutableSet<User>>> =
        MutableStateFlow(
            mapOf(
                LocalDate.now().year.toString() to mutableSetOf(),
                LocalDate.now().year.plus(1).toString() to mutableSetOf(),
            )
        )
    val userListWithBDayGroups: MutableStateFlow<Map<String, MutableSet<User>>> =
        _userListWithBDayGroups

    val screenLoadingState: StateFlow<ScreenStates>
        get() = _screenLoadingState.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _departmentsSet.emit(setOf(application.applicationContext.getString(R.string.detartment_tab_row_all)))
            _sortedBy.emit(ABC)
            _screenLoadingState.emit(ScreenStates.Loading)
            getUsers()
        }
    }


    fun sortByTabRow(chosen: String, all: String) {
        viewModelScope.launch {
            if (chosen == all) {
                when (_sortedBy.value) {
                    ABC -> {
                        _userListToShow.emit(_userOriginalList.value)
                        sortByType(ABC)
                    }
                    BORN_DATE -> {
                        _userListToShow.emit(_userOriginalList.value)
                        setupYearGroups(_userListWithBDayGroups, _userListToShow)
                    }
                }

            } else {
                _userListToShow.emit(
                    _userOriginalList.value?.filter {
                        it.department == departmentsAccordance.getOriginalName(chosen)
                    })
                when (_sortedBy.value) {
                    ABC -> {
                        sortByType(ABC)
                    }
                    BORN_DATE -> {
                        setupYearGroups(_userListWithBDayGroups, _userListToShow)
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
                        _userListToShow.emit(
                            list.sortedBy { it.lastName }
                        )
                        sortedBy.emit(ABC)
                    }
                }
                BORN_DATE -> {
                    setupYearGroups(_userListWithBDayGroups, _userListToShow)
                    sortedBy.emit(BORN_DATE)
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
                    _userListToShow.value = result.list
                    setupTabRowList()
                    sortByType(ABC)
                    _screenLoadingState.emit(ScreenStates.Ready)
                }
                is GetUsersResult.ConnectionError -> {
                    _screenLoadingState.emit(ScreenStates.Error)
                }
                else -> {
                    _screenLoadingState.emit(ScreenStates.Error)
                }
            }
        }
    }

    private fun setupTabRowList() {
        viewModelScope.launch {
            val set = mutableSetOf<String>()
            set.add(application.applicationContext.getString(R.string.detartment_tab_row_all))
            _userOriginalList.value?.map { it.department }?.forEach {
                it?.let { dep -> set.add(departmentsAccordance.getAccordanceName(dep)) }
            }
            _departmentsSet.emit(
                set
            )
        }
    }

    private fun setupYearGroups(
        birthdayYearGroup: MutableStateFlow<Map<String, MutableSet<User>>>,
        list: MutableStateFlow<List<User>?>
    ) {
        birthdayYearGroup.value.values.forEach {
            it.clear()
        }

        list.value?.forEach { user ->
            val date = user.birthday?.toLocalDate()
            date?.let { d ->
                val day = d.dayOfMonth
                val month = d.month
                val year = LocalDate.now().year
                val newDate = LocalDate.of(year, month, day)

                if (newDate.isAfter(LocalDate.now())) {
                    birthdayYearGroup.value[LocalDate.now().year.toString()]?.add(user)
                } else {
                    birthdayYearGroup.value[LocalDate.now().year.plus(1).toString()]?.add(user)
                }
            }
        }

        birthdayYearGroup.value.values.forEach { set ->
            set.sortedWith(
                compareBy(
                    { it.birthday?.toLocalDate()?.month },
                    { it.birthday?.toLocalDate()?.dayOfMonth },
                )
            )
        }
    }


    override fun clear() {
        _userOriginalList.value = null
        _userListToShow.value = null
    }


}