# 🧠 Sem-Bet API

API RESTful desenvolvida em Java com Spring Boot 3 para gerenciar usuários e acessos, seguindo os princípios da **Arquitetura Orientada a Serviços (SOA)**, com ênfase em **segurança, boas práticas, versionamento, testes e governança**.

---

## 🧱 Arquitetura

- 🔁 **SOA Modular**: serviços desacoplados, reutilizáveis e independentes.
- 🧩 **Camadas**: separação clara entre apresentação, serviço e dados (MVC).
- 🛡️ **Segurança JWT**: autenticação com tokens.
- 📦 **DAO, DTO, Factory** e padrões de projeto aplicados.
- 📊 **Testes de carga com JMeter**.
- 🚀 **CI/CD com GitHub Actions**.

---

## 🚀 Endpoints (v1)

### Autenticação
```http
POST /auth/login
````

### Usuários

```http
GET /v1/usuarios
GET /v1/usuarios/{id}
POST /v1/usuarios
PUT /v1/usuarios/{id}
DELETE /v1/usuarios/{id}
```

### Acessos

```http
GET /v1/acessos
GET /v1/acessos/{id}
GET /v1/acessos/acao?acao=login
POST /v1/acessos
DELETE /v1/acessos/{id}
```

> Todos os endpoints (exceto `/auth/**`) requerem autenticação JWT via header:
>
> ```
> Authorization: Bearer {seu_token}
> ```

---

## 📚 Tecnologias

* Java 17+
* Spring Boot 3
* Spring Security (JWT)
* Spring Data JPA
* Banco H2 (testes) ou PostgreSQL
* Swagger / OpenAPI
* JMeter (testes de carga)
* GitHub Actions (CI/CD)

---

## 📄 Governança de Serviços

### ✅ Versionamento de API

* Estrutura de endpoints organizada por versão (`/v1/...`)
* Possibilidade de evolução sem quebrar contratos antigos

### ✅ Contratos de Serviço

* Especificações OpenAPI geradas automaticamente
* Acesse a documentação Swagger:

  ```
  /swagger-ui.html
  /v3/api-docs
  ```

> O contrato da API também está disponível no repositório [sem-bet-contracts](https://github.com/seuusuario/sem-bet-contracts)

### ✅ Políticas de Acesso

* Endpoint de login para obter JWT
* Apenas usuários autenticados têm acesso aos endpoints protegidos
* Regras de segurança definidas no arquivo `SecurityConfig.java`

---

## ⚙️ CI/CD com GitHub Actions

Este projeto usa GitHub Actions para:

* 🧪 Executar testes automatizados
* ✅ Validar build e dependências
* 📥 Publicar contratos OpenAPI (se configurado)

Arquivo `.github/workflows/ci.yml` define o pipeline.

---

## 🧪 Testes com JMeter

Scripts `.jmx` disponíveis em `jmeter/`. Execute:

```bash
jmeter -n -t jmeter/teste-login.jmx -l jmeter/resultados.jtl
```

---

## 🔐 Variáveis de Ambiente

Configure o `application.properties` ou use um `.env`:

```properties
jwt.secret=chave_super_secreta
spring.datasource.url=jdbc:h2:mem:testdb
```

---

## 👨‍💻 Autores

| Nome                    | RM     |
| ----------------------- | ------ |
| Eduardo Pielich Sanchez | 99767  |
| Débora da Silva Amaral  | 550412 |
| Lívia Namba Seraphim    | 97819  |

---

## 📁 Estrutura

```
sem-bet-api/
├── controller/
├── service/
├── model/
├── repository/
├── dto/
├── security/
├── config/
├── util/
└── jmeter/
```

---

## 🛡️ Análise Estática de Segurança com Semgrep

Realizamos uma varredura completa do código-fonte utilizando a ferramenta **Semgrep** com o conjunto de regras **OWASP Top 10** para identificar possíveis vulnerabilidades comuns, como injeção de SQL, Cross-Site Scripting (XSS), exposição de senhas em texto plano, entre outras falhas de segurança.

Após a execução da análise estática, **não foram encontradas vulnerabilidades ou falhas relevantes** no código, indicando que as boas práticas de segurança e validação estão sendo corretamente aplicadas no projeto.

---

## 📦 Licença

Este projeto é livre para fins educacionais.
