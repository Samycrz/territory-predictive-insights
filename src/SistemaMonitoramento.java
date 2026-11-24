import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SistemaMonitoramento {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciação do Território com Limiares de Alerta Protegidos
        DadosTerritorio territorio = new DadosTerritorio(
                "Região Metropolitana - Zona Sul / Centro Elétrico",
                1850000,
                37.5,   // Limiar Temperatura Alerta (°C)
                115.0,  // Limiar Consumo Elétrico Alerta (MW)
                "ADMIN2026" // Código de acesso administrativo
        );

        // Componentes Territoriais
        List<ComponenteTerritorial> componentes = new ArrayList<>();
        componentes.add(new EstacaoDeColeta("EST-SP-01", "Subestação Principal Berrini", "Zona Sul", 12, 150000.0));
        componentes.add(new PainelInformativo("PNL-SP-01", "Painel Digital Marginal Pinheiros", "Zona Sul", "Painel LED de Alto Impacto"));

        // Sensores
        List<Sensor> sensores = new ArrayList<>();
        sensores.add(new SensorTemperatura("TEMP-01"));
        sensores.add(new SensorConsumoEnergia("ENERG-01"));
        sensores.add(new SensorUmidade("UMID-01"));

        // Histórico de Leituras mantido em ArrayList (Conforme Requisito)
        List<Double> historicoTemperatura = new ArrayList<>();
        List<Double> historicoConsumo = new ArrayList<>();

        // Leitura de amostras iniciais
        historicoTemperatura.add(33.5);
        historicoTemperatura.add(35.0);
        historicoTemperatura.add(36.8);
        historicoTemperatura.add(38.2);

        historicoConsumo.add(85.0);
        historicoConsumo.add(98.4);
        historicoConsumo.add(110.2);
        historicoConsumo.add(118.6);

        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            System.out.print("Selecione uma opção do menu: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Opção inválida. Por favor, digite um número de 0 a 5.");
                scanner.nextLine();
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza do buffer

            switch (opcao) {
                case 1 -> executarVerificacaoSensores(sensores, territorio);
                case 2 -> executarSistemaPrevisaoEAlertas(historicoTemperatura, historicoConsumo, territorio);
                case 3 -> gerenciarDadosTerritorio(territorio, scanner);
                case 4 -> simularNovasLeituras(sensores, historicoTemperatura, historicoConsumo);
                case 5 -> exibirStatusCompleto(territorio, componentes, sensores, historicoTemperatura.size());
                case 0 -> System.out.println("\n👋 Encerrando o Sistema de Monitoramento de Calor e Energia. Até breve!");
                default -> System.out.println("❌ Opção não reconhecida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n==================================================================");
        System.out.println("  🌡️  SISTEMA DE MONITORAMENTO: ONDAS DE CALOR E REDE ELÉTRICA ⚡  ");
        System.out.println("==================================================================");
        System.out.println(" 1. Ler Sensores em Tempo Real e Detetar Excesso de Limiar");
        System.out.println(" 2. Rodar Modelos Preditivos e Processar Sistema de Alertas");
        System.out.println(" 3. Gerenciar Dados do Território (Limiares com Código de Acesso)");
        System.out.println(" 4. Simular Nova Vaga de Telemetria (Alimentar Histórico)");
        System.out.println(" 5. Exibir Diagnóstico Integrado e Status dos Componentes");
        System.out.println(" 0. Sair do Programa");
        System.out.println("==================================================================");
    }

    private static void executarVerificacaoSensores(List<Sensor> sensores, DadosTerritorio territorio) {
        System.out.println("\n--- [TELEMETRIA] LEITURA DE SENSORES INSTALADOS ---");
        for (Sensor sensor : sensores) {
            boolean func = sensor.verificarFuncionamento();
            double valor = sensor.lerValor();
            String tipo = sensor.retornarTipo();

            System.out.printf("🔹 Sensor ID: %s | Tipo: %-32s | Estado: %s | Leitura: %.2f%n",
                    sensor.getId(), tipo, (func ? "OK" : "FALHA"), valor);

            // Verificação imediata do valor lido vs limiares
            if (tipo.contains("Temperatura") && valor > territorio.getLimiteTemperaturaAlerta()) {
                System.out.printf("   🚨 [ALERTA DIRETO DE TEMPERATURA] Valor lido (%.2f°C) excedeu o limiar estipulado (%.2f°C)!%n",
                        valor, territorio.getLimiteTemperaturaAlerta());
            } else if (tipo.contains("Consumo") && valor > territorio.getLimiteConsumoAlerta()) {
                System.out.printf("   ⚠️ [ALERTA DIRETO DE SOBRECARGA] Consumo em subestação (%.2f MW) excedeu o limiar (%.2f MW)!%n",
                        valor, territorio.getLimiteConsumoAlerta());
            }
        }
    }

    private static void executarSistemaPrevisaoEAlertas(List<Double> histTemp, List<Double> histConsumo, DadosTerritorio territorio) {
        System.out.println("\n==================================================================");
        System.out.println(" 🔮 PROCESSAMENTO PREDITIVO E DIAGNÓSTICO DE RISCO DE APAGÃO ");
        System.out.println("==================================================================");

        // Instanciação das estratégias de previsão
        EstrategiaPrevisao mediaMovelTemp = new PrevisaoMediaMovel(3, "°C");
        EstrategiaPrevisao comparacaoConsumo = new PrevisaoComparacao(1.18, "MW"); // Projeção de +18% de pico de climatizadores

        double tempPrevista = mediaMovelTemp.prever(histTemp);
        double consumoPrevisto = comparacaoConsumo.prever(histConsumo);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println(" 📊 RESULTADOS DAS PROJEÇÕES ");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("• Temperatura Ilha de Calor Prevista: %.2f °C (Limiar Alerta: %.2f °C)%n", tempPrevista, territorio.getLimiteTemperaturaAlerta());
        System.out.printf("• Consumo na Subestação Previsto:   %.2f MW (Limiar Alerta: %.2f MW)%n", consumoPrevisto, territorio.getLimiteConsumoAlerta());

        // Motor de Alertas com Classificação por Níveis
        processarMotorDeAlertas("Temperatura Urbana", tempPrevista, territorio.getLimiteTemperaturaAlerta(), "°C", histTemp.size());
        processarMotorDeAlertas("Consumo Elétrico Subestação", consumoPrevisto, territorio.getLimiteConsumoAlerta(), "MW", histConsumo.size());
    }

    private static void processarMotorDeAlertas(String indicador, double valorPrevisto, double limiar, String unidade, int quantidadeAmostras) {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println(" 📢 AVALIAÇÃO DO MOTOR DE ALERTAS: " + indicador.toUpperCase());
        System.out.println("------------------------------------------------------------------");

        if (quantidadeAmostras < 5) {
            System.out.println("🟡 [NÍVEL: ATENÇÃO - DADOS INSUFICIENTES]");
            System.out.printf("   Motivo: O histórico possui apenas %d leituras. Previsões preditivas exigem pelo menos 5 amostras para alta confiabilidade.%n", quantidadeAmostras);
            return;
        }

        double percentualExcesso = ((valorPrevisto - limiar) / limiar) * 100.0;

        if (valorPrevisto > limiar * 1.15) {
            System.out.println("🔴 [NÍVEL: CRÍTICO]");
            System.out.printf("   Detalhes: Sensor de %s com previsão de %.2f %s ultrapassou o limiar de %.2f %s em %.1f%%.%n",
                    indicador, valorPrevisto, unidade, limiar, unidade, percentualExcesso);
            System.out.println("   Ação: Riscos iminentes de colapso por sobreaquecimento nos transformadores e blecaute regional.");
        } else if (valorPrevisto > limiar) {
            System.out.println("🟠 [NÍVEL: ALERTA]");
            System.out.printf("   Detalhes: Sensor de %s com previsão de %.2f %s superou o limite regulatório de %.2f %s.%n",
                    indicador, valorPrevisto, unidade, limiar, unidade);
            System.out.println("   Ação: Recomenda-se redistribuição de carga entre subestações e aviso de uso consciente de ar-condicionado.");
        } else {
            System.out.println("🟢 [NÍVEL: NORMAL / ESTÁVEL]");
            System.out.printf("   Detalhes: Previsão de %.2f %s dentro da margem de operação segura (abaixo de %.2f %s).%n",
                    valorPrevisto, unidade, limiar, unidade);
        }
    }

    private static void gerenciarDadosTerritorio(DadosTerritorio territorio, Scanner scanner) {
        System.out.println("\n--- [GESTAO] GERENCIAMENTO DE DADOS DO TERRITÓRIO ---");
        System.out.println("Região Registada: " + territorio.getRegiao());
        System.out.println("População Coberta: " + territorio.getPopulacao() + " habitantes");
        System.out.println("Limiar Alerta Temperatura: " + territorio.getLimiteTemperaturaAlerta() + " °C");
        System.out.println("Limiar Alerta Consumo:     " + territorio.getLimiteConsumoAlerta() + " MW");

        System.out.println("\nO que deseja alterar?");
        System.out.println("1. População ou Região");
        System.out.println("2. Limiares Críticos de Segurança (Requer Código de Acesso)");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");

        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            System.out.print("Nova População: ");
            int pop = scanner.nextInt();
            scanner.nextLine();
            territorio.setPopulacao(pop);

            System.out.print("Nova Descrição da Região: ");
            String reg = scanner.nextLine();
            territorio.setRegiao(reg);
        } else if (op == 2) {
            System.out.print("🔑 Digite o Código de Acesso Administrativo: ");
            String codigo = scanner.nextLine();
            System.out.print("Novo Limiar de Temperatura (°C): ");
            double t = scanner.nextDouble();
            System.out.print("Novo Limiar de Consumo (MW): ");
            double c = scanner.nextDouble();
            scanner.nextLine();

            territorio.alterarLimiaresProtegidos(codigo, t, c);
        }
    }

    private static void simularNovasLeituras(List<Sensor> sensores, List<Double> histTemp, List<Double> histConsumo) {
        System.out.println("\n--- [SIMULAÇÃO] GERANDO NOVAS LEITURAS DE TELEMETRIA ---");
        for (Sensor s : sensores) {
            double v = s.lerValor();
            if (s.retornarTipo().contains("Temperatura")) {
                histTemp.add(v);
                System.out.printf("-> Adicionado ao histórico de Temperatura: %.2f °C%n", v);
            } else if (s.retornarTipo().contains("Consumo")) {
                histConsumo.add(v);
                System.out.printf("-> Adicionado ao histórico de Consumo: %.2f MW%n", v);
            }
        }
        System.out.println("✅ Histórico atualizado. Total de leituras acumuladas: " + histTemp.size());
    }

    private static void exibirStatusCompleto(DadosTerritorio territorio, List<ComponenteTerritorial> componentes, List<Sensor> sensores, int totalLeituras) {
        System.out.println("\n==================================================================");
        System.out.println(" 🌐 RESUMO COMPLETO DO TERRITÓRIO E COMPONENTES OPERACIONAIS ");
        System.out.println("==================================================================");
        System.out.println("• Região Monitorada: " + territorio.getRegiao());
        System.out.println("• População Abrangida: " + territorio.getPopulacao() + " pessoas");
        System.out.println("• Total de Leituras no ArrayList: " + totalLeituras);

        System.out.println("\n--- COMPONENTES TERRITORIAIS ---");
        for (ComponenteTerritorial c : componentes) {
            c.verificarStatus();
        }

        System.out.println("\n--- SENSORES ATIVOS ---");
        for (Sensor s : sensores) {
            System.out.println("• ID: " + s.getId() + " | Tipo: " + s.retornarTipo() + " | Estado: " + (s.verificarFuncionamento() ? "Operacional" : "Inativo"));
        }
    }
}