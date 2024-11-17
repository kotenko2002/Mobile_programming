package com.example.controlwork.infrastructure.utils

class TextHelper {
    companion object {
        fun getCountryFlag(countryCode: String): String {
            val upperCaseCode = countryCode.uppercase()
            if (upperCaseCode.length != 2) {
                throw IllegalArgumentException("Country code must be a two-letter ISO code.")
            }

            if (upperCaseCode == "RU") {
                return "\uD83C\uDFAA"
            }

            return upperCaseCode.map {
                    char -> 0x1F1E6 + (char - 'A')
            }.joinToString("") {
                String(Character.toChars(it))
            }
        }
    }
}