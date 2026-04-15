package modelos;

public class Envio {
    private String cliente;
    private TipoDeEnvio tipo;
    private String codigo;
    private double peso;
    private double distancia;
    private double costo;
    public Envio(String cliente, String codigo){
        this.cliente = cliente;
        this.codigo = codigo;
        this.costo = 0;
    }
    public String getTitular() {
        return cliente;
    }

    public TipoDeEnvio getTipo() {
        return tipo;
    }
    public String getCodigo() {
        return codigo;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistancia() {
        return distancia;
    }
    public double getCosto() {
        return costo;
    }
    protected void setCosto(double costo) {
        this.costo = costo;
    }
}
