package ru.northbringer.bia_vacation.diagramScreen.domain.usecase

import android.annotation.SuppressLint
import java.time.LocalDate

class GetVacationsUseCase {

    @SuppressLint("NewApi")
    fun execute(): MutableList<Task> {
        val tasks: MutableList<Task> = mutableListOf(
            Task(
                name = "Вася П",
                dateStart = LocalDate.of(2022, 4, 10),
                dateEnd = LocalDate.of(2022, 4, 29)
            ) ,
            Task(
                name = "Дима Н",
                dateStart = LocalDate.of(2022, 5, 10),
                dateEnd = LocalDate.of(2022, 5, 12)
            ),
            Task(
                name = "Анжела Х",
                dateStart = LocalDate.of(2022, 4, 1),
                dateEnd = LocalDate.of(2022, 4, 10)
            ),
            Task(
                name = "Богдан Б",
                dateStart = LocalDate.of(2022, 4, 5),
                dateEnd = LocalDate.of(2022, 4, 10)
            ),
            Task(
                name = "Данил М",
                dateStart = LocalDate.of(2022, 6, 2),
                dateEnd = LocalDate.of(2022, 6, 20)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate.of(2022, 7, 3),
                dateEnd = LocalDate.of(2022, 7, 5)
            ),

            Task(
                name = "Артурка П",
                dateStart = LocalDate.of(2022, 7, 3),
                dateEnd = LocalDate.of(2022, 7, 19)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate.of(2022, 1, 1),
                dateEnd = LocalDate.of(2022, 1, 20)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate.of(2022, 1, 2),
                dateEnd = LocalDate.of(2022, 1, 19)
            ),
            Task(
                name = "Артурка П",
                dateStart = LocalDate.of(2022, 1, 4),
                dateEnd = LocalDate.of(2022, 2, 9)
            )
        )
        val list: MutableList<Task> = mutableListOf()

        tasks.forEach { task ->
            list.addAll(separateRange(task))
        }
        return list
    }

    @SuppressLint("NewApi")
    private fun separateRange(task: Task): List<Task> {
        val list: MutableList<Task> = mutableListOf()
        var start = task.dateStart
        val end = task.dateEnd
        if (start.monthValue != end.monthValue) {
            while(start.monthValue != end.monthValue) {
                val newEnd = LocalDate.of(start.year, start.month, start.month.length(start.isLeapYear))
                list.add(Task(task.name, start, newEnd))
                start = newEnd.plusDays(1)
            }
            list.add(Task(task.name, start, end))
        } else {
            list.add(task)
        }
        return list
    }
}