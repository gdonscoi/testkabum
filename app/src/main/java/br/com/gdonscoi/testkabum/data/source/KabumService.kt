package br.com.gdonscoi.testkabum.data.source

import br.com.gdonscoi.testkabum.data.model.ProdutosResposta
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface KabumService {
    @GET("home/produto?app=1")
    suspend fun loadHomeProdutos(@Query("pagina") page: Int,
                         @Query("limite") limit: Int): Deferred<ProdutosResposta>
}
