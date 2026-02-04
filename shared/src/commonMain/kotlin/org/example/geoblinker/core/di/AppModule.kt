package org.example.geoblinker.core.di

import org.koin.dsl.module
import org.example.geoblinker.presentation.features.device_list.ListScreenViewModel
import org.example.geoblinker.core.util.AppStrings
import org.example.geoblinker.core.util.AppStringsRu

val appModule = module {
    // Предоставляем строки
    single<AppStrings> { AppStringsRu() }
    
    // Регистрируем ViewModel для списка устройств
    factory { ListScreenViewModel() }
}
