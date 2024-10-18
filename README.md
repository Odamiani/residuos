# API Java - Urban Clean

Aplicação API para projeto StartUp teste

## Pré-requisitos

- Java 17
- Git
- Docker

##  Execução

```sh
mvn spring-boot:run
```



## Documentação para testar End-Points

Gravar Usuario  S/ AUTH(POST): 
http://localhost:8080/auth/register

Gravar Usuario(POST):
http://localhost:8080/api/usuarios

Efetuar login(POST):
http://localhost:8080/auth/login

Atualizar usuario(PUT):
http://localhost:8080/api/usuarios

Listar todos os usuarios(GET):
http://localhost:8080/api/usuarios

Consultar pelo id(GET):
http://localhost:8080/api/usuarios/{id}

deletar contato por id(DELETE):
http://localhost:8080/api/usuarios/{id}

