package br.com.gdonscoi.testkabum.presenter

import br.com.gdonscoi.testkabum.data.model.ProdutosResposta
import br.com.gdonscoi.testkabum.data.source.KabumAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProdutosPresenter : ProdutosContract.Presenter {

    lateinit var view: ProdutosContract.View
    private var loading = false
    private var page: Int = 1

    override fun attachView(view: ProdutosContract.View) {
        this.view = view
    }

    override fun loadProdutos() {
        GlobalScope.launch(Dispatchers.Main) {
            loading = true
            view.showTopLoading(loading)
            page = 1

            val result = callAPI()

            result?.produtos?.let { view.updateList(it) }
            loading = false
            view.showTopLoading(loading)
        }
    }


    override fun loadMoreProdutos() {
        GlobalScope.launch(Dispatchers.Main) {
            loading = true
            view.showBottomLoading(loading)
            page++

            val result = callAPI()

            result?.produtos?.let { view.addList(it) }
            loading = false
            view.showBottomLoading(loading)
        }
    }

    override fun isLoading(): Boolean {
        return this.loading
    }

    private suspend fun callAPI(): ProdutosResposta? {
        return try {
            KabumAPI().loadHomeProdutos(page).await()
        } catch (e: Throwable) {
            view.showError()
            null
        }
    }
}
