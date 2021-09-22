package com.sania.airliftcasestudy.screen.states

data class ProductListScreenState(
    val showLoading: Boolean = false,
    val showContent: Boolean = false,
    val showError: Boolean = false,
    val showEmpty: Boolean = false,
    val errorMessage: String = ""
)
