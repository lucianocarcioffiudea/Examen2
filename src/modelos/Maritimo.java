package modelos;

public class Maritimo extends Envio {

    public Maritimo(String cliente, String Codigo, double peso, double distancia) {
        super(cliente, Codigo, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (1000 * getPeso()) + (800 * getDistancia());
    }

}
