package ru.northbringer.bia_vacation

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}