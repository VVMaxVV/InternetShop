package com.example.internetshop.data.response.mapper

import com.example.internetshop.domain.data.model.Category
import javax.inject.Inject

class CategoryResponseMapper @Inject constructor(){
    fun toCategory(categoryResponseList: List<String>): List<Category> {
        return categoryResponseList.map {
             when (it) {
                "electronics" -> Category(
                    it,
                    "https://upload.wikimedia.org/wikipedia/commons/d/d9/Arduino_ftdi_chip-1.jpg"
                )
                "jewelery" -> Category(
                    it,
                    "https://www.anitolia.com/wp-content/uploads/2021/02/oval-red-big-agate-stone-men-ring-vintage-handcarved-men-jewelery-quality-fashionable-ring-mens-ring-anitolia-0-6024-16-B.jpg"
                )
                "men's clothing" -> Category(
                    it,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.next.ua%2Fen%2Fmen&psig=AOvVaw36AIi2PsB82R2uqvgr27TX&ust=1642514766725000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJjb0776uPUCFQAAAAAdAAAAABAD"
                )
                "women's clothing" -> Category(
                    it,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Ferinlisa1%2Fwomens-clothing%2F&psig=AOvVaw0ThFRAIWoFkSDexL-NnLrb&ust=1642514804084000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCKie-M_6uPUCFQAAAAAdAAAAABAJ"
                )
                else -> Category("", "")

            }

        }
    }
}