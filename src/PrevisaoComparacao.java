import java.util.List;

public class PrevisaoComparacao extends EstrategiaPrevisao {
    private double fatorMultiplicadorSazonal;

    public PrevisaoComparacao(double fatorMultiplicadorSazonal, String tipoUnidade) {
        super("Previsão por Comparação de Períodos e Sazonalidade", tipoUnidade);
        this.fatorMultiplicadorSazonal = Math.max(0.5, fatorMultiplicadorSazonal);
    }

    @Override
    public double prever(List<Double> historicoLeituras) {
        super.exibirContextoExecucao();

        if (historicoLeituras == null || historicoLeituras.isEmpty()) {
            System.out.println(" Histórico insuficiente para efetuar comparação temporal.");
            return 0.0;
        }

        double valorAtual = historicoLeituras.get(historicoLeituras.size() - 1);
        double valorAnterior = (historicoLeituras.size() > 1) ? historicoLeituras.get(historicoLeituras.size() - 2) : valorAtual;

        double variacaoPercentual = ((valorAtual - valorAnterior) / (valorAnterior == 0 ? 1 : valorAnterior)) * 100.0;
        double valorProjetado = valorAtual * fatorMultiplicadorSazonal;

        System.out.println("  -> Valor Período Anterior: " + String.format("%.2f", valorAnterior) + " " + tipoUnidade);
        System.out.println("  -> Valor Período Atual: " + String.format("%.2f", valorAtual) + " " + tipoUnidade + " (Variação: " + String.format("%.1f", variacaoPercentual) + "%)");
        System.out.println("  -> Fator Sazonal Aplicado: " + fatorMultiplicadorSazonal + "x");

        return valorProjetado;
    }
}