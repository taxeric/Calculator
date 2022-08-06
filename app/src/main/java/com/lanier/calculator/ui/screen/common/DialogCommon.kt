package com.lanier.calculator.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.TextDelegate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lanier.calculator.entity.ROUTE_SCREEN_WEB_VIEW
import com.lanier.calculator.util.log
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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

@Composable
fun ActivityDialog(navController: NavHostController, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        val mUrls = listOf("https://github.com/taxeric/Calculator", "https://mp.weixin.qq.com/s/O7rz2BP8yJ5Yy4ndOQXDkg")
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "功能", fontSize = 18.sp, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
            TODOView(content = "四则运算")
            TODOView(content = "历史记录")
            TODOView(content = "MD3 风格")
            TODOView(content = "横竖屏切换")
            TODOView(content = "暗色模式")
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp)) {
                Text(text = "项目地址: ")
                val buildStr1 = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                        append("点击前往")
                    }
                }
                ClickableText(text = buildStr1, onClick = {
                    val encodeUrl = URLEncoder.encode(mUrls[0], StandardCharsets.UTF_8.toString())
                    navController.navigate("${ROUTE_SCREEN_WEB_VIEW}/正文/$encodeUrl")
                    onDismiss()
                })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp)) {
                Text(text = "活动地址: ")
                val buildStr1 = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                        append("点击前往")
                    }
                }
                ClickableText(text = buildStr1, onClick = {
                    val encodeUrl = URLEncoder.encode(mUrls[1], StandardCharsets.UTF_8.toString())
                    navController.navigate("${ROUTE_SCREEN_WEB_VIEW}/正文/$encodeUrl")
                    onDismiss()
                })
            }
            TextButton(onClick = onDismiss, modifier = Modifier
                .align(Alignment.End)
                .padding(5.dp)) {
                Text(text = "Close")
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
