package modelos;

public class Terrestre extends Envio {

    public Terrestre(String cliente, String Codigo, double peso, double distancia) {
        super(cliente, Codigo, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (2000 * getPeso()) + (1500 * getDistancia());
    }

}
