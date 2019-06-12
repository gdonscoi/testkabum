package br.com.gdonscoi.testkabum.presenter

import br.com.gdonscoi.testkabum.data.model.Produto

interface ProdutosContract {

    interface View {

        fun showTopLoading(value: Boolean)

        fun showBottomLoading(value: Boolean)

        fun updateList(list: List<Produto>)

        fun addList(list: List<Produto>)

        fun showError()
    }

    interface Presenter {

        fun attachView(view: View)

        fun isLoading(): Boolean

        fun loadProdutos()

        fun loadMoreProdutos()

        fun unsubscribe()
    }
}
