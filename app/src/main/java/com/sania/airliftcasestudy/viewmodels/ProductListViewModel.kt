package com.sania.airliftcasestudy.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sania.airliftcasestudy.models.ProductModel
import com.sania.airliftcasestudy.repository.ProductListRepository
import com.sania.airliftcasestudy.screen.states.ProductListScreenState

class ProductListViewModel @JvmOverloads constructor(
    app: Application,
    private val repository: ProductListRepository = ProductListRepository(app)
) : ObservableViewModel(app) {

    private var screenState = MutableLiveData<ProductListScreenState>()
    private var postsList = MutableLiveData<List<ProductModel>>()

    fun getObservedScreenState() = screenState

    fun getObservedPostsList() = postsList

    fun addCrypto(accesskey: String, amount: Int, crypto: String, currency: String) {
        screenState.postValue(
            ProductListScreenState(
                showLoading = true
            )
        )
        repository.fetchConversionFromServer(accesskey, crypto, currency, success = {

            repository.insertCrypto(amount , crypto, it)

            fetchAllCryptoListFromDb()

        }, failure = {
            screenState.postValue(
                ProductListScreenState(
                    showError = true,
                    errorMessage = it
                )
            )
        })
    }

    fun fetchAllCryptoListFromDb() {
        if (repository.getAllCryptoList() == null)
            screenState.postValue(
                ProductListScreenState(
                    showEmpty = true
                )
            )
        else {
            postsList.postValue(repository.getAllCryptoList())
            screenState.postValue(
                ProductListScreenState(
                    showContent = true
                )
            )
        }

    }
}