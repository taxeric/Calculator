package com.lanier.calculator.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.lanier.calculator.entity.CalculateResult
import com.lanier.calculator.util.LocalCache

/**
 * Create by Eric
 * on 2022/8/6
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryPage(navHostController: NavHostController, title: String){
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
    ){ innerPadding ->
        HistoryPageImpl(paddingValues = innerPadding)
    }
}

@Composable
fun HistoryPageImpl(paddingValues: PaddingValues) {
    val list = LocalCache.calculateResult.toMutableStateList()
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(list) { index, item ->
            HistoryItem(index, item)
        }
    }
}

@Composable
fun HistoryItem(index: Int, data: CalculateResult) {
    val mData = data.result.split("=")
    var importantValue by remember {
        mutableStateOf(data.important)
    }
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .clickable {  }
        .background(Color.Transparent)
        .padding(10.dp, 5.dp)
    ) {
        val (cbLayout, tag, result) = createRefs()
        Row(modifier = Modifier.constrainAs(cbLayout) {
            start.linkTo(parent.start)
        }, verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = importantValue, onCheckedChange = { importantValue = it })
            Text(text = "重要")
        }
        Text(text = "#${index + 1}", modifier = Modifier.constrainAs(tag){
            end.linkTo(parent.end)
            baseline.linkTo(cbLayout.baseline)
        })
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                    append(mData[0])
                }
                withStyle(SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                    append("=")
                    append(mData[1])
                }
            }, textAlign = TextAlign.End, modifier = Modifier
                .fillMaxWidth().constrainAs(result){
                    top.linkTo(cbLayout.bottom)
                }
        )
    }
}
