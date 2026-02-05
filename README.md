# 🍴 Tech Challenge: Sistema de Reserva e Avaliação de Restaurantes

O **Tech Challenge** é um projeto integrador organizado pela FIAP que abrange os conhecimentos adquiridos em todas as disciplinas da fase. 
O objetivo do projeto é a criação de um sistema completo de **Reserva e Avaliação de Restaurantes**, utilizando práticas de **Clean Architecture**, **Clean Code** e **Qualidade de Software**.

## 📝 Funcionalidades Básicas

1. **Cadastro de Restaurantes**  

2. **Reserva de Mesas**  

3. **Avaliações e Comentários**  

4. **Busca de Restaurantes**  

5. **Gerenciamento de Reservas**  

## 🎯 O que foi aplicado neste projeto

### 🛠️ Clean Architecture

1. **Design Arquitetônico**  
   - O backend foi estruturado com base nos princípios da **Clean Architecture**, garantindo:  
     - Separação clara entre as **entidades** e as regras de negócio.  
     - Implementação de **adaptadores de interface** para comunicação entre camadas.  
     - Utilização de frameworks e ferramentas de forma desacoplada, promovendo maior flexibilidade e manutenção.

2. **Clean Code em Testes**  
   - Todo o código foi desenvolvido seguindo práticas de **Clean Code**, resultando em soluções legíveis, organizadas e consistentes, com atenção especial à qualidade dos testes.

---

### 🧪 Qualidade de Software

1. **Testes Unitários (TDD)**  
   - O desenvolvimento seguiu o modelo de **Test-Driven Development (TDD)** utilizando ferramentas como **JUnit**, garantindo cobertura total para as funcionalidades críticas do sistema.

2. **Testes Integrados e Inspeção de Código**  
   - Foram realizados **testes integrados** para validar a interação entre as diferentes partes do sistema.  
   - Utilizamos ferramentas de inspeção de código, como **SonarQube**, para identificar e corrigir eventuais problemas, assegurando alta qualidade do código.

3. **Testes de Integração no Controller, CI e BDD**  
   - Os **controllers** foram amplamente testados para validar a lógica de negócios.  
   - Implementamos um pipeline de **CI/CD**, onde os testes são automaticamente executados em cada alteração do código.  
   - Adotamos **BDD (Behavior-Driven Development)** utilizando o Cucumber para melhorar a comunicação entre equipe e stakeholders, descrevendo cenários em linguagem acessível.

4. **Testes Não Funcionais**  
   - Foram realizados **testes de carga e desempenho** para garantir que o sistema suporte altas demandas de usuários simultâneos e volume de reservas sem comprometer a eficiência.

5. **Cobertura de Testes**  
   - Monitoramos e mantivemos uma **alta cobertura de testes** utilizando ferramentas de **coverage**, assegurando que as principais funcionalidades estão devidamente protegidas contra falhas.

## 🌐 Deploy

Tipos de deploy escolhidos para o sistema:  
- Local  
- Azure

## 📊 Gerar Relatório de testes pelo Jacoco:

1. No terminal, execute:  
   ```bash
   mvn clean test

2. Depois execute:  
   ```bash
   mvn jacoco:report

3. Depois acesse o diretório e abra o arquivo em seu navegador:
   `target/site/jacoco/index.html`
   
## 📂 Estrutura do projeto:
```
src/main/java/fiap/restaurant_manager/
├── adapters                // Adapters Layer
│   ├── api                 // Endpoint REST (adaptador para o mundo externo)
│   └── persistence         // Implementações dos repositórios (JPA, MongoDB, etc.)
│
├── application             // Application Layer
│   ├── usecases            // Casos de uso da aplicação (services)
│   └── gateways            // Interfaces (ports) para comunicação entre o domínio e os adaptadores
│
├── domain                  // Core/Domain Layer
│   ├── entities            // Entidades do sistema
│   └── exception           // Exceções relacionadas ao domínio
│   └── enums               // Enumeartions do projeto
│
├── infrastructure          // Frameworks & Drivers Layer
│   ├── settings            // Configurações dos Beans, injeção de dependência, etc.
│   └── util                // Classes utilitárias (compartilhadas)
│   	└── mappers           // Faz as conversões de DTO's, Entidades de banco e Entidades de domínios

```

### ▶️ Como Rodar o Projeto

1. Baixe as dependências do projeto: 
 ```bash
   mvn clean install
```

2. **Certifique-se de que o Docker está instalado em sua máquina.**
   - Caso não tenha o Docker, [baixe e instale-o](https://www.docker.com/get-started).

3. **Suba os containers com o Docker Compose:**

```bash
docker-compose up -d
```
Configure os dados corretos do banco no arquivo application.properties, após a execução, a aplicação estará disponível em http://localhost:8080

A documentação da API está disponível no Swagger, que pode ser acessada em: 

Local:

http://localhost:8080/swagger-ui/index.html

Link da aplicação na Azure:

https://restaurant-manager-app-c5f9bha8c8gahjc8.brazilsouth-01.azurewebsites.net/swagger-ui/index.html

Link da aplicação na Render:

https://rm-render-ga-release.onrender.com/swagger-ui/index.html

Obs.: O pacote de serviço Free do Render desativa o Web Service por inatividade. Portanto, ao tentar acessar o link acima, após a primeira tentativa, aguardar alguns minutos para que a aplicação suba novamente.
