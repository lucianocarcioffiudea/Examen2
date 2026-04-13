import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Envio;

public class EnviosServicio {

    private static List<Envio> envios = new ArrayList<>();

    public static String[] encabezados = new String[] { "Tipo", "Número", "Titular", "Peso", "Distancia", "Costo" };
    
    public static Envio getEnvios(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            return envios.get(posicion);
        }
        return null;
    }

    public static void mostrar(JTable tbl) {
        String[][] datos = new String[envios.size()][encabezados.length];

        int fila = 0;
        for (Envio envio : envios) {

        datos[fila][0] = envio.getTipo().toString();
        datos[fila][1] = envio.getNumero();
        datos[fila][2] = envio.getTitular();
        datos[fila][3] = String.valueOf(envio.getPeso());
        datos[fila][4] = String.valueOf(envio.getDistancia());
        datos[fila][5] = String.valueOf(envio.getCosto());

        fila++;
    }

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }
    public static boolean eliminar(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            envios.remove(posicion);
            return true;
        }
        return false;
    }
}
