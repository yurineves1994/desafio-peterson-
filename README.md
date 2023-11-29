# TESTE TECNICO

## Descrição do Projeto

Trata-se de um sistema de cadastro de empresa, onde é possivel inserir endereço para cada empresa. Além disso, foi construido um microserviço de envio de e-mails utilizando o SMTP do gmail utilizando RabbitMQ.

O backend conta com autenticação utilizando JWT e Spring Security, Spring JPA, controle Logs utilizando Log4j2 e Docker, já o frontend foi construido com Angular 15, utilizando controle de rotas e permissões.

## Como executar o backend do projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/yurineves1994/api-authentication.git
2. **Navegue até o diretório do projeto:**

    ```bash
    cd desafio-peterson-

3. **Execute o docker compose**

    ```bash
    docker-compose up

- CASO NÃO TENHA DOCKER/DOCKER-COMPOSE SERÁ NECESSARIO INSTALAR
- CREDENCIAIS PRA ACESSAR FRONT-END (LOGIN: admin@admin.com / SENHA: 12345)


## Como executar o front do projeto

1. **Com o projeto já clonado, acesse a pasta interface-empresa**

    ```bash
    cd interface-empresa

2. **Instale as Dependencias**

    ```bash
    npm i
    
3. **Execute o Angular**

    ```bash
    ng serve

para visualização a documentação da API http://localhost:8080/swagger-ui/index.html#/

## ROTAS DO SISTEMA

### ROTAS NÃO AUTENTICADAS

- CADASTRO DE NOVO USUARIO (POST)
http://localhost:8080/auth/register

- LOGIN (POST)
http://localhost:8080/auth/login

- LISTAR TODOS USUARIOS (GET)
http://localhost:8080/auth/register


### ROTAS AUTENTICADAS AUTENTICADAS (LOGIN: admin@admin.com / SENHA: 12345)

- LISTAR ENDEREÇOS (GET)
http://localhost:8080/api/enderecos

- LISTAR EMPRESAS (GET)
http://localhost:8080/api/empresas

- ENVIO CADASTRO DE PERGUNTA (POST)
http://localhost:8080/api/faleconosco/$ID

- CADASTRO DE EMPRESA (POST)
http://localhost:8080/api/empresas

- CADASTRO DE ENDEREÇO POR ID (POST)
http://localhost:8080/api/enderecos/$ID
