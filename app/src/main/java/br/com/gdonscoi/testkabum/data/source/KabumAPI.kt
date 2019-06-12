package br.com.gdonscoi.testkabum.data.source

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class KabumAPI {

    private val service: KabumService

    init {
        val gson = GsonBuilder().setLenient().create()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://servicespub.prod.api.aws.grupokabum.com.br/home/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<KabumService>(KabumService::class.java)
    }

     fun loadHomeProdutos(page: Int, limit: Int = 5) = service.loadHomeProdutos(page, limit)
}
