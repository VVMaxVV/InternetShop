package com.example.internetshop

fun getProductFromServer(): ProductResponse {
    return ProductResponse(
        1,
        "Short dress",
        "H&M",
        300.00f,
        "Short black dress",
        "Short dress in soft cotton jersey with decorative buttons down the front and a wide, frill-trimmed square neckline with concealed elastication. Elasticated seam under the bust and short puff sleeves with a small frill trim.",
        5f,
        10,
        "Dress"
    )
}

fun getAuthTokenFromServer(login: String, password: String): Token {
    val myToken = Token()
    if (login == "123" && password == "123") {
        val register = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        myToken.tokenValue = (1..10).map { register.random() }.joinToString("")
    } else myToken.tokenValue = "Wrong"
    return myToken
}