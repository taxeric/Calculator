package com.lanier.calculator.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lanier.calculator.util.dataStore
import com.lanier.calculator.util.updateSentenceShowValue
import com.lanier.calculator.vm.SettingsViewModel
import kotlinx.coroutines.flow.map

/**
 * Create by Eric
 * on 2022/8/6
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(navHostController: NavHostController, title: String) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) { innerPadding ->
        HistoryPageImpl(paddingValues = innerPadding)
    }
}

@Composable
fun SettingsPageImpl(paddingValues: PaddingValues){
}
