package ru.northbringer.bia_vacation.diagramScreen.data.repository

import ru.northbringer.bia_vacation.diagramScreen.data.datasource.VacationDataSource
import ru.northbringer.bia_vacation.diagramScreen.domain.repository.VacationRepository
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task

class VacationRepositoryImpl(private val vacationDataSoure: VacationDataSource) : VacationRepository {
    override fun getVacations(): MutableList<Task> {
        return mutableListOf()
    }
}