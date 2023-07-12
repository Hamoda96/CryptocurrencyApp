package com.hamoda.coins.module.koin

import com.hamoda.coins.core.data.repository.CoinsRemote
import com.hamoda.coins.core.domain.repository.BaseCoinsRemote
import com.hamoda.coins.core.domain.use_case.get_coins.GetCoinsUseCase
import com.hamoda.coins.module.ktor.data.repository.KtorHttpClient
import com.hamoda.coins.core.presentation.coin_list.components.CoinListViewModel
import com.shipblu.corekt.domain.httpclient.repositories.BaseHttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.statement.HttpResponse
import org.koin.dsl.module

val moduleInjection = module {

    single<BaseHttpClient<HttpResponse>> {
        KtorHttpClient(Android.create())
    }

    single<BaseCoinsRemote> {
        CoinsRemote(get())
    }

    single {
        CoinListViewModel(get())
    }

    single {
        GetCoinsUseCase(get())
    }
}