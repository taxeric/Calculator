package com.lanier.calculator.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lanier.calculator.entity.Screen

/**
 * Create by Eric
 * on 2022/8/6
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHome(){
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavBar(navController = navController, paddingValues = innerPadding)
    }
}

@Composable
fun NavBar(navController: NavHostController, paddingValues: PaddingValues){
    NavHost(navController = navController,
        startDestination = Screen.MainCalculate.route,
        modifier = Modifier.padding(paddingValues)){
        composable(Screen.MainCalculate.route) {
            MainPage(navController, Screen.MainCalculate.title)
        }
        composable(Screen.History.route) {
            HistoryPage(navController, Screen.History.title)
        }
    }
}
