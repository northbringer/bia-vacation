package ru.northbringer.bia_vacation.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.northbringer.bia_vacation.android.presentation.diagramScreen.DiagramViewModel
import ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen.EmployeeFilterViewModel
import ru.northbringer.bia_vacation.android.presentation.filterScreen.FilterViewModel

val appModule = module {
    viewModel<DiagramViewModel> {
        DiagramViewModel(getVacationsUseCase = get())
    }

    viewModel<FilterViewModel> {
        FilterViewModel(getStartDateUseCase = get())
    }

    viewModel<EmployeeFilterViewModel> {
        EmployeeFilterViewModel()
    }
}
//import org.koin.androidx.viewmodel.dsl.viewModel

//import org.koin.dsl.module
//import ru.northbringer.bia_vacation.android.presentation.loginScreen.LoginViewModel
//import ru.northbringer.bia_vacation.android.presentation.mainScreen.MainViewModel

/*
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
}*/
