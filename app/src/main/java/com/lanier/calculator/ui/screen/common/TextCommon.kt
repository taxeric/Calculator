package com.lanier.calculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanier.calculator.ui.theme.MyTvColor

/**
 * Create by Eric
 * on 2022/8/6
 */

@Composable
fun SettingsText(modifier: Modifier = Modifier, title: String, desc: String) {
    Column(modifier = modifier) {
        Text(text = title, color = MyTvColor(isSystemInDarkTheme()).baseColor, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = desc, color = Color.Gray)
    }
}

@Composable
fun SettingsTextWithCheckBox(
    check: Boolean,
    title: String,
    desc: String,
    onCheckedChange: (Boolean) -> Unit){
    var mCheck by remember {
        mutableStateOf(check)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)) {
        SettingsText(title = title, desc = desc, modifier = Modifier.weight(4f))
        Checkbox(checked = mCheck, onCheckedChange = {
            mCheck = it
            onCheckedChange(mCheck)
        }, modifier = Modifier.weight(1f))
    }
}