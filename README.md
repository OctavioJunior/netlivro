
# API NetLivro

Uma API para o acompanhamento de estoque de uma biblioteca física, fornecendo a opção de empréstimo publicamente para qualquer usuário.

Projeto proposto para estudo e conhecimento da stack Java e Spring Boot.


## Properties

Para rodar esse projeto, você vai precisar criar o arquivo `application.properties` dentro de `src/main/resources` com as seguintes propriedades:

```
spring.jpa.database=postgresql
spring.sql.init.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/<nome_do_banco>
spring.datasource.username=<usuario_do_banco>
spring.datasource.password=<senha_do_banco>
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# jwt
netlivro.jwt.secret=
netlivro.jwt.expiration=

spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

OBS: É possível utilizar outro banco de dados, alterando as propriedades nesse arquivo, e adicionando a dependência de driver do banco desejado.


## Documentação

Para acessar a documentação da api, basta rodar o projeto no seu endereço local no caminho `/swagger-ui.html`


## Autores

- [@olivia-tiemi](https://github.com/olivia-tiemi)
- [@OctavioJunior](https://github.com/OctavioJunior)
- [@patricianogueira](https://github.com/patricianogueira)
- [@Rian-Aquino](https://github.com/Rian-Aquino)

