package com.lanier.calculator.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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
import com.lanier.calculator.vm.CalculateViewModel

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
                    IconButton(onClick = {
                        navHostController.navigate(Screen.History.route)
                    }) {
//                        Icon(imageVector = Icons.Filled.Send, contentDescription = "history")
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_history_24), contentDescription = "history")
                    }
                }
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
    Column(modifier = modifier.fillMaxWidth()) {
        ResultShowView(
            Modifier.fillMaxWidth(), result)
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
                .weight(1f), result)
        CalculationView(
            Modifier
                .fillMaxHeight()
                .weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultShowView(modifier: Modifier = Modifier, result: String) {
    Column(modifier = modifier.fillMaxWidth()) {
//        Text(text = "信步流年，付印岁月")
/*        Image(painter = painterResource(id = R.drawable.ic_roco), contentDescription = "back", modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
            .clip(RoundedCornerShape(10)))*/
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.0.dp)) {
            Text(
                text = result,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = Color(0xFF83AAF7),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White)
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
