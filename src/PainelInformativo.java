public class PainelInformativo extends ComponenteTerritorial {
    private String tipoExibicao; // Ex: "LED Urbano", "Sirene Operacional", "Alerta Móvel"
    private String mensagemAtual;

    public PainelInformativo(String id, String nome, String regiao, String tipoExibicao) {
        super(id, nome, regiao);
        this.tipoExibicao = tipoExibicao;
        this.mensagemAtual = "Condições Normais de Temperatura e Rede Elétrica.";
    }

    public void atualizarMensagem(String novaMensagem) {
        if (novaMensagem != null && !novaMensagem.trim().isEmpty()) {
            this.mensagemAtual = novaMensagem;
            System.out.println("[PAINEL " + id + "] Mensagem atualizada: \"" + novaMensagem + "\"");
        }
    }

    @Override
    public void verificarStatus() {
        String estado = status ? "ATIVO (EXIBINDO)" : "SILENCIADO / INATIVO";
        System.out.println(" PAINEL INFORMATIVO TERRITORIAL                         ");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println(" ID: " + id);
        System.out.println(" Nome: " + nome);
        System.out.println(" Região: " + regiao);
        System.out.println(" Tecnologia: " + tipoExibicao);
        System.out.println(" Estado: " + estado);
        System.out.println(" Exibição Atual: " + mensagemAtual);
        System.out.println("────────────────────────────────────────────────────────");
    }
}