package com.example.constatazione_amichevole

sealed class Screen(val route : String) {
    object SchermoLogin : Screen("schermo_login")
    object CreaAccount : Screen("crea_account")
    object HomePage : Screen("home_page")
    object GestioneMacchine : Screen("gestione_macchine")
}