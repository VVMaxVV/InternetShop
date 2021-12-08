package com.example.internetshop.model.implementation

import android.accounts.NetworkErrorException
import com.example.internetshop.Product
import com.example.internetshop.model.data.remote.ProductListItems
import com.example.internetshop.model.data.remote.RetrofitProvader
import com.example.internetshop.model.interfaces.ProductListCallback
import com.example.internetshop.model.interfaces.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryServerImpl: Repository {
    private val api = RetrofitProvader.getAPI()
    override fun getProduct(): Product {
        TODO("Not yet implemented")
    }

    override fun getProductList(productListCallback: ProductListCallback) {
        api.getProductList().enqueue(object : Callback<List<ProductListItems>>{
            override fun onResponse(
                call: Call<List<ProductListItems>>?,
                response: Response<List<ProductListItems>>?
            ) {
                productListCallback.onSucses(response?.body()?: emptyList())
            }

            override fun onFailure(call: Call<List<ProductListItems>>?, t: Throwable?) {
                productListCallback.onFail(t?: NetworkErrorException())
            }

        })
    }

}