package ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task

class EmployeeFilterViewModel: ViewModel() {

    private var _employees = MutableLiveData<ArrayList<Employee>>()
    var employees: LiveData<ArrayList<Employee>> = _employees

    fun loadEmployees() {
        val tom = Employee("Tom")
        val jerry = Employee("Jerry")
        val donald = Employee("Donald")
        val users = arrayListOf(tom, jerry, donald)
        _employees.value = users
    }

    fun onSelectAllCheckBoxClick() {

    }
}