package com.sania.airliftcasestudy.ui.utility

import com.sania.airliftcasestudy.R

class Helper {

    companion object {
        @JvmStatic
        fun getCryptoIcon(cryptoName: String): Int {
            return if (cryptoName == "BTC") {
                R.mipmap.btc
            } else {
                R.mipmap.eth
            }
        }
    }
}