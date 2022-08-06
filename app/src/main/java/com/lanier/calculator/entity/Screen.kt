package com.lanier.calculator.entity

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2022/8/5 17:56
 * Desc  :
 */

const val ROUTE_SCREEN_MAIN_CALCULATE = "screenCalculate"
const val ROUTE_SCREEN_MAIN_SETTINGS = "screenOther"
const val ROUTE_SCREEN_MAIN_HISTORY = "screenHistory"

const val ROUTE_SCREEN_WEB_VIEW = "webView"
const val ROUTE_PARAMS_WEB_VIEW_TITLE = "webViewTitle"
const val ROUTE_PARAMS_WEB_VIEW_URL = "webViewUrl"

sealed class Screen (val route: String, val title: String) {
    object MainCalculate : Screen(ROUTE_SCREEN_MAIN_CALCULATE, "计算器")
    object History: Screen(ROUTE_SCREEN_MAIN_HISTORY, "历史")
    object Settings : Screen(ROUTE_SCREEN_MAIN_SETTINGS, "设置")
    object WebViewPage: Screen(ROUTE_SCREEN_WEB_VIEW, "")
}
