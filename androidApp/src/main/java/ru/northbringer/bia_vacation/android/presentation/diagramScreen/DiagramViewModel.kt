package ru.northbringer.bia_vacation.android.presentation.diagramScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.GetVacationsUseCase
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task

class DiagramViewModel(private val getVacationsUseCase: GetVacationsUseCase) : ViewModel() {

    private var vacations = MutableLiveData<MutableList<Task>>()

    fun getVacation(): LiveData<MutableList<Task>> {
        return vacations
    }

    fun loadVacations() {
        vacations.value = getVacationsUseCase.execute()
    }
}