package ru.northbringer.bia_vacation.data

expect class KmmContext

expect fun KmmContext.putInt(key: String, value: Int)

expect fun KmmContext.getInt(key: String, default: Int): Int

expect fun KmmContext.putLong(key: String, value: Long)

expect fun KmmContext.getLong(key: String, default: Long): Long

expect fun KmmContext.putString(key: String, value: String)

expect fun KmmContext.getString(key: String) : String?

expect fun KmmContext.putBool(key: String, value: Boolean)

expect fun KmmContext.getBool(key: String, default: Boolean): Boolean