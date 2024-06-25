# Rodando o Projeto Java (Spring Boot)

## Configuração do Projeto:

### Spring Boot Configuration

Verifique se o projeto está configurado corretamente com as dependências necessárias, como Spring Web, Spring Data JPA (se estiver usando banco de dados), e H2 Database (no caso de um banco de dados em memória).

### Configuração de CORS

Certifique-se de que a classe WebConfig está corretamente configurada para permitir requisições CORS (Cross-Origin Resource Sharing). O código que você forneceu parece correto para permitir o acesso do Angular (executando em http://localhost:4200) ao backend Spring Boot (executando em http://localhost:8080).

## Executando o Projeto:

### Via IDE

1. Abra o projeto na sua IDE (como IntelliJ IDEA ou Eclipse).
2. Certifique-se de que o ambiente de desenvolvimento está configurado corretamente para o Spring Boot.
3. Execute a classe principal do aplicativo Spring Boot (geralmente uma classe com a anotação @SpringBootApplication).

### Via Linha de Comando

1. Navegue até o diretório raiz do seu projeto Spring Boot que contém o arquivo pom.xml.
2. Execute o comando Maven para construir e iniciar o aplicativo: `mvn spring-boot:run`.

## Verificação do Status:

Após iniciar o aplicativo, verifique o console para mensagens de inicialização sem erros.
O Spring Boot geralmente inicia em http://localhost:8080.

## Acesso ao Banco de Dados H2:

Se estiver usando o banco de dados H2 em memória, você pode acessar o console do H2 para verificar os dados.
Acesse [http://localhost:8080/h2-console](http://localhost:8080/h2-console) no navegador.
Use as configurações padrão ou as que você especificou no `application.properties` para se conectar ao banco de dados.

# Rodando o Projeto Angular

## Configuração do Projeto:

### Ambiente de Desenvolvimento

Verifique se o Node.js e o Angular CLI estão instalados globalmente na sua máquina.

- Instale o Node.js (se ainda não estiver instalado): [Node.js](https://nodejs.org/).
- Instale o Angular CLI globalmente via npm: `npm install -g @angular/cli`.

### Configuração do Ambiente

Verifique se o arquivo `environment.ts` está configurado corretamente para se comunicar com o backend Spring Boot.
O `baseUrl` e `api` devem apontar para `http://localhost:8080` se o Spring Boot estiver sendo executado localmente.

## Executando o Projeto:

### Navegação

Abra um terminal e navegue até o diretório raiz do projeto Angular (onde está localizado o arquivo `angular.json`).

### Inicialização

Execute o comando para iniciar o servidor de desenvolvimento do Angular: `ng serve`.
Adicione a opção `--open` se desejar que o navegador seja aberto automaticamente após a compilação.

## Verificação do Status:

Após a inicialização, verifique o console para mensagens de compilação sem erros.
O Angular normalmente inicia em [http://localhost:4200](http://localhost:4200).
