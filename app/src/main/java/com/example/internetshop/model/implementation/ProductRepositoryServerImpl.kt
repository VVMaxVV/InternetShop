package com.example.internetshop.model.implementation

import android.accounts.NetworkErrorException
import com.example.internetshop.Product
import com.example.internetshop.model.data.dataclass.ProductItem
import com.example.internetshop.model.data.dataclass.ProductListItems
import com.example.internetshop.model.data.maper.ProductMapper
import com.example.internetshop.model.data.remote.ProductApi
import com.example.internetshop.model.interfaces.ProductCallback
import com.example.internetshop.model.interfaces.ProductListCallback
import com.example.internetshop.model.interfaces.ProductRepository
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryServerImpl @Inject constructor(private val productApi: ProductApi,private val productMapper: ProductMapper) :
    ProductRepository {
    override fun getProduct(id: String, productCallback: ProductCallback) {
        productApi.getProduct(id).enqueue(object : Callback<ProductItem> {
            override fun onResponse(call: Call<ProductItem>?, response: Response<ProductItem>?) {
                val responseBody = response?.body()
                if (responseBody != null) {
                    productCallback.onSuccess(responseBody)
                } else {
                    productCallback.onFail(IllegalStateException("Empty body"))
                }
            }

            override fun onFailure(call: Call<ProductItem>?, t: Throwable?) {
                productCallback.onFail(t ?: NetworkErrorException())
            }
        })
    }

    override fun getProductList(productListCallback: ProductListCallback) {
        productApi.getProductList().enqueue(object : Callback<List<ProductListItems>> {
            override fun onResponse(
                call: Call<List<ProductListItems>>?,
                response: Response<List<ProductListItems>>?
            ) {
                productListCallback.onSuccess(response?.body() ?: emptyList())
            }

            override fun onFailure(call: Call<List<ProductListItems>>?, t: Throwable?) {
                productListCallback.onFail(t ?: NetworkErrorException())
            }
        })
    }

    override fun getProductRx(id: String): Single<Product> {
        return productApi.getProductRx(id).map {
            productMapper.toDomain(it)
        }
    }

    override fun getProductsRx(): Single<List<Product>> {
        return productApi.getProductsRx().map {
            it.map {
                productMapper.toDomain(it)
            }
        }
    }
}