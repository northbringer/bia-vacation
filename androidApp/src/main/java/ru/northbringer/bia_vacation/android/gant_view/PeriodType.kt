package ru.northbringer.bia_vacation.android.gant_view

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import java.time.temporal.IsoFields


enum class PeriodType {
    DAY {
        override fun increment(date: LocalDate): LocalDate {
            return date.plus(1, DateTimeUnit.DAY)
        }

        override fun getDateString(date: LocalDate): String {
            return date.dayOfMonth.toString()
        }

        override fun getPercentOfPeriods(date: LocalDate): Float {
            return 0f
        }
    };

    abstract fun increment(date: LocalDate): LocalDate

    abstract fun getDateString(date: LocalDate): String

    abstract fun getPercentOfPeriods(date: LocalDate): Float
}