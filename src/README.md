# Territory Predictive Insights — Calor & Sobrecarga na Rede Elétrica

## 📌 Contexto do Projeto
O **territory-predictive-insights** é um sistema desenvolvido em Java focado no monitoramento de **ilhas de calor urbanas** e **subestações de energia**. Durante ondas de calor extremo, o uso simultâneo e intensivo de aparelhos de ar-condicionado e sistemas de refrigeração gera picos de demanda que podem sobrecarregar a infraestrutura elétrica. 

O sistema coleta dados de telemetria em tempo real, executa modelos estatísticos de previsão preditiva e emite alertas visuais para auxiliar os gestores na prevenção de blecautes e na gestão de riscos urbanos.

---

## 🏗️ Estrutura do Projeto
A arquitetura do projeto segue os princípios de Orientação a Objetos (POO), garantindo alto grau de encapsulamento, reutilização por herança e polimorfismo via interfaces e classes abstratas.

```text
territory-predictive-insights/
├── ComponenteTerritorial.java    (Classe Abstrata)
├── EstacaoDeColeta.java          (Herda de ComponenteTerritorial)
├── PainelInformativo.java        (Herda de ComponenteTerritorial)
├── Sensor.java                   (Interface)
├── SensorTemperatura.java        (Implementa Sensor)
├── SensorConsumoEnergia.java     (Implementa Sensor)
├── SensorUmidade.java            (Implementa Sensor)
├── DadosTerritorio.java          (Classe com Encapsulamento e Validação)
├── EstrategiaPrevisao.java       (Classe Abstrata)
├── PrevisaoMediaMovel.java       (Herda de EstrategiaPrevisao)
├── PrevisaoComparacao.java       (Herda de EstrategiaPrevisao)
├── SistemaMonitoramento.java     (Classe Principal com Menu Interativo)
└── README.md                     (Documentação e Reflexão Crítica)
```

---

## 🛠️ Tecnologias e Conceitos Aplicados

1. **Classe Abstrata (`ComponenteTerritorial`, `EstrategiaPrevisao`)**: Define atributos e comportamentos fundamentais reutilizáveis, garantindo que subclasses implementem obrigatoriamente métodos concretos e abstratos.
2. **Interface (`Sensor`)**: Estabelece o contrato básico para múltiplos tipos de sensores (`Temperatura`, `ConsumoEnergia`, `Umidade`).
3. **Encapsulamento (`DadosTerritorio`)**: Todos os atributos são privados. Alterações em limiares críticos exigem um código de acesso administrativo (`ADMIN2026`) e passam por validações rígidas.
4. **Herança e Polimorfismo (`PrevisaoMediaMovel`, `PrevisaoComparacao`)**: Diferentes estratégias preditivas implementam o método `prever()` com lógicas distintas, utilizando `super` para reaproveitamento de código.
5. **Coleções (`ArrayList`)**: Armazenamento dinâmico do histórico de leituras de telemetria.

---

## 🧠 Reflexão Crítica

### 1. Quando o sistema deve se calar?
O sistema preditivo deve abster-se de gerar alertas automáticos de emergência ou recomendar ações severas (como o alívio preventivo de carga elétrica) quando a amostra de leituras for reduzida (por exemplo, menos de 5 registos no `ArrayList`). Modelos baseados em pouca amostragem sofrem de alta volatilidade estatística e ruídos transientes. Nesses cenários, o sistema deve registrar a restrição e exibir o status **`ATENÇÃO - DADOS INSUFICIENTES`**, prevenindo alarmes falsos, custos operacionais desnecessários e pânico na população.

### 2. O que os dados não representam?
A instalação de sensores em pontos específicos de uma cidade ou subestação fornece apenas uma visão agregada ou regional. **Os dados não representam microclimas urbanos hiper-localizados**, como áreas densamente construídas com pouca vegetação (onde o efeito de ilha de calor é muito superior à média regional). Da mesma forma, uma medição agregada de consumo elétrico (em MW) em uma subestação não reflete a vulnerabilidade individual de transformadores locais em bairros específicos. A telemetria quantitativa também oculta fatores socioeconômicos e a capacidade de adaptação térmica de diferentes grupos de moradores.

### 3. Por que o alerta apoia a decisão da pessoa, em vez de decidir por ela?
O sistema foi concebido como um **Sistema de Apoio à Decisão (DSS)**. Modelos estatísticos preditivos não conseguem prever eventos atípicos ou imprevisíveis, tais como quedas de árvores sobre linhas de transmissão, manobras emergenciais na rede nacional de energia ou decisões governamentais de emergência. Se o sistema tomasse decisões autônomas (como efetuar o desligamento automático de redes), poderia interromper o fornecimento de energia em serviços essenciais como hospitais e transporte público. O alerta fornece diagnósticos claros e fundamentados, permitindo que os engenheiros e gestores públicos tomem a decisão final com responsabilidade ética e operacional.