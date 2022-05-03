package ru.northbringer.bia_vacation.diagramScreen.domain.usecase

import java.time.LocalDate

data class Task(
    val name: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
)