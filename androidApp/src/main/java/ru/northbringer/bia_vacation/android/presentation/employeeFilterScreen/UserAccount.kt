package ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen

import java.io.Serializable

class UserAccount: Serializable {
    var userName: String
    var userType: String
    var isActive: Boolean

    constructor(userName: String, userType: String) {
        this.userName = userName
        this.userType = userType
        isActive = true
    }

    constructor(userName: String, userType: String, active: Boolean) {
        this.userName = userName
        this.userType = userType
        isActive = active
    }

    override fun toString(): String {
        return userName + " (" + userType + ")"
    }
}