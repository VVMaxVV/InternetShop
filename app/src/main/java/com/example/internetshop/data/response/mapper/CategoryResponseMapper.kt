package com.example.internetshop.data.response.mapper

import com.example.internetshop.data.exception.NoSuchCategoryException
import com.example.internetshop.data.response.CategoryResponse
import com.example.internetshop.domain.data.model.category.Category
import java.util.*
import javax.inject.Inject

class CategoryResponseMapper @Inject constructor()  {
    fun toCategory(categoryResponseList: CategoryResponse): List<Category>{
        return categoryResponseList.categoryName.map { it ->
            val nameOfCategory =
                it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            when (it) {
                "electronics" -> Category(
                    nameOfCategory,
                    "https://upload.wikimedia.org/wikipedia/commons/d/d9/Arduino_ftdi_chip-1.jpg"
                )
                "jewelery" -> Category(
                    nameOfCategory,
                    "https://cache.net-a-porter.com/content/images/story-head-content-1stFebruary2021-1611749733226.jpeg/w1900_q65.jpeg"
                )
                "men's clothing" -> Category(
                    nameOfCategory,
                    "https://www.farmers.co.nz/INTERSHOP/static/WFS/Farmers-Shop-Site/-/Farmers-Shop/en_NZ/2021/September/FTC4030-New-Season-Cat-Tiles-7-Oct/Mens-Cat-Tiles/02-Mens-Clothing/03-Mens-Clothing-Coats-Jackets.jpg"
                )
                "women's clothing" -> Category(
                    nameOfCategory,
                    "https://media.4rgos.it/i/Argos/4221-m007-25-01-women-sweatcoat?maxW=768&qlt=75&fmt.jpeg.interlaced=true"
                )
                else -> throw NoSuchCategoryException("Invalid category $it")
            }
        }
    }
}