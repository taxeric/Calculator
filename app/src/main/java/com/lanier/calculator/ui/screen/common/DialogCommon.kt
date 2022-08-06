package com.lanier.calculator.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Create by Eric
 * on 2022/8/6
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyInfoDialog(desc: String, onDismiss: (Boolean, String) -> Unit){
    Dialog(
        onDismissRequest = { onDismiss(false, "") },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        var mDesc by remember {
            mutableStateOf(desc)
        }
        var isModify by remember{
            mutableStateOf(false)
        }
        Column(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.background)
        ) {
            Text(text = "更新", modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = mDesc, onValueChange = {
                isModify = true
                mDesc = it
            },
            label = {
                Text(text = "更改描述")
            }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = { onDismiss(isModify, mDesc) }, modifier = Modifier
                .align(Alignment.End)
                .padding(5.dp)) {
                Text(text = "确定")
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
