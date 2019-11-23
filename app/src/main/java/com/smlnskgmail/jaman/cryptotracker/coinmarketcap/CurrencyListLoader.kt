package com.smlnskgmail.jaman.cryptotracker.coinmarketcap

import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.CurrencyApi
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.api.responses.CurrencyResponse
import com.smlnskgmail.jaman.cryptotracker.coinmarketcap.model.CurrencyMedia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyListLoader(
    private val api: CurrencyApi,
    private val currencyListLoaderTarget: CurrencyListLoaderTarget
) {

    fun loadCurrencies() {
        api.currencyService().currencyList(
            CurrencyMedia.supportedSymbols().joinToString(
                ","
            ).dropLast(
                1
            )
        ).enqueue(
            object : Callback<CurrencyResponse> {
                override fun onFailure(
                    call: Call<CurrencyResponse>,
                    t: Throwable
                ) {
                    currencyListLoaderTarget.loaderResult(
                        emptyList(),
                        t
                    )
                }

                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ) {
                    val body = response.body()
                    if (body == null) {
                        currencyListLoaderTarget.loaderResult(
                            emptyList(),
                            null
                        )
                    } else {
                        currencyListLoaderTarget.loaderResult(
                            body.currencies,
                            null
                        )
                    }
                }
            }
        )
    }

}