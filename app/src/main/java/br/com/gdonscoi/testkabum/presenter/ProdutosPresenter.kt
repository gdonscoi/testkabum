package br.com.gdonscoi.testkabum.presenter

import android.util.Log
import br.com.gdonscoi.testkabum.data.model.Produto
import br.com.gdonscoi.testkabum.data.source.KabumAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProdutosPresenter : ProdutosContract.Presenter {

    lateinit var view: ProdutosContract.View
    private val subscriptions = CompositeDisposable()
    private var loading = false
    private var page: Int = 1

    override fun attachView(view: ProdutosContract.View) {
        this.view = view
    }

    override fun loadProdutos() {
        val produtosAPI = ArrayList<Produto>()
        this.loading = true
        view.showTopLoading(this.loading)
        this.page = 1

        val subscription = KabumAPI().loadHomeProdutos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ produto ->
                    produtosAPI.add(produto)
                }, { e ->
                    Log.e("Presenter", e.message)
                    view.showTopLoading(false)
                    view.showError()
                }, {
                    view.updateList(produtosAPI)
                    this.loading = false
                    view.showTopLoading(this.loading)
                })

        subscriptions.add(subscription)
    }

    override fun loadMoreProdutos() {
        val produtosAPI = ArrayList<Produto>()
        this.loading = true
        view.showBottomLoading(this.loading)
        this.page++

        val subscription = KabumAPI().loadHomeProdutos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ produto ->
                    produtosAPI.add(produto)
                }, { e ->
                    Log.e("Presenter", e.message)
                    view.showTopLoading(false)
                    view.showError()
                }, {
                    view.addList(produtosAPI)
                    this.loading = false
                    view.showBottomLoading(this.loading)
                })

        subscriptions.add(subscription)
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun isLoading(): Boolean {
        return this.loading
    }
}
