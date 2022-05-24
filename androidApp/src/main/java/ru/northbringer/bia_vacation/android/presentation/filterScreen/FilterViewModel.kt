package ru.northbringer.bia_vacation.android.presentation.filterScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.datetime.LocalDate
import ru.northbringer.bia_vacation.filterScreen.domain.api.Network
import ru.northbringer.bia_vacation.filterScreen.domain.usecase.GetStartDateUseCase

class FilterViewModel(private val getStartDateUseCase: GetStartDateUseCase) : ViewModel() {

    private var startDate: String = ""
    private var endDate: String = ""
    var test = MutableLiveData<Double>()
    val net = Network()

    private var _period = MutableLiveData<String>()
    var period: LiveData<String> = _period


    fun loadStartDate() {
        startDate = getStartDateUseCase.execute()
        endDate = getStartDateUseCase.execute()
    }

    fun loadData() {
        net.getData {
            test.value = it.daysLeft
        }
    }
}