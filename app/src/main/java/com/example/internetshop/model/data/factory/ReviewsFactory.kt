package com.example.internetshop.model.data.factory

import com.example.internetshop.data.mock.DataMapper
import com.example.internetshop.domain.data.model.Review

class ReviewsFactory {
    fun create(id: String): List<Review> {
        val reviewList = mutableListOf<Review>()
        when(id) {
            "1"-> {
                reviewList.add(
                    Review("1",
                    "Maksim Voloshko",
                    5,
                "Классный рюкзак, быстрая доставка",
                DataMapper(18,8,2021),
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftech.liga.net%2Ftechnology%2Fnovosti%2Fiskusstvennyy-intellekt-nvidia-generiruet-litsa-nenastoyaschih-lyudey&psig=AOvVaw0sC64qItEI4jHi0x2U-rkF&ust=1640438184717000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIifoszC_PQCFQAAAAAdAAAAABAD")
                )

                reviewList.add(
                    Review("1",
                "Elena Golovach",
                4,
                "Не плохо,но и не хорошо",
                DataMapper(13,11,21),
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Flikeyou.io%2Fetot-sajt-pokazyvaet-lyudej-kotoryh-ne-sushhestvuet-ih-generiruet-nejroset%2F&psig=AOvVaw0sC64qItEI4jHi0x2U-rkF&ust=1640438184717000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIifoszC_PQCFQAAAAAdAAAAABAJ")
                )
                return reviewList
            }
            else -> {
                reviewList.add(
                    Review("5",
                        "Oleg Chernov",
                    1,
                    "Товар не оправдал ожидания и к тому же долго шел! Ставлю 1!!!",
                        DataMapper(24,11,2021),
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.maximonline.ru%2Fguide%2Fprogress%2F_article%2Fsayt-dnya-generator-fotografiy-nesuschestvuyuschi%2F&psig=AOvVaw0sC64qItEI4jHi0x2U-rkF&ust=1640438184717000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIifoszC_PQCFQAAAAAdAAAAABAO")
                )
                return reviewList
            }
        }
    }
}