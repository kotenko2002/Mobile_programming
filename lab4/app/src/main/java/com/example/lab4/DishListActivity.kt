package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DishListActivity : AppCompatActivity() {
    private val dishes = listOf(
        Dish("Борщ", "Традиційний український суп з буряком", 12.99, listOf("Буряк", "Капуста", "Картопля", "Морква", "М'ясо")),
        Dish("Вареники з картоплею", "Пісні вареники з начинкою з картоплі", 10.99, listOf("Тісто", "Картопля", "Цибуля", "Олія")),
        Dish("Сало з часником", "Сало, приправлене часником і спеціями", 8.99, listOf("Сало", "Часник", "Сіль", "Перець")),
        Dish("Голубці", "Листя капусти, нафаршироване м'ясом і рисом", 11.99, listOf("Капуста", "М'ясо", "Рис", "Цибуля", "Томатний соус")),
        Dish("Пампушки з часником", "М'які булочки з часником і кропом", 9.99, listOf("Тісто", "Часник", "Кріп", "Олія")),
        Dish("Котлета по-київськи", "Куряча котлета з начинкою з масла і зелені", 14.99, listOf("Курка", "Масло", "Зелень", "Часник", "Хлібні крихти")),
        Dish("Олів'є", "Салат з вареного м'яса, картоплі та овочів", 13.49, listOf("М'ясо", "Картопля", "Морква", "Огірки", "Майонез")),
        Dish("Деруни", "Картопляні оладки з цибулею", 11.49, listOf("Картопля", "Цибуля", "Яйце", "Борошно", "Олія")),
        Dish("Запечена риба", "Риба, запечена з овочами та спеціями", 15.99, listOf("Риба", "Овочі", "Спеції", "Олія")),
        Dish("Каша гречана", "Гречка, приготована на воді або молоці", 7.99, listOf("Гречка", "Вода або молоко", "Сіль"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_list)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val emptyMessage: TextView = findViewById(R.id.empty_message)

        if (dishes.isEmpty()) {
            emptyMessage.apply {
                text = "Список порожній"
                visibility = View.VISIBLE
            }
            recyclerView.visibility = View.GONE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyMessage.visibility = View.GONE

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = DishAdapter(dishes) { dish ->
                val intent = Intent(this, DishDetailActivity::class.java).apply {
                    putExtra("DISH_NAME", dish.name)
                    putExtra("DISH_DESCRIPTION", dish.description)
                    putExtra("DISH_PRICE", dish.price)
                    putExtra("DISH_INGREDIENTS", dish.ingredients.toTypedArray())
                }
                startActivity(intent)
            }
        }
    }
}
