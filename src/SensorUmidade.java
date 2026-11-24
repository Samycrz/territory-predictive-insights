public class SensorUmidade implements Sensor {
    private final String id;
    private boolean operacional;

    public SensorUmidade(String id) {
        this.id = id;
        this.operacional = true;
    }

    @Override
    public double lerValor() {
        if (!operacional) {
            return 0.0;
        }
        // Simulação de humidade relativa durante episódios de calor seco (entre 12% e 65%)
        return 12.0 + (Math.random() * 53.0);
    }

    @Override
    public boolean verificarFuncionamento() {
        return operacional;
    }

    @Override
    public String retornarTipo() {
        return "Humidade Relativa do Ar (%)";
    }

    @Override
    public String getId() {
        return id;
    }

    public void setOperacional(boolean operacional) {
        this.operacional = operacional;
    }
}