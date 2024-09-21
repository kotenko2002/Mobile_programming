package com.example.lab7.data

import java.util.UUID

object ClientTempData {
    val clients = arrayListOf(
        Client(
            id = UUID.randomUUID().toString(),
            firstName = "Олександр",
            lastName = "Іванов",
            email = "oleksandr.ivanov@example.com",
            age = 30,
            gender = "Чоловіча",
            schedule = "Пн, Ср, Пт - 18:00",
            contactInfo = "+380123456789"
        ),
        Client(
            id = UUID.randomUUID().toString(),
            firstName = "Марія",
            lastName = "Петренко",
            email = "mariya.petrenko@example.com",
            age = 25,
            gender = "Жіноча",
            schedule = "Вт, Чт - 19:00, Сб - 10:00",
            contactInfo = "+380987654321"
        ),
        Client(
            id = UUID.randomUUID().toString(),
            firstName = "Дмитро",
            lastName = "Сидоренко",
            email = "dmytro.sydorenko@example.com",
            age = 35,
            gender = "Чоловіча",
            schedule = "Пн, Ср - 20:00",
            contactInfo = "+380123456780"
        ),
        Client(
            id = UUID.randomUUID().toString(),
            firstName = "Олена",
            lastName = "Ковальчук",
            email = "olena.kovalchuk@example.com",
            age = 28,
            gender = "Жіноча",
            schedule = "Вт, Чт - 18:30, Нд - 11:00",
            contactInfo = "+380123456781"
        ),
        Client(
            id = UUID.randomUUID().toString(),
            firstName = "Іван",
            lastName = "Ткаченко",
            email = "ivan.tkachenko@example.com",
            age = 40,
            gender = "Чоловіча",
            schedule = "Ср, Пт - 19:30",
            contactInfo = "+380123456782"
        )
    )
}
