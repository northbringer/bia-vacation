package ru.northbringer.bia_vacation.android.gant_view

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.temporal.IsoFields

@SuppressLint("NewApi")
enum class PeriodType {
    MONTH {
        override fun increment(date: LocalDate): LocalDate = date.plusMonths(1)

        override fun getDateString(date: LocalDate): String = date.month.name

        override fun getPercentOfPeriods(date: LocalDate) =
            (date.dayOfMonth - 1f) / date.lengthOfMonth()

    },
    WEEK {
        override fun increment(date: LocalDate): LocalDate = date.plusWeeks(1)

        override fun getDateString(date: LocalDate): String =
            date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR).toString()

        override fun getPercentOfPeriods(date: LocalDate) = (date.dayOfWeek.value - 1f) / 7
    },
    DAY {
        override fun increment(date: LocalDate): LocalDate {
            return date.plusDays(1)
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