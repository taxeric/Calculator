package com.lanier.calculator.vm

import androidx.lifecycle.ViewModel
import com.lanier.calculator.util.LocalCache
import com.lanier.calculator.util.log
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2022/8/5 10:17
 * Desc  :
 */
class CalculateViewModel: ViewModel(){

    private val sb = StringBuilder()
    private val symbolsIndex = mutableListOf<SymbolEntity>()
    private val valueList = mutableListOf<String>()

    private val _showResultStr = MutableStateFlow("")
    val showResultStr get() = _showResultStr

    fun c() {
        valueList.clear()
        symbolsIndex.clear()
        sb.clear()
        print()
    }

    fun X(){
        if (sb.isNotEmpty()) {
            sb.delete(sb.length - 1, sb.length)
            print()
        }
    }

    fun add(str: String) {
        when (str) {
            "." -> checkPoint(str)
            "(" -> checkLeftBracket()
            ")" -> checkRightBracket()
            "+", "-", "×", "/" -> checkBaseSymbol(str)
            else -> {
                sb.append(str)
                print()
            }
        }
    }

    fun calculate() {
        if (sb.isEmpty()) {
            return
        }
        if (sb.last().isBaseSymbol()) {
            sb.deleteCharAt(sb.length - 1)
        }
        sb.toString().log()
        var isMinus = false
        if (sb.first() == '-') {
            isMinus = true
            sb.deleteCharAt(0)
        }
        var baseIndex = -1
        sb.forEachIndexed { index, c ->
            if (c.isBaseSymbol() && index != 0) {
                baseIndex ++
                symbolsIndex.add(SymbolEntity(index, c.toString(), c, baseIndex))
            }
        }
        baseIndex = -1
        //取得运算符下标
        if (sb[0] != '-') {
            valueList.add(sb.substring(0, symbolsIndex[0].index))
        }
        symbolsIndex.forEachIndexed { index, m ->
            if (index != symbolsIndex.size - 1) {
                valueList.add(sb.substring(m.index + 1, symbolsIndex[index + 1].index))
            }
        }
        valueList.add(sb.substring(symbolsIndex[symbolsIndex.size - 1].index + 1, sb.length))
        handleMD(isMinus, symbolsIndex, valueList)
    }

    private fun handleMD(firstIsMinus: Boolean = false, list: MutableList<SymbolEntity>, value: MutableList<String>) {
        val _list = mutableListOf<SymbolEntity>().apply { addAll(list) }
//        val _value = mutableListOf<String>().apply { addAll(value) }
        var isContains = false
        for (index in 0 until _list.size) {
            val symbolEntity = _list[index]
            if (symbolEntity.symbolChar == '×' || symbolEntity.symbolChar == '/') {
                isContains = true
                val r = if (symbolEntity.symbolChar == '×') {
                    value[index].toBigDecimal() * value[index + 1].toBigDecimal()
                } else {
                    value[index].toBigDecimal() / value[index + 1].toBigDecimal()
                }
                value.removeAt(index + 1)
                value.removeAt(index)
                value.add(index, r.toString())
                list.removeAt(index)
                break
            }
        }
        if (isContains) {
            handleMD(firstIsMinus, list, value)
        } else {
            handleJJ(firstIsMinus, list, value)
        }
    }

    private fun handleJJ(firstIsMinus: Boolean = false, list: MutableList<SymbolEntity>, value: MutableList<String>) {
        var result = if (firstIsMinus) {
            - value[0].toBigDecimal()
        } else {
            value[0].toBigDecimal()
        }
        list.forEachIndexed { index, data ->
            result = if (data.symbolChar == '+') {
                result + value[index + 1].toBigDecimal()
            } else {
                result - value[index + 1].toBigDecimal()
            }
        }
        "result -> $result".log()
        sb.append("\n").append("=").append(result)
        val singleData = sb.toString()
        LocalCache.calculateResult.add(singleData)
        print()
        sb.clear()
    }

    private fun Char.isBaseSymbol(): Boolean = this == '+' || this == '-' || this == '×' || this == '/'

    private fun checkPoint(str: String){
        if (sb.isEmpty()){
            sb.append("0")
        }
        sb.append(".")
        print()
    }

    private fun checkBaseSymbol(symbol: String) {
        if (sb.isEmpty()){
            if (symbol != "-"){
                return
            }
            sb.append(symbol)
            print()
        } else {
            val sbLast = sb.last()
            if (sbLast == '+' || sbLast == '-' || sbLast == '×' || sbLast == '/' || sbLast == '.') {
                return
            }
            sb.append(symbol)
            print()
        }
    }

    private fun checkLeftBracket() {
        if (sb.isEmpty()){
            sb.append("(")
            print()
            return
        }
        val sbLast = sb.last()
        if (sbLast == '+' || sbLast == '-' || sbLast == '*' || sbLast == '/'){
            sb.append("(")
            print()
        }
    }

    private fun checkRightBracket() {
        if (sb.isEmpty()){
            return
        }
        var leftBracketTime = 0
        var rightBracketTime = 0
        sb.forEach {
            if (it == '(')
                leftBracketTime ++
            if (it == ')')
                rightBracketTime ++
        }
        if (leftBracketTime % 2 == 1 && rightBracketTime % 2 == 0) {
            sb.append(")")
            print()
        } else {
            "failed -> not match".log()
        }
    }

    private fun print() {
        val result = sb.toString()
        _showResultStr.tryEmit(result)
    }

    data class SymbolEntity(
        val index: Int,
        val symbolStr: String,
        val symbolChar: Char,
        val realIndex: Int = -1
    )
}
