package com.lanier.calculator.util

import com.lanier.calculator.entity.CalculateResult

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2022/8/5 13:40
 * Desc  : 内存缓存数据
 */
@Deprecated("didn't use")
object LocalCache {

    val calculateResult = mutableListOf<CalculateResult>()
}
