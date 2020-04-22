package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return (if (firstName == null || firstName.isEmpty()) null else firstName) to (if (lastName == null || lastName.isEmpty()) null else lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        for (char in payload) {
            var trans = when (char.toLowerCase()) {
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"
                else -> char.toString()
            }
            if (char.isUpperCase()) {
                trans = trans.capitalize()
            }
            if (char.isWhitespace()) {
                trans = divider
            }
            result += trans
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fn =
            if (firstName == null || firstName.isBlank()) null else firstName.trim()[0].toUpperCase()
        val ln =
            if (lastName == null || lastName.isBlank()) null else lastName.trim()[0].toUpperCase()
        val initials = "${fn ?: ""}${ln ?: ""}"
        return if (initials.isEmpty()) null else initials
    }
}