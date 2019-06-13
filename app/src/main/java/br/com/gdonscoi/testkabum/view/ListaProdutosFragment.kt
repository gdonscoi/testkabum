package br.com.gdonscoi.testkabum.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import br.com.gdonscoi.testkabum.R
import br.com.gdonscoi.testkabum.data.model.Produto
import br.com.gdonscoi.testkabum.presenter.ProdutosContract
import br.com.gdonscoi.testkabum.presenter.ProdutosPresenter
import kotlinx.android.synthetic.main.lista_produtos_fragment.view.*


class ListaProdutosFragment : Fragment(), ProdutosContract.View {

    private lateinit var presenter: ProdutosContract.Presenter
    private val produtos: MutableList<Produto> = mutableListOf()
    private lateinit var adapter: ProdutosViewAdapter
    private lateinit var listRefresh: SwipeRefreshLayout
    private lateinit var bottomLoad: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = ProdutosPresenter()
        presenter.attachView(this)

        this.adapter = ProdutosViewAdapter(produtos)
        this.adapter.setHasStableIds(true)

        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lista_produtos_fragment, container, false)

        this.linearLayoutManager = LinearLayoutManager(context)
        this.recyclerView = view.lista_produtos
        this.recyclerView.layoutManager = this.linearLayoutManager
        this.recyclerView.isNestedScrollingEnabled = false

        view.nested_scroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { nestedScroll, _, scrollY, _, _ ->
            if (scrollY == (nestedScroll?.getChildAt(0)?.measuredHeight?.minus(view.measuredHeight))) {
                if (!presenter.isLoading()) presenter.loadMoreProdutos()
            }
        })

        this.listRefresh = view.list_swipe
        this.listRefresh.setOnRefreshListener { presenter.loadProdutos() }
        this.listRefresh.setColorSchemeResources(R.color.colorAccent)

        this.bottomLoad = view.bottom_loading

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.recyclerView.adapter = this.adapter

        presenter.loadProdutos()
    }

    override fun showTopLoading(value: Boolean) {
        this.listRefresh.isRefreshing = value
    }

    override fun showBottomLoading(value: Boolean) {
        this.bottomLoad.visibility = if (value) View.VISIBLE else View.GONE
    }

    override fun updateList(list: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(list)
        this.adapter.notifyDataSetChanged()
    }

    override fun addList(list: List<Produto>) {
        this.produtos.addAll(list)
        this.adapter.notifyItemRangeInserted(this.adapter.itemCount-1, list.size)
    }

    override fun showError() {
        Toast.makeText(activity, R.string.error_message, Toast.LENGTH_SHORT).show()
    }
}
