package com.smlnskgmail.jaman.cryptotracker.model.api.currency

import java.io.Serializable

interface Currency : Serializable {

    fun name(): String
    fun symbol(): String
    fun slug(): String
    fun firstHistoricalData(): String
    fun lastHistoricalData(): String

    fun updateCurrencyListing(
        currencyListing: CurrencyListing
    )
    fun currencyListing(): CurrencyListing

    fun currencyType(): CurrencyType

}
