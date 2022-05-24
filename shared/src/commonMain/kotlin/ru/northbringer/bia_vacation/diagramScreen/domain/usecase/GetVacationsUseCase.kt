package ru.northbringer.bia_vacation.diagramScreen.domain.usecase

import io.ktor.utils.io.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus


class GetVacationsUseCase {

    fun execute(): MutableList<Task> {
        val tasks: MutableList<Task> = mutableListOf(
            Task(
                name = "Вася П",
                dateStart = LocalDate(2022, 4, 10),
                dateEnd = LocalDate(2022, 4, 29)
            ) ,
            Task(
                name = "Дима Н",
                dateStart = LocalDate(2022, 5, 10),
                dateEnd = LocalDate(2022, 5, 12)
            ),
            Task(
                name = "Анжела Х",
                dateStart = LocalDate(2022, 4, 1),
                dateEnd = LocalDate(2022, 4, 10)
            ),
            Task(
                name = "Богдан Б",
                dateStart = LocalDate(2022, 4, 5),
                dateEnd = LocalDate(2022, 4, 10)
            ),
            Task(
                name = "Данил М",
                dateStart = LocalDate(2022, 6, 2),
                dateEnd = LocalDate(2022, 6, 20)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate(2022, 7, 3),
                dateEnd = LocalDate(2022, 7, 5)
            ),

            Task(
                name = "Артурка П",
                dateStart = LocalDate(2022, 7, 3),
                dateEnd = LocalDate(2022, 7, 19)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate(2022, 1, 1),
                dateEnd = LocalDate(2022, 1, 20)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate(2022, 1, 2),
                dateEnd = LocalDate(2022, 1, 19)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate(2022, 1, 4),
                dateEnd = LocalDate(2022, 2, 9)
            )
        )
        val list: MutableList<Task> = mutableListOf()

        tasks.forEach { task ->
            list.addAll(separateRange(task))
        }
        return list
    }

    private fun separateRange(task: Task): List<Task> {
        val list: MutableList<Task> = mutableListOf()
        var start = task.dateStart
        val end = task.dateEnd
        if (start.monthNumber != end.monthNumber) {
            while(start.monthNumber != end.monthNumber) {
                val newEnd = start.plus(1, DateTimeUnit.MONTH).minus(start.dayOfMonth, DateTimeUnit.DAY)
                list.add(Task(task.name, start, newEnd))
                start = newEnd.plus(1, DateTimeUnit.DAY)
            }
            list.add(Task(task.name, start, end))
        } else {
            list.add(task)
        }
        return list
    }
}