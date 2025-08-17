package com.dbajaj.whatsappclone.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Splash : Routes()

    @Serializable
    data object Welcome : Routes()

    @Serializable
    data object UserRegistration : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Updates : Routes()

    @Serializable
    data object Communities : Routes()

    @Serializable
    data object Calls : Routes()

    @Serializable
    data object UserProfile : Routes()


}