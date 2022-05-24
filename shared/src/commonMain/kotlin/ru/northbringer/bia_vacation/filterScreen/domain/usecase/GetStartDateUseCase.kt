package ru.northbringer.bia_vacation.filterScreen.domain.usecase

import android.content.Context
import kotlinx.datetime.LocalDate
import ru.northbringer.bia_vacation.data.KmmContext
import ru.northbringer.bia_vacation.data.KmmSharedPref
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task


class GetStartDateUseCase(context: KmmContext) {
    val pref = KmmSharedPref(context)
    fun execute(): String {
        val dateString = if (pref.getString("startDate") != null) "2022-11-11" else "2022-12-12"
        return dateString
    }
}