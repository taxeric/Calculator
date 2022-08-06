package com.lanier.calculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lanier.calculator.entity.CalculateResult
import com.lanier.calculator.ui.screen.common.ModifyInfoDialog
import com.lanier.calculator.ui.theme.MyTvColor
import com.lanier.calculator.util.LocalCache
import com.lanier.calculator.util.log
import com.lanier.calculator.vm.HistoryViewModel

/**
 * Create by Eric
 * on 2022/8/6
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryPage(navHostController: NavHostController, title: String){
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
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
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ){ innerPadding ->
        HistoryPageImpl(paddingValues = innerPadding)
    }
}

@Composable
fun HistoryPageImpl(paddingValues: PaddingValues) {
    val vm = viewModel<HistoryViewModel>()
    val cache = vm.dbResult.collectAsState().value
    val importantList = cache.filter {
        it.important
    }
    val notImportantList = cache.filter {
        !it.important
    }
    val list = (importantList + notImportantList).toMutableStateList()
    LaunchedEffect(key1 = Unit) {
        vm.getCalculateResults()
    }
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(list) { index, item ->
            HistoryItem(index, item) { mIndex, important, newDesc ->
                val mData = list[mIndex].copy(important = important, desc = newDesc)
                list[mIndex] = mData
                vm.update(mData)
//                LocalCache.calculateResult[mIndex] =
//                    LocalCache.calculateResult[mIndex].copy(important = important, desc = newDesc)
            }
            if (index < list.size) {
                Divider()
            }
        }
    }
}

@Composable
fun HistoryItem(index: Int, data: CalculateResult, modify: (Int, Boolean, String) -> Unit = {_, _, _ ->}) {
    val mData = data.result.split("=")
    var importantValue by remember {
        mutableStateOf(data.important)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    "data -> ${data.desc}".log()
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .clickable { showDialog = true }
        .background(Color.Transparent)
        .padding(10.dp, 5.dp)
    ) {
        val (cbLayout, tag, desc, result) = createRefs()
        Row(modifier = Modifier.constrainAs(cbLayout) {
            start.linkTo(parent.start)
        }, verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = importantValue, onCheckedChange = {
                importantValue = it
                modify(index, importantValue, data.desc)
            })
            Text(text = "重要")
        }
        Text(text = buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Gray)){
                append("#")
            }
            withStyle(SpanStyle(color = MyTvColor(isSystemInDarkTheme()).baseColor)){
                append( "${index + 1}")
            }
        }, modifier = Modifier.constrainAs(tag) {
            end.linkTo(parent.end)
            baseline.linkTo(cbLayout.baseline)
        })
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                    append(mData[0])
                }
                withStyle(SpanStyle(color = MyTvColor(isSystemInDarkTheme()).baseColor, fontSize = 18.sp)) {
                    append("=")
                    append(mData[1])
                }
            }, textAlign = TextAlign.End, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(result) {
                    top.linkTo(cbLayout.bottom)
                }
        )
        if (data.desc.isNotEmpty()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(desc) {
                    top.linkTo(result.bottom)
                }) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = data.desc,
                    color = Color(0xFF83AAF7),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    if (showDialog) {
        ModifyInfoDialog(desc = data.desc) { update, desc ->
            "m -> $update $desc".log()
            if (update) {
                modify(index, importantValue, desc)
            }
            showDialog = false
        }
    }
}
