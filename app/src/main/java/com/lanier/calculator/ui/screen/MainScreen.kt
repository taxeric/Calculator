package com.lanier.calculator.ui.screen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lanier.calculator.R
import com.lanier.calculator.entity.Screen
import com.lanier.calculator.ui.theme.BaseMainAppTheme
import com.lanier.calculator.ui.theme.MyTvColor
import com.lanier.calculator.util.log
import com.lanier.calculator.util.sentences
import com.lanier.calculator.vm.CalculateViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Create by Eric
 * on 2022/8/5
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navHostController: NavHostController, title: String) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = title) },
                actions = {
                    Row {
                        IconButton(onClick = {
                            navHostController.navigate(Screen.History.route)
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_history_24), contentDescription = "history")
                        }
/*                        IconButton(onClick = {
                            navHostController.navigate(Screen.Settings.route)
                        }) {
                            Icon(imageVector = Icons.Filled.Settings, contentDescription = "history")
                        }*/
                    }
                },
            )
        },
    ) { innerPadding ->
        MainPageImpl(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MainPageImpl(modifier: Modifier = Modifier) {
    val vm = viewModel<CalculateViewModel>()
    val result = vm.showResultStr.collectAsState().value
    val localConfig = LocalConfiguration.current
    when (localConfig.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            MainPageLandscapeImpl(modifier, result)
        }
        Configuration.ORIENTATION_PORTRAIT,Configuration.ORIENTATION_UNDEFINED -> {
            MainPagePortraitImpl(modifier, result)
        }
    }
}

@Composable
fun MainPagePortraitImpl(modifier: Modifier = Modifier, result: String){
    val vm = viewModel<CalculateViewModel>()
    if (!vm.shouldShowAnim) {
        LaunchedEffect(key1 = Unit) {
            delay(1000)
            vm.shouldShowAnim = true
        }
    }
    Column(modifier = modifier.fillMaxWidth()) {
        AnimatedVisibility(
            visible = vm.shouldShowAnim,
            enter = fadeIn(animationSpec = tween(delayMillis = 500, durationMillis = 1000))
        ) {
            Column {
                Text(text = vm.sentence.cite, modifier = Modifier
                    .padding(10.dp, 5.dp))
                Text(text = vm.sentence.sentence,
                    fontSize = 13.sp,
                    modifier = Modifier
                    .padding(10.dp, 5.dp, 10.dp, 10.dp))
            }
        }
        ResultShowView(
            Modifier.fillMaxWidth(), false, result)
        CalculationView(
            Modifier.fillMaxWidth())
    }
}

@Composable
fun MainPageLandscapeImpl(modifier: Modifier = Modifier, result: String){
    Row(modifier = modifier.fillMaxWidth()) {
        ResultShowView(
            Modifier
                .fillMaxHeight()
                .weight(1f), true, result)
        CalculationView(
            Modifier
                .fillMaxHeight()
                .weight(1f))
    }
}

@Composable
fun ResultShowView(modifier: Modifier = Modifier, landscape: Boolean = false, result: String) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp, 0.dp, 10.dp, 10.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.0.dp)) {
            val tvModifier = if (landscape) {
                Modifier.fillMaxHeight()
            } else {
                Modifier.height(200.dp)
            }
            Text(
                text = result,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = MyTvColor(isSystemInDarkTheme()).calculateColor,
                modifier = tvModifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    /*.border(1.dp, color = Color(0xFF83AAF7), shape = RoundedCornerShape(20.dp))*/
                    .padding(10.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )
        }
    }
}

@Composable
fun CalculationView(modifier: Modifier = Modifier) {
    val vm = viewModel<CalculateViewModel>()
    Column(modifier = modifier.padding(10.dp, 0.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
        ) {
            Button(onClick = { vm.c() }, modifier = Modifier
                .weight(2f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "CE", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.X() }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "←", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("/") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "/", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)) {
            Button(onClick = { vm.add("7") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "7", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("8") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "8", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("9") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "9", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("×") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "×", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)) {
            Button(onClick = { vm.add("4") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "4", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("5") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "5", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("6") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "6", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("-") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "-", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)) {
            Button(onClick = { vm.add("1") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "1", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("2") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "2", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("3") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "3", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add("+") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "+", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)) {
            Button(onClick = { vm.add("0") }, modifier = Modifier
                .weight(2f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "0", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.add(".") }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = ".", fontSize = 16.sp, textAlign = TextAlign.Center,)
            }
            Button(onClick = { vm.calculate() }, modifier = Modifier
                .weight(1f)
                .padding(5.dp, 0.dp)
                .fillMaxHeight()) {
                Text(text = "＝", color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,)
            }
        }
    }
}
