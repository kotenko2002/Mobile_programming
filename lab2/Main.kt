fun main() {
    val calculator = Calculator()

    try {
        val (num1, num2, operator) = calculator.input()
        val result = calculator.calculate(num1, num2, operator)
        println("Результат: $result")
    } catch (e: IllegalArgumentException) {
        println("Помилка: ${e.message}")
    }
}

class Calculator {
    fun input(): Triple<Double, Double, Char> {
        println("Введіть перше число:")
        val num1Str = readlnOrNull()
        if (num1Str.isNullOrBlank()) {
            throw IllegalArgumentException("Перше число не може бути порожнім")
        }

        val num1: Double = try {
            num1Str.toDouble()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Перше число повинно бути числом")
        }

        println("Введіть друге число:")
        val num2Str = readlnOrNull()
        if (num2Str.isNullOrBlank()) {
            throw IllegalArgumentException("Друге число не може бути порожнім")
        }

        val num2: Double = try {
            num2Str.toDouble()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Друге число повинно бути числом")
        }

        println("Введіть операцію (+, -, *, /):")
        val operatorStr = readlnOrNull()
        if (operatorStr.isNullOrBlank()) {
            throw IllegalArgumentException("Операція не може бути порожньою")
        }

        val operator: Char = operatorStr[0]
        if (operator !in listOf('+', '-', '*', '/')) {
            throw IllegalArgumentException("Невідома операція: $operator")
        }

        return Triple(num1, num2, operator)
    }

    fun calculate(a: Double, b: Double, operator: Char): Double {
        return when (operator) {
            '+' -> add(a, b)
            '-' -> subtract(a, b)
            '*' -> multiply(a, b)
            '/' -> divide(a, b)
            else -> throw IllegalArgumentException("Невідома операція: $operator")
        }
    }

    private fun add(a: Double, b: Double): Double = a + b

    private fun subtract(a: Double, b: Double): Double = a - b

    private fun multiply(a: Double, b: Double): Double = a * b

    private fun divide(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw IllegalArgumentException("Ділення на нуль неможливе")
        }

        return a / b
    }
}
