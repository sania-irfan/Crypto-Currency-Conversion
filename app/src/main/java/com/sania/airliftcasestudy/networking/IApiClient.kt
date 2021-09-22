package com.sania.airliftcasestudy.networking

import com.sania.airliftcasestudy.models.ProductModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface IApiClient {

    @POST("live")
    fun getProducts(@Query("access_key") accessKey : String,
                    @Query("symbols") symbols : String,
                    @Query("target") target : String) : Call<ProductModel>


}