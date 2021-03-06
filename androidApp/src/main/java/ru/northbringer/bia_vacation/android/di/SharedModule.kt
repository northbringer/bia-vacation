package ru.northbringer.bia_vacation.android.di

import org.koin.dsl.module
import ru.northbringer.bia_vacation.loginScreen.data.datasource.RemoteUserApi
import ru.northbringer.bia_vacation.loginScreen.data.repository.UserSignInRepositoryImpl
import ru.northbringer.bia_vacation.loginScreen.domain.repository.UserSignInRepository
import ru.northbringer.bia_vacation.loginScreen.domain.usecase.TrySignInUseCase
import ru.northbringer.bia_vacation.loginScreen.domain.usecase.ValidatePasswordUseCase
import ru.northbringer.bia_vacation.loginScreen.domain.usecase.ValidateUserLoginUseCase

val sharedModule = module {

    // ======= Domain =========================================================


    // LoginFragment
    factory<ValidateUserLoginUseCase> {
        ValidateUserLoginUseCase()
    }

    // LoginFragment
    factory<ValidatePasswordUseCase> {
        ValidatePasswordUseCase()
    }

    // LoginFragment
    factory<TrySignInUseCase> {
        TrySignInUseCase(userSignInRepository = get())
    }

    // ======= Domain =========================================================



    // ======= Data ===========================================================

    // LoginFragment
    single<UserSignInRepository> {
        UserSignInRepositoryImpl(remoteUserApi = get())
    }

    // LoginFragment
    single<RemoteUserApi> {
        RemoteUserApi.create()
    }

    // ======= Data ===========================================================

}