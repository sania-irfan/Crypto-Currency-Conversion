package com.sania.airliftcasestudy.models

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class ProductModel(
    @SerializedName("timestamp")
    var timestamp: Long = 0,
    @SerializedName("target")
    var target: String = "",
    @SerializedName("crypto")
    var crypto: String = "",
    @SerializedName("amount")
    var amount: Double = 0.0,
    @SerializedName("inputAmount")
    var inputAmount: Int = 1,
    @SerializedName("rates")
    var rates: LinkedHashMap<String, Double> = LinkedHashMap()

)
