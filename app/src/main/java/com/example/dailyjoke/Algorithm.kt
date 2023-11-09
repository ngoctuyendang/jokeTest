package com.example.dailyjoke

fun main() {
    findMinMax(intArrayOf(1, 5, 7, 8, 9))
}

fun findMinMax(data: IntArray) {
    val size = data.size
    data.sort()
    val sum = data.sum()
    when (size) {
        0 -> {
            println("Sum min: 0, Sum max: 0")
        }
        1 -> {
            println("Sum: $sum")
        }
        else -> {
            val sumMin = sum - data[size - 1]
            val sumMax = sum - data[0]
            println(
                "Sum: $sum, Sum min: $sumMin, Sum max: $sumMax, " +
                        "Min_value: ${data[0]}, Max_value: ${data[size - 1]}"
            )
        }
    }
}