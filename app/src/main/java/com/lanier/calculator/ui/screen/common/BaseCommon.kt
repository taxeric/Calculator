package com.lanier.calculator.ui.screen.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Create by Eric
 * on 2022/8/7
 */
@Preview()
@Composable
fun TODOView(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 13.sp,
    content: String = "",
    completed: Boolean = true
) {
    Row(modifier = modifier.fillMaxWidth().height(30.dp).padding(10.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = completed, enabled = false, onCheckedChange = {}, modifier = Modifier.weight(1f))
        Text(text = content, fontSize = fontSize, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(4f))
    }
}
