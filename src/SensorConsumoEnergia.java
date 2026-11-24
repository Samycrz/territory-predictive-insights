public class SensorConsumoEnergia implements Sensor {
    private final String id;
    private boolean operacional;

    public SensorConsumoEnergia(String id) {
        this.id = id;
        this.operacional = true;
    }

    @Override
    public double lerValor() {
        if (!operacional) {
            return 0.0;
        }
        return 40.0 + (Math.random() * 100.0);
    }

    @Override
    public boolean verificarFuncionamento() {
        return operacional;
    }

    @Override
    public String retornarTipo() {
        return "Consumo Subestação Elétrica (MW)";
    }

    @Override
    public String getId() {
        return id;
    }

    public void setOperacional(boolean operacional) {
        this.operacional = operacional;
    }
}