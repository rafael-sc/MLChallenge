# ML Challenge

Projeto feito para a avaliação técnica do Mercado Livre.

## Funcionalidades

- Busca de produtos
- Detalhes do produto
- Tela de configurações

## Estrutura do Projeto

A arquitetura do projeto é baseada em Clean Architecture e MVVM.

- `data`: Contém os modelos de dados e a implementação dos repositórios.
- `domain`: Contém as interfaces dos repositórios e os casos de uso.
- `presentation`: Contém as telas, view models e navegação.
- `ui`: Contém os componentes de UI reutilizáveis.

A injeção de dependências é feita com Koin.


## Dependências

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Koin](https://insert-koin.io/)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [coil 3](https://coil-kt.github.io/coil/)

## Configuração

1. Clone o repositório:
    ```sh
    git clone https://github.com/rafael-sc/MLChallenge.git
    ```   
   ou
    ```sh
    git clone git@github.com:rafael-sc/MLChallenge.git
    ```
2. Abra o projeto no Android Studio.
3. Sincronize o projeto com o Gradle.

## Uso

### Busca de Produtos

A tela principal permite buscar produtos por nome. Os resultados são exibidos em uma lista paginada.

### Detalhes do Produto

Ao clicar em um produto na lista, você será levado para a tela de detalhes do produto, onde mais informações sobre o produto são exibidas.

### Configurações

A tela de configurações permite alternar entre tema claro e escuro e ajustar o tamanho do texto.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.