package ru.northbringer.bia_vacation.diagramScreen.domain.usecase

import kotlinx.datetime.LocalDate

data class Task(
    val name: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
)