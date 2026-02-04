package org.example.geoblinker.presentation.navigation

sealed class ScreenRoute(val route: String) {
    object List : ScreenRoute("device_list")
    object Details : ScreenRoute("device_details/{imei}")
    object Settings : ScreenRoute("settings")
    object Map : ScreenRoute("map")
}
