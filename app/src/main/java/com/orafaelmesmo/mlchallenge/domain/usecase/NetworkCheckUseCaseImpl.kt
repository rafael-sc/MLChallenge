package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.commom.NetworkUtils

class NetworkCheckUseCaseImpl(private val networkUtils: NetworkUtils) : NetworkCheckUseCase {
    override fun isConnected(): Boolean {
        return networkUtils.isConnected()
    }
}
