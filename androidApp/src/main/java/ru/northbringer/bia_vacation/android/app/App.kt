package ru.northbringer.bia_vacation.android.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.northbringer.bia_vacation.android.di.appModule
import ru.northbringer.bia_vacation.android.di.sharedModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO Дописать инициализацию Koin

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, sharedModule))
        }
    }
}