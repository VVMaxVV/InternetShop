package com.example.internetshop.data.mock


class DataMapper(val day: Int, var month: Int, val year: Int) {
    fun getData(): String {
        if(month>12||month<1) month = (0..12).random()
        return when (month) {
            1-> "$day January $year"
            2-> "$day February $year"
            3-> "$day March $year"
            4-> "$day April $year"
            5-> "$day May $year"
            6-> "$day June $year"
            7-> "$day July $year"
            8-> "$day August $year"
            9-> "$day September $year"
            10-> "$day October $year"
            11-> "$day November $year"
            12-> "$day December $year"
            else-> "$day $year"
        }
    }
}