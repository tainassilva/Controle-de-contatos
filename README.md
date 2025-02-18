<h1 align="center" style="font-weight: bold;">Controle de Contatos 📞</h1>


<p align="center">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring" />
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java" />
</p>



<p align="center">
  <b>Projeto API rest para gerenciar contatos de uma pessoa, construído com Java e Spring Boot para o processo seletivo da empresa Minsait.</b>
</p>
<h3>📝 Pré-requisitos </h3>

Aqui está a lista de todos os pré-requisitos necessários para executar o projeto:

- **[Java 21](https://www.oracle.com/br/java/technologies/downloads/#java21)**: A versão do Java compatível com o projeto. Você pode fazer o download do JDK 21, que é compatível com o Spring Boot 3.4.2.
- **[Maven 3.8+](https://maven.apache.org/)**: Maven é uma ferramenta de automação de build. A versão 3.8 ou superior é recomendada.
- **IDE (como IntelliJ IDEA, Eclipse ou VS Code)** Um ambiente de desenvolvimento integrado para facilitar a construção do projeto.



<h2 id="started">🚀 Como executar</h2>


### Etapas
1. **Clone o repositório**

Para começar, você precisa clonar o repositório em sua máquina local. Use o comando `git clone`:

```bash
git clone https://github.com/tainassilva/Controle-de-contatos.git
```

2. **Execute o projeto**
 Depois de fazer o clone do projeto, abra o terminal na pasta do projeto e execute o seguinte comando para rodar o projeto com Maven:

```bash
mvn spring-boot:run
```

Ou abra na sua IDE e execute o arquivo ControleDeContatosApplication dentro da pasta br.com.taina.

O banco de dados é o H2 Database, um banco de dados em memória. Você pode acessá-lo através do endereço http://localhost:8080/h2-console.

Para testar a API, você pode usar o Swagger. Acesse pelo endereço http://localhost:8080/swagger-ui.html.

3.**Execute os testes caso queira**

Se você deseja executar os testes automatizados, o Maven facilita isso com o seguinte comando:
```bash
mvn test
```
Se você preferir usar a sua IDE, você pode simplesmente rodar os testes clicando com o botão direito no arquivo de teste e selecionando "Run as" > "JUnit Test" (dependendo da IDE que você está utilizando).


### API Endpoints 📍
Aqui estão os principais endpoints da API e os detalhes de como interagir com eles.

<b>Endpoints da entidade pessoa</b>
<table border="1" cellpadding="10" cellspacing="0" style="width: 100%; text-align: center;">
  <thead>
    <tr>
      <th>Rota</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><kbd>POST /api/pessoas</kbd></td>
      <td>Cria uma nova pessoa.</td>
    </tr>
    <tr>
      <td><kbd>GET /api/pessoas/{id}</kbd></td>
      <td>Retorna os dados de uma pessoa por ID.</td>
    </tr>
     <tr>
      <td><kbd>GET /api/pessoas/maladireta/{id}</kbd></td>
      <td>Retorna os dados de uma pessoa por ID para mala direta.</td>
    </tr>
     <tr>
      <td><kbd>GET /api/pessoas</kbd></td>
      <td>Lista todas as pessoas.</td>
    </tr>
     <tr>
      <td><kbd>PUT /api/pessoas/{id}</kbd></td>
      <td>Atualiza uma pessoa existente.</td>
    </tr>
     <tr>
      <td><kbd>DELETE /api/pessoas/{id}</kbd></td>
      <td>Remove uma pessoa por ID.</td>
    </tr>
  </tbody>
</table>


<b>Endpoints da entidade contato</b>
<table border="1" cellpadding="10" cellspacing="0" style="width: 100%; text-align: center;">
  <thead>
    <tr>
      <th>Rota</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><kbd>POST /api/contatos</kbd></td>
      <td>Adiciona um novo contato a uma pessoa.</td>
    </tr>
    <tr>
      <td><kbd>GET /api/contatos/{id}</kbd></td>
      <td>Retorna os dados de um contato por ID.</td>
    </tr>
     <tr>
      <td><kbd>GET /api/contatos/pessoa/{idPessoa}</kbd></td>
      <td>Retorna todos os contatos de uma pessoa.</td>
    </tr>
     <tr>
      <td><kbd>PUT /api/contatos/{id}</kbd></td>
      <td>Atualiza um contato existente.</td>
    </tr>
     <tr>
      <td><kbd>DELETE /api/contatos/{id}</kbd></td>
      <td>Remove um contato por ID.</td>
    </tr>
  </tbody>
</table>

<h2>Request e Response da entidade Pessoa</h2>
<h3><b>POST /api/pessoas</b></h3>

**REQUEST**
```json
{
  "nome": "Taina",
  "endereco": "Rua Penha, 102",
  "cep": "06700000",
  "cidade": "Cotia",
  "uf": "SP"
}
```

**RESPONSE**
```json
{
  "id": 1,
  "nome": "Taina",
  "endereco": "Rua Penha, 102",
  "cep": "06700000",
  "cidade": "Cotia",
  "uf": "SP"
}
```

<h3><b>GET /api/pessoas/{id}</b></h3>

**REQUEST**
```http
localhost:8080/api/pessoas/1
```

**RESPONSE**
```json
{
  "id": 1,
  "nome": "Taina",
  "endereco": "Rua Penha, 102",
  "cep": "06700000",
  "cidade": "Cotia",
  "uf": "SP"
}
```
<h3><b>GET /api/pessoas/maladireta/{id}</b></h3>

**REQUEST**
```http
localhost:8080/api/pessoas/maladireta/1
```

**RESPONSE**
```json
{
  "id": 1,
  "nome": "Taina",
  "malaDireta": "Rua Penha, 102 – CEP: 06700000 – Cotia / SP"
}
```

<h3><b>GET /api/pessoas</b></h3>

**REQUEST**
```http
localhost:8080/api/pessoas
```

**RESPONSE**
```json
[
  {
    "id": 1,
    "nome": "Taina",
    "endereco": "Rua Penha, 102",
    "cep": "06700000",
    "cidade": "Cotia",
    "uf": "SP"
  },
  {
    "id": 2,
    "nome": "Gabriela",
    "endereco": "Rua Penha, 135",
    "cep": "06700000",
    "cidade": "Cotia",
    "uf": "SP"
  }
]
```
<h3><b>PUT /api/pessoas/{id}</b></h3>

**REQUEST**

```http
localhost:8080/api/pessoas/1
```

```json
{
  "nome": "Taina Santos Silva",
  "endereco": "Rua Penha, 102",
  "cep": "06700000",
  "cidade": "Cotia",
  "uf": "SP"
}
```

**RESPONSE**
```json
{
  "id": 1,
  "nome": "Taina Santos Silva",
  "endereco": "Rua Penha, 102",
  "cep": "06700000",
  "cidade": "Cotia",
  "uf": "SP"
}
```

<h3><b>DELETE /api/pessoas/{id}</b></h3>

**REQUEST**
```http
localhost:8080/api/pessoas/1
```

**RESPONSE**
```http
HTTP/1.1 204 NO CONTENT
```




<h2>Request e Response da entidade Contato</h2>
<h3><b>POST /api/contatos</b></h3>

**REQUEST**
```json
{
  "tipoContato": "CELULAR",
  "contato": "11974510719",
  "idPessoa": 1
}
```

**RESPONSE**
```json
{
  "id": 1,
  "tipoContato": "CELULAR",
  "contato": "11974510719",
  "idPessoa": 1
}
```

<h3><b>GET /api/contatos/{id}</b></h3>

**REQUEST**
```http
localhost:8080/api/contatos/1
```

**RESPONSE**
```json
{
  "id": 1,
  "tipoContato": "CELULAR",
  "contato": "11974510719",
  "idPessoa": 1
}
```
<h3><b>GET /api/contatos/pessoa/{idPessoa}</b></h3>

**REQUEST**
```http
localhost:8080/api/contatos/pessoa/1
```

**RESPONSE**
```json
[
  {
    "id": 1,
    "tipoContato": "CELULAR",
    "contato": "11974510719",
    "idPessoa": 1
  },
  {
    "id": 2,
    "tipoContato": "EMAIL",
    "contato": "email@email.com",
    "idPessoa": 1
  }
]
```


<h3><b>PUT /api/contatos/{id}</b></h3>

**REQUEST**

```http
localhost:8080/api/contatos/1
```

```json
{
  "tipoContato": "EMAIL",
  "contato": "email@email.com",
  "idPessoa": 1
}
```

**RESPONSE**
```json
{
  "id": 1,
  "tipoContato": "EMAIL",
  "contato": "email@email.com",
  "idPessoa": 1
}
```

<h3><b>DELETE /api/contatos/{id}</b></h3>

**REQUEST**
```http
localhost:8080/api/contatos/1
```

**RESPONSE**
```http
HTTP/1.1 204 NO CONTENT
```
## 🤝 Contribuindo com o Projeto

Aqui você encontrará como outros desenvolvedores podem contribuir com o seu projeto. Seguem as etapas para contribuir:

1. Faça um fork do repositório para sua conta.
   
2. Clone o repositório para o seu computador:
```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
```
1. Crie uma nova branch para sua funcionalidade ou correção:


```bash
git checkout -b feature/NAME
```
4. Siga os padrões de commits (use mensagens claras e objetivas).
   
5. Faça o commit das suas mudanças:
```bash
git add .
git commit -m "Adicionando nova funcionalidade X"
```
6. Envie suas alterações para o repositório remoto:
```bash
git push origin feature/NOME-DESAFIO
```
7. Abra um Pull Request explicando o problema que foi resolvido ou a nova funcionalidade implementada. Se houver mudanças visuais, adicione capturas de tela para ilustrar.

**Aguarde pela revisão do código e feedback!**


