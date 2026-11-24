public abstract class ComponenteTerritorial {
    protected String id;
    protected String nome;
    protected String regiao;
    protected boolean status;

    public ComponenteTerritorial(String id, String nome, String regiao) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("O ID do componente não pode ser nulo ou vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do componente não pode ser nulo ou vazio.");
        }
        if (regiao == null || regiao.trim().isEmpty()) {
            throw new IllegalArgumentException("A região do componente não pode ser nula ou vazia.");
        }
        this.id = id;
        this.nome = nome;
        this.regiao = regiao;
        this.status = true;
    }

    public void ativar() {
        this.status = true;
        System.out.println("[SISTEMA] Componente '" + nome + "' (ID: " + id + ") foi ATIVADO.");
    }

    public void desativar() {
        this.status = false;
        System.out.println("[SISTEMA] Componente '" + nome + "' (ID: " + id + ") foi DESATIVADO.");
    }

    public abstract void verificarStatus();

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getRegiao() { return regiao; }
    public boolean isAtivo() { return status; }
}
