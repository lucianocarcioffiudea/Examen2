package modelos;

public class Envio {
    private String cliente;
    private TipoDeEnvio tipo;
    private String numero;
    private double peso;
    private double distancia;
    private double costo;
    public Envio(String cliente, String numero){
        this.cliente = cliente;
        this.numero = numero;
        this.costo = 0;
    }
    public String getTitular() {
        return cliente;
    }

    public TipoDeEnvio getTipo() {
        return tipo;
    }
    public String getNumero() {
        return numero;
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
