package modelos;

public class Aereo extends Envio {
    public Aereo(String cliente, String Codigo, double peso, double distancia) {
        super(cliente, Codigo, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (4000 * getPeso()) + (5000 * getDistancia());
    }

}
