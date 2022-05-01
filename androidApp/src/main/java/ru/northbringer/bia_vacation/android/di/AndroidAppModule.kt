package ru.northbringer.bia_vacation.android.di

import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import ru.northbringer.bia_vacation.android.presentation.loginScreen.LoginViewModel
import ru.northbringer.bia_vacation.android.presentation.mainScreen.MainViewModel

val appModule = module {

    viewModel<LoginViewModel> {
        LoginViewModel(
            validateUserLoginUseCase = get(),
            validatePasswordUseCase = get(),
            trySignInUseCase = get()
        )
    }

    viewModel<MainViewModel> {
        MainViewModel()
    }
}