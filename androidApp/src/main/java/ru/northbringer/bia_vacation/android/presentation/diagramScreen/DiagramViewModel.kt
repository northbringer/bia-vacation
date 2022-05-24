package ru.northbringer.bia_vacation.android.presentation.diagramScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.GetVacationsUseCase
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task

class DiagramViewModel(private val getVacationsUseCase: GetVacationsUseCase) : ViewModel() {

    private var _vacations = MutableLiveData<MutableList<Task>>()
    var vacations: LiveData<MutableList<Task>> = _vacations

    fun loadVacations() {
        _vacations.value = getVacationsUseCase.execute()
    }
}