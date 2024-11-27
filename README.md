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
   - Adotamos **BDD (Behavior-Driven Development)** para melhorar a comunicação entre equipe e stakeholders, descrevendo cenários em linguagem acessível.

4. **Testes Não Funcionais**  
   - Foram realizados **testes de carga e desempenho** para garantir que o sistema suporte altas demandas de usuários simultâneos e volume de reservas sem comprometer a eficiência.

5. **Cobertura de Testes**  
   - Monitoramos e mantivemos uma **alta cobertura de testes** utilizando ferramentas de **coverage**, assegurando que as principais funcionalidades estão devidamente protegidas contra falhas.

## 🌐 Deploy

Tipos de deploy escolhidos para o sistema:  
- Local  
- AWS ECS  
- Heroku

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

src/main/java/com/yourproject/
├── domain/                  // Core/Domain Layer
│   ├── entity/              // Entidades do sistema
│   ├── exception/           // Exceções relacionadas ao domínio
│
├── application/             // Application Layer
│   ├── usecase/             // Casos de uso da aplicação
│   └── gateway/             // Interfaces (ports) para comunicação entre o domínio e os adaptadores
│
├── adapters/                // Adapters Layer
│   ├── controller/          // Controladores (API, interfaces de usuário)
│   ├── gateway/             // Implementações concretas para atender às portas definidas na camada de aplicação.
│
├── infra/                   // Frameworks & Drivers Layer
│   ├── config/              // Configurações de frameworks, injeção de dependência, etc.
│   ├── database/            // Implementações específicas de banco de dados
│   ├── repository/          // Repositórios concretos para persistência de dados.
│   └── util/                // Classes utilitárias
