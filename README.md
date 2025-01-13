# CarrinhoComprasJava

Bem-vindo ao repositório do projeto Carrinho de Compras em Java! Este é um projeto desenvolvido para gerenciar um carrinho de compras utilizando Java e um banco de dados conectado via Docker.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal do projeto.
- **Docker**: Para gerenciar e conectar o banco de dados.
- **Maven**: Gerenciador de dependências do projeto.

## Estrutura do Projeto
A estrutura do projeto segue o padrão MVC (Model-View-Controller):
- **controller**: Contém as classes de controle, como `CarrinhoController` e `EstoqueController`.
- **model**: Contém as classes de modelo que representam os dados.
- **repository**: Responsável pela comunicação com o banco de dados.
- **view**: Contém as classes para exibição e interação com o usuário, como `MenuView`.

## Configuração do Projeto

### Requisitos
- Java 17 ou superior.
- Maven instalado.
- Docker e Docker Compose instalados.

### Passo a Passo para Configuração

1. **Clone o Repositório**
```bash
git clone https://github.com/seu-usuario/CarrinhoComprasJava.git
cd CarrinhoComprasJava
```

2. **Suba o Banco de Dados com Docker**
O Docker é usado para criar e gerenciar o banco de dados necessário para o projeto. Para isso, siga os passos abaixo:

- Certifique-se de que o Docker está instalado e rodando na sua máquina. Caso não tenha o Docker instalado, consulte a [documentação oficial do Docker](https://docs.docker.com/get-docker/) para fazer a instalação.


- No diretório do projeto, existe um arquivo chamado `docker-compose.yml` que contém as configurações necessárias para criar o banco de dados. O arquivo especifica que será usado o PostgreSQL versão 15 e define o nome do banco, usuário e senha.


- Para iniciar o banco de dados, execute o seguinte comando no terminal:
```bash
docker-compose up -d
```
Isso iniciará o container do banco de dados definido no arquivo `docker-compose.yml`.

- Você pode verificar se o banco de dados está rodando corretamente usando o comando:
```bash
docker ps
```
- Isso mostrará a lista de containers em execução, e o container `postgres_carrinho` deve estar entre eles.


3. **Configure o Banco de Dados**
   No arquivo de configuração do projeto, ajuste os parâmetros para a conexão com o banco de dados, caso necessário. Os valores padrão definidos no `docker-compose.yml` são:
- **URL**: jdbc:postgresql://localhost:5432/carrinho_db
- **Usuário**: admin
- **Senha**: postgres

4. **Compile e Execute o Projeto**
- Para compilar o projeto, execute:
```bash
mvn clean install
```

5. **Testando o Projeto**
   A aplicação deverá iniciar e exibir o menu principal no terminal. Siga as instruções para interagir com o carrinho de compras.

