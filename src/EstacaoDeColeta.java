public class EstacaoDeColeta extends ComponenteTerritorial {
    private int quantidadeSensoresConectados;
    private double capacidadeCargaSubestacaoKW;

    public EstacaoDeColeta(String id, String nome, String regiao, int quantidadeSensoresConectados, double capacidadeCargaSubestacaoKW) {
        super(id, nome, regiao);
        this.quantidadeSensoresConectados = Math.max(0, quantidadeSensoresConectados);
        this.capacidadeCargaSubestacaoKW = Math.max(0.0, capacidadeCargaSubestacaoKW);
    }

    @Override
    public void verificarStatus() {
        String estado = status ? "OPERACIONAL (ONLINE)" : "FORA DE SERVIÇO (OFFLINE)";
        System.out.println(" ESTAÇÃO DE COLETA E SUBESTAÇÃO                         ");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println(" ID: " + id);
        System.out.println(" Nome: " + nome);
        System.out.println(" Região: " + regiao);
        System.out.println(" Estado: " + estado);
        System.out.println(" Sensores Conectados: " + quantidadeSensoresConectados);
        System.out.println(" Capacidade Nominal: " + capacidadeCargaSubestacaoKW + " kW");
        System.out.println("────────────────────────────────────────────────────────");
    }

    public int getQuantidadeSensoresConectados() { return quantidadeSensoresConectados; }
    public double getCapacidadeCargaSubestacaoKW() { return capacidadeCargaSubestacaoKW; }
}
