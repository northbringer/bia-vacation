package ru.northbringer.bia_vacation.data

const val SP_NAME = "kmm_app"

actual fun KmmContext.putInt(key: String, value: Int) {
    getSpEditor().putInt(key, value).apply()
}

actual fun KmmContext.getInt(key: String, default: Int): Int {
    return  getSp().getInt(key, default )
}

actual fun KmmContext.putLong(key: String, value: Long) {
    getSpEditor().putLong(key, value).apply()
}

actual fun KmmContext.getLong(key: String, default: Long): Long {
    return  getSp().getLong(key, default )
}

actual fun KmmContext.putString(key: String, value: String) {
    getSpEditor().putString(key, value).apply()
}

actual fun KmmContext.getString(key: String): String? {
    return  getSp().getString(key, null)
}

actual fun KmmContext.putBool(key: String, value: Boolean) {
    getSpEditor().putBoolean(key, value).apply()
}

actual fun KmmContext.getBool(key: String, default: Boolean): Boolean {
    return getSp().getBoolean(key, default)
}

private fun KmmContext.getSp() = getSharedPreferences(SP_NAME, 0)

private fun KmmContext.getSpEditor() = getSp().edit()