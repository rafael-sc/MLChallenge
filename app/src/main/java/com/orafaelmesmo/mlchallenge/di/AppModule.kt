package com.orafaelmesmo.mlchallenge.di

import com.orafaelmesmo.mlchallenge.commom.NetworkUtils
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.data.remote.RetrofitClient
import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository
import com.orafaelmesmo.mlchallenge.data.repository.ProductRepositoryImpl
import com.orafaelmesmo.mlchallenge.domain.usecase.NetworkCheckUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.NetworkCheckUseCaseImpl
import com.orafaelmesmo.mlchallenge.domain.usecase.ProductsDetailsUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.ProductsDetailsUseCaseImpl
import com.orafaelmesmo.mlchallenge.domain.usecase.SearchProductsUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.SearchProductsUseCaseImpl
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.DetailsViewModel
import com.orafaelmesmo.mlchallenge.presentation.viewmodel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule =
    module {
        //commom
        single<NetworkUtils> {
            NetworkUtils(androidContext())
        }
        single<ResourceProvider> {
            ResourceProvider(androidContext())
        }

        // repository
        factory<ProductRepository> {
            ProductRepositoryImpl(
                apiService = get(),
                resourceProvider = get()
            )
        }
        // use case
        factory<SearchProductsUseCase> {
            SearchProductsUseCaseImpl(
                repository = get(),
            )
        }

        factory<ProductsDetailsUseCase> {
            ProductsDetailsUseCaseImpl(
                repository = get(),
            )
        }

        factory <NetworkCheckUseCase> {
            NetworkCheckUseCaseImpl(
                networkUtils = get()
            )
        }
        // view model
        viewModel<SearchViewModel> {
            SearchViewModel(
                searchProductsUseCase = get(),
                networkCheckUseCase = get(),
                resourceProvider = get()
            )
        }

        viewModel<DetailsViewModel> {
            DetailsViewModel(
                productsDetailsUseCase = get(),
                networkCheckUseCase = get(),
                resourceProvider = get()
            )
        }

        // network
        single<Retrofit> {
            RetrofitClient.retrofit
        }
        single<ProductApi> {
            get<Retrofit>().create(ProductApi::class.java)
        }
    }
