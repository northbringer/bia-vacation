package ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen

import android.text.Spannable
import android.text.SpannableString

data class Employee(val name: Spannable) {
        constructor(name: String) : this(SpannableString(name)){
        }
}