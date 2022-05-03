package ru.northbringer.bia_vacation.diagramScreen.domain.repository

import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task

interface VacationRepository {
    fun getVacations(): MutableList<Task>
}