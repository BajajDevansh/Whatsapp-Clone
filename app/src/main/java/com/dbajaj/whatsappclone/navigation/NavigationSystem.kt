package com.dbajaj.whatsappclone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dbajaj.whatsappclone.screens.CallsScreen
import com.dbajaj.whatsappclone.screens.CommunitiesScreen
import com.dbajaj.whatsappclone.screens.HomeScreen
import com.dbajaj.whatsappclone.screens.SplashScreen
import com.dbajaj.whatsappclone.screens.UpdatesScreen
import com.dbajaj.whatsappclone.screens.UserRegistrationScreen
import com.dbajaj.whatsappclone.screens.WelcomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash){
        composable<Routes.Splash>{
            SplashScreen(navController)
        }
        composable<Routes.Welcome>{
            WelcomeScreen(navController)
        }
        composable<Routes.UserRegistration> {
            UserRegistrationScreen(navController)
        }
        composable<Routes.Home> {
            HomeScreen()
        }
        composable<Routes.Updates> {
            UpdatesScreen()
        }
        composable<Routes.Communities> {
            CommunitiesScreen()
        }
        composable<Routes.Calls> {
            CallsScreen()
        }
    }
}