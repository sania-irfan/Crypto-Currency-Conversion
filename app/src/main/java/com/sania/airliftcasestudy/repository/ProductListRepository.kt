package com.sania.airliftcasestudy.repository

import android.app.Application
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sania.airliftcasestudy.R
import com.sania.airliftcasestudy.models.ProductModel
import com.sania.airliftcasestudy.networking.IApiClient
import com.sania.airliftcasestudy.networking.NetController
import com.sania.airliftcasestudy.ui.utility.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class ProductListRepository constructor(app: Application) {

    //    private var mCurrencyDao: CurrencyDao
    private var mAllCryptoList: MutableList<ProductModel>? = null
    var sharedPref = SharedPrefManager.getInstance(app.applicationContext)

    init {
//        val db: AppDatabase? = AppDatabase.getDatabase(app)
//        mCurrencyDao = db?.currencyDao()!!

        mAllCryptoList = sharedPref.getCollectionFromSharedPref(
            "key_list",
            ProductModel::class.java
        )
    }

    fun fetchConversionFromServer(
        accessKey: String,
        crypto: String,
        currency: String,
        success: (postsList: ProductModel) -> Unit,
        failure: (message: String) -> Unit
    ) {
        val call = NetController.createService(IApiClient::class.java)
            .getProducts(accessKey, crypto, currency)
        call.enqueue(object : Callback<ProductModel> {

            override fun onResponse(
                call: Call<ProductModel>,
                response: Response<ProductModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        success(it)
                        return
                    }
                    failure("Unknown Error Occurred!")
                    return
                } else {
                    // Get the error from response
                    failure("Any server error occurred!")
                }
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                // Show other errors like network connectivity etc here
                failure(t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    fun getAllCryptoList(): MutableList<ProductModel>? {
        return mAllCryptoList
    }

    fun insertCrypto(amount : Int, crypto: String, currencyModel: ProductModel) {
        currencyModel.amount = (amount * currencyModel.rates[crypto]!!)
        currencyModel.crypto = crypto
        currencyModel.inputAmount = amount
        if(mAllCryptoList == null)
            mAllCryptoList = mutableListOf()
        if (!mAllCryptoList?.contains(currencyModel)!!) {
            mAllCryptoList?.add(currencyModel)
        }

        sharedPref.saveCollectionTOSharedPref(
            mAllCryptoList as MutableList<ProductModel>,
            "key_list"
        )

    }

}