package br.com.gdonscoi.testkabum.data.source

import br.com.gdonscoi.testkabum.data.model.ProdutosResposta
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface KabumService {
    @GET("home/produto?app=1")
    fun loadHomeProdutos(@Query("pagina") page: Int,
                         @Query("limite") limit: Int): Observable<ProdutosResposta>
}
