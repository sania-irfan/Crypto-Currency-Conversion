package com.sania.airliftcasestudy.ui.databinding

import com.sania.airliftcasestudy.R
import com.sania.airliftcasestudy.models.SpinnerModel

object SpinnerConstants {

    val countryList = arrayListOf(
        SpinnerModel("AUD", R.mipmap.icon_ic_australia),
        SpinnerModel("CAD", R.mipmap.icon_ic_canada),
        SpinnerModel("CHF", R.mipmap.icon_ic_switzerland),
        SpinnerModel("CNY", R.mipmap.icon_ic_china),
        SpinnerModel("DKK", R.mipmap.icon_ic_denmark),
        SpinnerModel("EUR", R.mipmap.icon_ic_euro),
        SpinnerModel("GBP", R.mipmap.icon_ic_gbp),
        SpinnerModel("INR", R.mipmap.icon_ic_india),
        SpinnerModel("JPY", R.mipmap.icon_ic_japan),
        SpinnerModel("KRW", R.mipmap.icon_ic_southkorea),
        SpinnerModel("MXN", R.mipmap.icon_ic_mexico),
        SpinnerModel("NGN", R.mipmap.icon_ic_nigeria),
        SpinnerModel("NOK", R.mipmap.icon_ic_norway),
        SpinnerModel("NZD", R.mipmap.icon_ic_newzealand),
        SpinnerModel("RUB", R.mipmap.icon_ic_russia),
        SpinnerModel("SAR", R.mipmap.icon_ic_southafrica),
        SpinnerModel("SEK", R.mipmap.icon_ic_sweden),
        SpinnerModel("SGD", R.mipmap.icon_ic_singapore),
        SpinnerModel("TRY", R.mipmap.icon_ic_turkey),
        SpinnerModel("USD", R.mipmap.icon_ic_usa)
    )

    val cryptoList = arrayListOf(
        SpinnerModel("BTC", R.mipmap.btc),
        SpinnerModel("ETH", R.mipmap.eth)
    )
}