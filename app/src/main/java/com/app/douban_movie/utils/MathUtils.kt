package com.app.douban_movie_ktx.utils

import java.math.BigDecimal


object MathUtils {

    @JvmStatic
    fun NumberRounding(number: Double): Int {
        var num = BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP).toDouble()
        return num.toInt()
    }
}