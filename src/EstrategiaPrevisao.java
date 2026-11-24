import java.util.List;


public abstract class EstrategiaPrevisao {
    protected String nomeEstrategia;
    protected String tipoUnidade;

    public EstrategiaPrevisao(String nomeEstrategia, String tipoUnidade) {
        this.nomeEstrategia = nomeEstrategia;
        this.tipoUnidade = tipoUnidade;
    }


    public void exibirContextoExecucao() {
        System.out.println("\n[ALGORITMO PREDITIVO] A executar: " + nomeEstrategia + " (" + tipoUnidade + ")");
    }


    public abstract double prever(List<Double> historicoLeituras);

    public String getNomeEstrategia() {
        return nomeEstrategia;
    }
}
