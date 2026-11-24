import java.util.List;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {
    private int tamanhoJanela;

    public PrevisaoMediaMovel(int tamanhoJanela, String tipoUnidade) {
        super("Previsão por Média Móvel", tipoUnidade);
        this.tamanhoJanela = Math.max(1, tamanhoJanela);
    }

    @Override
    public double prever(List<Double> historicoLeituras) {
        super.exibirContextoExecucao();

        if (historicoLeituras == null || historicoLeituras.isEmpty()) {
            System.out.println("⚠️ Histórico de leituras insuficiente para calcular a média móvel.");
            return 0.0;
        }

        int totalElementos = historicoLeituras.size();
        int inicio = Math.max(0, totalElementos - tamanhoJanela);

        double soma = 0.0;
        int contador = 0;

        for (int i = inicio; i < totalElementos; i++) {
            soma += historicoLeituras.get(i);
            contador++;
        }

        double media = soma / contador;
        System.out.println("  -> Janela considerada: últimos " + contador + " registos.");
        return media;
    }

    public int getTamanhoJanela() { return tamanhoJanela; }
}