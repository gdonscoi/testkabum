package br.com.gdonscoi.testkabum.presenter

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

            val result = KabumAPI().loadHomeProdutos(page).await()
            view.updateList(result.produtos)
            loading = false
            view.showTopLoading(loading)
        }
    }


    override fun loadMoreProdutos() {
        GlobalScope.launch(Dispatchers.Main) {
            loading = true
            view.showBottomLoading(loading)
            page++

            val result = KabumAPI().loadHomeProdutos(page).await()
            view.addList(result.produtos)
            loading = false
            view.showBottomLoading(loading)
        }
    }

    override fun isLoading(): Boolean {
        return this.loading
    }
}
