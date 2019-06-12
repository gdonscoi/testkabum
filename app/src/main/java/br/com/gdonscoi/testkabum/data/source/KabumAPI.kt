package br.com.gdonscoi.testkabum.data.source

import br.com.gdonscoi.testkabum.data.model.Produto
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<KabumService>(KabumService::class.java)
    }

    fun loadHomeProdutos(page: Int, limit: Int = 5): Observable<Produto> {
        return service.loadHomeProdutos(page, limit)
                .flatMap { produtos -> Observable.fromIterable(produtos.produtos) }
    }
}
