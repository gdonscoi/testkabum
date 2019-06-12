package br.com.gdonscoi.testkabum.data.model

import com.google.gson.annotations.SerializedName

data class ProdutosResposta(val produtos: List<Produto>)

data class Fabricante(val codigo: Int,
                      val nome: String,
                      @SerializedName("img") val imgUrl: String)

data class Produto(val codigo: Int,
                   @SerializedName("img") val imgUrl: String,
                   val nome: String,
                   @SerializedName("link_descricao") val linkDescricao: String,
                   val preco: String,
                   @SerializedName("preco_formatado") val precoFormatado: String,
                   @SerializedName("preco_desconto") val precoDesconto: String,
                   @SerializedName("preco_desconto_formatado") val precoDescontoFormatado: String,
                   @SerializedName("avaliacao_numero") val avaliacaoNumero: Int,
                   @SerializedName("avaliacao_nota") val avaliacaoNota: Int,
                   val fabricante: Fabricante,
                   val disponibilidade: Boolean)
