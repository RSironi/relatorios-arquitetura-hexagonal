# Relatórios Arquitetura Hexagonal

Exemplo de aplicação para geração de relatórios utilizando arquitetura modular de fácil expansão.
Com sistema de mensageria **Amazon SQS** para processamento assíncrono, incluindo suporte a **Dead Letter Queue (DLQ)** e fácil de iniciar via docker-compose.
## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.1**
- **AWS SDK**: Para integração com o serviço SQS (simulado pelo ElasticMQ).
- **ElasticMQ**: Simulador de filas SQS para testes locais.
- **Docker**: Para containerização e fácil execução do ambiente.

## Vantagens do Projeto

### 1. ** Facilidade de Implementação de Novos Relatórios**
Principal foco, a arquitetura permite adicionar novos relatórios de forma simples. Basta criar uma nova implementação de `RelatorioUseCaseI` e registrá-la no contexto da aplicação.

### 2. **Processo Bem Definido**
O fluxo de geração de relatórios é claro e bem estruturado, desde o envio da mensagem até a finalização do processo.

### 3. **Facilidade Para Futuras Expansões**
Seja para criar persistência em banco de dados, salvar o arquivo de relatório gerado em storage ou até mesmo integrar com outros serviços, essas implementações podem ser feitas com mudanças mínimas na estrutura atual.

---
### Rodando o Projeto

De forma prática, basta executar o comando e irá iniciar o ElasticMQ já configurado pelo arquivo `elasticmq.conf` seguido do aplicativo Spring Boot.
```bash
docker-compose up --build
```

### Acompanhando os Logs

Para visualizar os logs do container da aplicação Spring Boot:
```bash
docker logs -f relatorios-arquitetura-hexagonal-app-1
```

Para visualizar os logs do ElasticMQ:
```bash
docker logs -f elasticmq
```

### Enviando Mensagens para a Fila

#### Via Postman
1. Configure uma requisição `POST` para o endpoint da fila:
   ```
   http://localhost:9324/000000000000/relatorio-fila
   ```
2. No body, selecione o tipo `x-www-form-urlencoded` e adicione os seguintes campos:
   - **Key**: `Action`  
     **Value**: `SendMessage`
   - **Key**: `MessageBody`  
     **Value**: `{"codUsuario":123,"codFuncionalidade":1,"filtro":"filtro-exemplo"}`
3. Envie a requisição.

#### Via AWS CLI
```bash
aws sqs send-message \
  --endpoint-url http://localhost:9324 \
  --queue-url http://localhost:9324/000000000000/relatorio-fila \
  --message-body '{"codUsuario":123,"codFuncionalidade":1,"filtro":"filtro-exemplo"}'
```

---
### Como Funciona o Processo de Geração de Relatórios

1. **Envio de Mensagem para a Fila**:
    - O processo começa com o envio de uma mensagem para a fila `relatorio-fila`.
    - A mensagem contém informações como `codUsuario`, `codFuncionalidade` (1 ou 2) e um `filtro`.

2. **Recepção da Mensagem**:
    - A aplicação Spring Boot consome a mensagem da fila utilizando um listener configurado.

3. **Criação do ProcessoRelatorio**:
    - A mensagem é transformada em um objeto `ProcessoRelatorio`.
    - Um código único de 2 dígitos é gerado para identificar o processo.

4. **Execução do Relatório**:
    - Com base no `codFuncionalidade`, o caso de uso correspondente é chamado para processar o relatório.
    - Durante o processamento, logs são gerados para acompanhar o início e o fim do processo.

5. **Finalização**:
    - Após o processamento, o status do relatório é atualizado para `PROCESSADO` ou `ERRO` em caso de falha.
    - O tempo de início e fim do processamento também é registrado.
---

Este projeto demonstra como uma arquitetura bem planejada pode facilitar o desenvolvimento e a manutenção de sistemas complexos. Sinta-se à vontade para explorar e contribuir!
