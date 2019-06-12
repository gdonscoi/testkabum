package br.com.gdonscoi.testkabum.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.gdonscoi.testkabum.data.model.Produto
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.container_avaliacao_card.view.*
import kotlinx.android.synthetic.main.container_preco_card.view.*
import kotlinx.android.synthetic.main.container_preco_desconto_card.view.*
import kotlinx.android.synthetic.main.container_top_card.view.*
import kotlinx.android.synthetic.main.produto_card.view.*


class ProdutosViewAdapter(private val produtos: MutableList<Produto>) : RecyclerView.Adapter<ProdutosViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(br.com.gdonscoi.testkabum.R.layout.produto_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nome.text = produtos[position].nome
        holder.precoDesconto.text = produtos[position].precoDescontoFormatado
        holder.preco.text = produtos[position].precoFormatado
        holder.avaliacaoNumero.text = produtos[position].avaliacaoNumero.toString()

        for (i in 1..produtos[position].avaliacaoNota)
            holder.containerNota[i - 1].imageTintList = ColorStateList.valueOf(Color.parseColor("#FFFF5722"))

        Picasso.get().load(produtos[position].imgUrl).into(holder.imgProduto)
        Picasso.get().load(produtos[position].fabricante.imgUrl).into(holder.imgFabricante)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.nome
        val precoDesconto: TextView = view.preco_desconto
        val preco: TextView = view.preco
        val imgProduto: ImageView = view.img_produto
        val imgFabricante: ImageView = view.img_fabricante
        val avaliacaoNumero: TextView = view.avaliacao_numero
        val containerNota: ArrayList<ImageView> = arrayListOf(view.avaliacao_img1,
                view.avaliacao_img2, view.avaliacao_img3, view.avaliacao_img4, view.avaliacao_img5)
    }
}
