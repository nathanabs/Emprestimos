# Simulador de Crédito

Este serviço é um simulador de crédito que permite aos usuários simular empréstimos, visualizando as condições de pagamento baseadas no valor solicitado, taxa de juros e prazo de pagamento.

## Tecnologias Utilizadas

- **Java 21**: A aplicação é desenvolvida utilizando a versão 21 do Java.
- **Swagger 3.0**: Utilizado para documentar a API e gerar código automaticamente.

## Pré-requisitos

- Java 21 instalado.
- Maven instalado.

## Como Gerar o Código

Para gerar o código da aplicação, utilize o Maven com o seguinte comando:

```bash
  mvn clean package
```

# Endpoints Disponíveis

## 1. Simulação de Empréstimo

- **Descrição**: Permite simular um empréstimo com base no valor, data de nascimento do solicitante e número de parcelas.
- **Método HTTP**: `GET`
- **URL**: `http://localhost:8080/emprestimos`
- **Parâmetros**:
    - `valor` (obrigatório): Valor do empréstimo.
    - `dataNascimento` (obrigatório): Data de nascimento do solicitante no formato `AAAA-MM-DD`.
    - `parcelas` (obrigatório): Número de parcelas.
- **Exemplo de Requisição**:

  ```bash
  curl --location 'http://localhost:8080/emprestimos?valor=100&dataNascimento=1994-04-06&parcelas=10'
    ```
## 2. Simulação de Empréstimos em Lote

- **Descrição**: Permite simular múltiplos empréstimos em uma única requisição.
- **Método HTTP**: `POST`
- **URL**: `http://localhost:8080/emprestimos/batch`
- **Cabeçalhos**:
  - `Content-Type: application/json`
- **Corpo da Requisição**: Um array de objetos JSON, cada um contendo os campos `valor`, `dataNascimento` e `parcelas`.
- **Exemplo de Requisição**:

  ```bash
  curl --location 'http://localhost:8080/emprestimos/batch' \
  --header 'Content-Type: application/json' \
  --data '[
      {
          "valor": 100,
          "dataNascimento": "1994-04-06",
          "parcelas": 10
      },
      {
          "valor": 20000,
          "dataNascimento": "1980-04-06",
          "parcelas": 30
      },
      {
          "valor": 155646,
          "dataNascimento": "2000-04-06",
          "parcelas": 48
      }
  ]'
    ```
## Documentação da API
A documentação completa da API, gerada pelo Swagger 3.0, está disponível em:
```bash
  http://localhost:8080/swagger-ui.html
```