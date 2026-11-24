public class DadosTerritorio {
    private String regiao;
    private int populacao;
    private double limiteTemperaturaAlerta;
    private double limiteConsumoAlerta;
    private final String codigoAcessoSeguro;

    public DadosTerritorio(String regiao, int populacao, double limiteTemperaturaAlerta, double limiteConsumoAlerta, String codigoAcessoInicial) {
        if (codigoAcessoInicial == null || codigoAcessoInicial.trim().isEmpty()) {
            throw new IllegalArgumentException("O código de acesso de segurança não pode ser vazio.");
        }
        this.codigoAcessoSeguro = codigoAcessoInicial;

        // Aplicação de validações na inicialização
        if (!setRegiaoValidado(regiao)) {
            this.regiao = "Região Não Definida";
        }
        if (!setPopulacaoValidado(populacao)) {
            this.populacao = 1000;
        }
        this.limiteTemperaturaAlerta = Math.max(0.0, limiteTemperaturaAlerta);
        this.limiteConsumoAlerta = Math.max(0.0, limiteConsumoAlerta);
    }

    public String getRegiao() {
        return regiao;
    }

    public boolean setRegiao(String novaRegiao) {
        return setRegiaoValidado(novaRegiao);
    }

    private boolean setRegiaoValidado(String novaRegiao) {
        if (novaRegiao != null && !novaRegiao.trim().isEmpty()) {
            this.regiao = novaRegiao;
            return true;
        }
        System.out.println("❌ [ERRO DE VALIDAÇÃO] A região fornecida é inválida. O valor anterior foi mantido: '" + this.regiao + "'");
        return false;
    }

    public int getPopulacao() {
        return populacao;
    }

    public boolean setPopulacao(int novaPopulacao) {
        return setPopulacaoValidado(novaPopulacao);
    }

    private boolean setPopulacaoValidado(int novaPopulacao) {
        if (novaPopulacao > 0) {
            this.populacao = novaPopulacao;
            return true;
        }
        System.out.println("❌ [ERRO DE VALIDAÇÃO] População deve ser maior que zero (recebido: " + novaPopulacao + "). O valor anterior foi mantido: " + this.populacao);
        return false;
    }

    public double getLimiteTemperaturaAlerta() {
        return limiteTemperaturaAlerta;
    }

    public double getLimiteConsumoAlerta() {
        return limiteConsumoAlerta;
    }



    public boolean alterarLimiaresProtegidos(String codigoAcesso, double novaTempLimite, double novoConsumoLimite) {
        if (!this.codigoAcessoSeguro.equals(codigoAcesso)) {
            System.out.println("🔒 [ACESSO NEGADO] Código de acesso incorreto! Os limiares de segurança permanecem inalterados.");
            return false;
        }

        if (novaTempLimite <= 0 || novaTempLimite > 60.0) {
            System.out.println("❌ [ERRO DE VALIDAÇÃO] Limiar de temperatura fora da faixa operacional segura (0°C a 60°C). Rejeitado.");
            return false;
        }

        if (novoConsumoLimite <= 0 || novoConsumoLimite > 1000.0) {
            System.out.println("❌ [ERRO DE VALIDAÇÃO] Limiar de consumo fora da faixa operacional segura (0 a 1000 MW). Rejeitado.");
            return false;
        }

        this.limiteTemperaturaAlerta = novaTempLimite;
        this.limiteConsumoAlerta = novoConsumoLimite;
        System.out.println("✅ [SUCESSO] Limiares alterados com sucesso! Nova Temp: " + novaTempLimite + "°C | Novo Consumo: " + novoConsumoLimite + " MW");
        return true;
    }
}