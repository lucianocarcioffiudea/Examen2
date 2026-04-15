package modelos;

public abstract class Envio {

    private String cliente;
    private String codigo;
    private double peso;
    private double distancia;

    public Envio(String cliente, String codigo, double peso, double distancia) {
        this.cliente = cliente;
        this.codigo = codigo;
        this.peso = peso;
        this.distancia = distancia;
    }

    public String[] getDatos() {
        return new String[] {
                this.getClass().getSimpleName(),
                this.getTitular(),
                this.getCodigo(),
                String.valueOf(this.getPeso()),
                String.valueOf(this.getDistancia()),
                String.valueOf(this.calcularTarifa())
        };
    }

    public String getTitular() {
        return cliente;
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

    public abstract double calcularTarifa();
}
