package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String =
    if (this.trim().length > count) "${this.substring(0, count).trim()}..." else this

fun String.stripHtml(): String = this
    .replace(Regex("<[^>]*>"), "")
    .replace(Regex("&\\S*;"), "")
    .replace(Regex("\\s{2,}"), " ")