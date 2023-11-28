# TESTE TECNICO

## Descrição do Projeto

Trata-se de um sistema de cadastro de empresa, onde é possivel inserir endereço para cada empresa. Além disso, foi construido um microserviço de envio de e-mails utilizando o SMTP do gmail.

O backend conta com autenticação utilizando JWT e Spring Security, Spring JPA, controle Logs utilizando Log4j2 e Docker, já o frontend foi construido com Angular 15, utilizando controle de rotas e permissões.

para visualização a documentação da API http://localhost:8080/swagger-ui/index.html#/

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


## Como executar o front do projeto

1. **Com o projeto já clonado, acesse a pasta interface-empresa**

    ```bash
    cd interface-empresa
    
2. **Execute o Angular**

    ```bash
    ng serve
