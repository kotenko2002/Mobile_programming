fun main() {
    val converter = CurrencyConverter()

    try {
        val (fromCurrency, amount, toCurrency) = converter.input()
        val result = String.format("%.2f", converter.convert(fromCurrency, amount, toCurrency))
        println("$amount $fromCurrency = $result $toCurrency")
    } catch (e: IllegalArgumentException) {
        println("Помилка: ${e.message}")
    }
}

class CurrencyConverter {
    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "UAH" to 0.035
    )

    fun input(): Triple<String, Double, String> {
        println("Введіть вхідну валюту (USD, EUR, UAH):")
        val fromCurrency = readlnOrNull()
        if (fromCurrency === null) {
            throw IllegalArgumentException("Вхідна валюта не може бути порожньою")
        }
        if (!exchangeRates.containsKey(fromCurrency)) {
            throw IllegalArgumentException("Введена вхідна валюта не підтримується")
        }

        println("Введіть кількість валюти:")
        val amountStr = readlnOrNull()
        if (amountStr.isNullOrBlank()) {
            throw IllegalArgumentException("Кількість валюти не може бути порожньою")
        }

        val amount: Double = try {
            amountStr.toDouble()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Кількість валюти повинна бути числом")
        }

        println("Введіть вихідну валюту (USD, EUR, UAH):")
        val toCurrency = readlnOrNull()
        if (toCurrency === null) {
            throw IllegalArgumentException("Вихідна валюта не може бути порожньою")
        }
        if (!exchangeRates.containsKey(toCurrency)) {
            throw IllegalArgumentException("Введена вихідна  валюта не підтримується")
        }

        return Triple(fromCurrency.uppercase(), amount.toDouble(), toCurrency.uppercase())
    }

    fun convert(fromCurrency: String, amount: Double, toCurrency: String): Double {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            val rate = exchangeRates[fromCurrency]!! / exchangeRates[toCurrency]!!
            return amount * rate
        } else {
            throw IllegalArgumentException("Invalid currency")
        }
    }
}