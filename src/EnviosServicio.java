import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Envio;

public class EnviosServicio {

    private static List<Envio> envios = new ArrayList<>();

    public static String[] encabezados = new String[] { "Tipo", "Codigo", "Cliente", "Peso", "Distancia", "Costo" };

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
            int columna = 0;

            for (String dato : envio.getDatos()) {
                datos[fila][columna] = dato;
                columna++;
            }

            fila++;
        }

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl.setModel(dtm);
    }

    public static boolean eliminar(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            envios.remove(posicion);
            return true;
        }
        return false;
    }

    public static void agregar(Envio envio) {
        envios.add(envio);
    }
    
    public static boolean existeCodigo(String codigo) {
    for (Envio envio : envios) {
        if (envio.getCodigo().equalsIgnoreCase(codigo)) {
            return true;
        }
    }
    return false;
}
}