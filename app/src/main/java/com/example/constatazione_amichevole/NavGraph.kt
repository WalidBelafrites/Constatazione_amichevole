package com.example.constatazione_amichevole

import HomePage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavController


@Composable
fun SetupNavGraph(navController : NavHostController) {
    NavHost(navController, startDestination = Screen.SchermoLogin.route) {
        composable(route = Screen.SchermoLogin.route) { SchermoLogin(navController = navController) }
        composable(route = Screen.CreaAccount.route) { CreaAccount(navController = navController) }
        composable(route = Screen.HomePage.route) { HomePage(navController = navController) }
        composable(route = Screen.GestioneMacchine.route) { GestioneMacchine(navController = navController) }
        composable(route = Screen.CreaConstatazione.route) { CreaConstatazione(navController = navController) }
        composable(route = Screen.Profilo.route) { Profilo(navController = navController) }


    }
}