public class SensorTemperatura implements Sensor {
    private final String id;
    private boolean operacional;

    public SensorTemperatura(String id) {
        this.id = id;
        this.operacional = true;
    }

    @Override
    public double lerValor() {
        if (!operacional) {
            return 0.0;
        }
        // Simulação realista de temperatura urbana durante onda de calor (entre 26°C e 44°C)
        return 26.0 + (Math.random() * 18.0);
    }

    @Override
    public boolean verificarFuncionamento() {
        return operacional;
    }

    @Override
    public String retornarTipo() {
        return "Temperatura Ilha de Calor (°C)";
    }

    @Override
    public String getId() {
        return id;
    }

    public void setOperacional(boolean operacional) {
        this.operacional = operacional;
    }
}
