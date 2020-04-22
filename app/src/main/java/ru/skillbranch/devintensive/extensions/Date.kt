package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun TimeUnits.toMilliseconds(): Long = when(this) {
    TimeUnits.SECOND -> SECOND
    TimeUnits.MINUTE -> MINUTE
    TimeUnits.HOUR -> HOUR
    TimeUnits.DAY -> DAY
}

fun TimeUnits.plural(value: Int): String = millisecondsInUnits(this.toMilliseconds() * value.toLong(), this)

fun millisecondsInUnits(value: Long, units: TimeUnits = TimeUnits.SECOND): String {
    val count = when(units) {
        TimeUnits.SECOND -> value / SECOND
        TimeUnits.MINUTE -> value / MINUTE
        TimeUnits.HOUR -> value / HOUR
        TimeUnits.DAY -> value / DAY
    }
    val remainder = count.rem(10).toInt()
    val unit = when(units) {
        TimeUnits.SECOND -> when (remainder) {
            1 -> "секунду"
            in 2..4 -> "секунды"
            else -> "секунд"
        }
        TimeUnits.MINUTE -> when (remainder) {
            1 -> "минуту"
            in 2..4 -> "минуты"
            else -> "минут"
        }
        TimeUnits.HOUR -> when (remainder) {
            1 -> "час"
            in 2..4 -> "часа"
            else -> "часов"
        }
        TimeUnits.DAY -> when (remainder) {
            1 -> "день"
            in 2..4 -> "дня"
            else -> "дней"
        }
    }
    return "$count $unit"
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val timeDiff = date.time - this.time
    val milliDiff = abs(timeDiff)
    val textDiff: String
    if (timeDiff > 0) {
        textDiff = when (milliDiff) {
            in 0 * SECOND..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE -> "${millisecondsInUnits(milliDiff, TimeUnits.MINUTE)} назад"
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> "${millisecondsInUnits(milliDiff, TimeUnits.HOUR)} назад"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> "${millisecondsInUnits(milliDiff, TimeUnits.DAY)} назад"
            else -> "более года назад"
        }
    } else {
        textDiff = when (milliDiff) {
            in 0 * SECOND..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> "через ${millisecondsInUnits(milliDiff, TimeUnits.MINUTE)}"
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> "через ${millisecondsInUnits(milliDiff, TimeUnits.HOUR)}"
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> "через ${millisecondsInUnits(milliDiff, TimeUnits.DAY)}"
            else -> "более чем через год"
        }
    }
    return textDiff
}