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
    factory { AuthPhoneViewModel(get()) }
    factory { AuthConfirmViewModel(get()) }
    factory { MapMainViewModel(get()) }
    factory { MapViewModel() }
    factory { DeviceDetailsViewModel() }
    factory { DeviceSignalsViewModel() }
    factory { DeviceDetachViewModel() }
    factory { ProfileMainViewModel() }
    factory { ProfileEditViewModel() }
    factory { AppSettingsViewModel() }
    factory { AddDeviceSearchViewModel() }
    factory { AddDeviceManualViewModel() }
    factory { AddDeviceSuccessViewModel() }
    factory { GeofenceListViewModel() }
    factory { GeofenceListViewModel() }
}
