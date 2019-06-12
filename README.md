## Descrição avaliação Kabum
Dado o endpoint: 
```
    https://servicespub.prod.api.aws.grupokabum.com.br/home/v1/home/produto
    @GET
    app: Int -> flag para indicação da origem, app = 1
    limite: Int -> limite de produtos por página
    pagina: Int -> número de páginas
```

1. Modelar data classes necessários.
2. Consumir data classes em uma RecyclerView.
3. Paginar usando endless scrolling.
4. Estruturar arquitetura de projeto e explicá-la.
5. O app precisa permanecer com no mínimo 30fps (não apresentar freezing).
6. Usar o app da Kabum! como referência de estilo/design.
7. Kotlin deverá ser usado.
8. Subir desafio em um repositório do github

# Sobre o projeto

O projeto está nas `branchs`
* [rxjava](https://github.com/gdonscoi/testkabum/tree/rxjava)
* [kotlin_coroutines](https://github.com/gdonscoi/testkabum/tree/kotlin_coroutines)

[Pull request](https://github.com/gdonscoi/testkabum/pull/1) com as mudanças entre as branch`s 

## Arquitetura
A arquitetura usada foi o MVP, onde fiz a seguinte separação:
* `data`: com os data class (model) e definição e implementação da API (source)
* `presenter`: com a implementação e definição do fluxo e lógica 
* `view`: com a `Activity`, `Fragment` e `Adapter` ligados ao android 

## Registro das horas trabalhadas
* sexta 7/6 - 3 horas
* sábado 8/6 - 2 horas
* domingo 9/6 - 1 hora
* segunda 10/6 - 2 horas
* terça 11/6 - 5 horas
* quarta 12/6 - 1 hora

`total: 14 horas`

## Dificuldades

* `RecyclerView` dentro de um `Fragment`

No começo estava adicionando o `adapter` na `RecyclerView` no `onCreate` do `fragment`, porem, nesse momento a `view` ainda não está "desenhada", assim não atualizando a tela com os novos itens que foram adicionados

Com isso transferi a atribuição do `adapter` para `onViewCreated`


* `RecyclerView` + `AppBarLayout`:
 
Para fazer toda a junção da lista com o texto "Produtos em destaque" + o app bar, ocasionaram algumas buscas que consegui resolver desta maneira:

[`this.recyclerView.isNestedScrollingEnabled = false`](https://github.com/gdonscoi/testkabum/blob/rxjava/app/src/main/java/br/com/gdonscoi/testkabum/view/ListaProdutosFragment.kt#L50)
 
[`view.nested_scroll.setOnScrollChangeListener`](https://github.com/gdonscoi/testkabum/blob/rxjava/app/src/main/java/br/com/gdonscoi/testkabum/view/ListaProdutosFragment.kt#L52)

Para a `RecyclerView` trabalhar junto com a `NestedScrollView` e conseguir adicionar um `listener` para o _endless scroll_ 

* Estrelas da avaliação

Na primeira vez, as avaliações do `CardView` estavam ficando bagunçadas, mudei a implementação e adicionei no meu adapter
[`this.adapter.setHasStableIds(true)`](https://github.com/gdonscoi/testkabum/blob/rxjava/app/src/main/java/br/com/gdonscoi/testkabum/view/ListaProdutosFragment.kt#L37)

* Coroutines

A principio achei alguns tutoriais, mas ao colocar a ultima versão disponível, as chamadas do Coroutines mudaram
