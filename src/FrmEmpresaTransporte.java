import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import modelos.TipoDeEnvio;

public class FrmEmpresaTransporte extends JFrame {
    public String[] encabezadosCuentas = new String[] { "Tipo", "Codigo", "Titular", "Peso",
            "Distancia", "Costo" };
    public String[] encabezadosTransacciones = new String[] { "Tipo", "Codigo", "Titular", "Peso", "Distancia", "Costo" };
    private JLabel lblDistancia,lblPeso;
    private JTabbedPane tp;
    private JTable tblEnvios,tblTransacciones;
    private JPanel pnlEditarEnvio, pnlEditarTransaccion;
    private JTextField txtNumero, txtCliente, txtDistancia,txtPeso, txtPesoTransaccion,txtDistanciaTransaccion;
    private JComboBox<TipoDeEnvio> cmbTipoDeEnvio, cmbEnvio;

    public FrmEmpresaTransporte(){
        setSize(600,400);
        setTitle("Empresa de transporte");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbEmpresaTransporte = new JToolBar();
        JButton btnAgregarPedido = new JButton();
        btnAgregarPedido.setIcon(new ImageIcon(getClass().getResource("/iconos/btnAgregarCliente.png")));
        btnAgregarPedido.setToolTipText("Agregar Pedido");
        btnAgregarPedido.addActionListener(evt -> {
             btnAgregarEnvioClick();
        }); 
        tbEmpresaTransporte.add(btnAgregarPedido);

        JButton btnQuitarPedido = new JButton();
        btnQuitarPedido.setIcon(new ImageIcon(getClass().getResource("/iconos/btnQuitarCliente.png")));
        btnQuitarPedido.setToolTipText("Quitar Pedido");
          btnQuitarPedido.addActionListener(evt -> {
            btnQuitarPedidoClick();
        }); 
        tbEmpresaTransporte.add(btnQuitarPedido);

        JButton btnTransaccion = new JButton();
        btnTransaccion.setIcon(new ImageIcon(getClass().getResource("/iconos/Transaccion.png")));
        btnTransaccion.setToolTipText("Realizar Transacción");
        btnTransaccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTransaccionClick();
            }
        });
        tbEmpresaTransporte.add(btnTransaccion);

        JPanel pnlEnvio = new JPanel();
        pnlEnvio.setLayout(new BoxLayout(pnlEnvio, BoxLayout.Y_AXIS));
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 100));
        pnlEditarEnvio.setLayout(null);

        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        lblDistancia = new JLabel("Distancia");
        lblDistancia.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        cmbTipoDeEnvio = new JComboBox<>();
        cmbTipoDeEnvio .setBounds(220, 10, 100, 25);

        DefaultComboBoxModel mdlTipoDeEnvio = new DefaultComboBoxModel<>(TipoDeEnvio.values());
        cmbTipoDeEnvio.setModel(mdlTipoDeEnvio);
        pnlEditarEnvio.add(cmbTipoDeEnvio);

        cmbTipoDeEnvio.addActionListener(e -> {
            switch ((TipoDeEnvio) cmbTipoDeEnvio.getSelectedItem()) {
                case Terrestre:
                txtDistancia.setVisible(true);
                break;
                case Maritimo:
                txtDistancia.setVisible(false);
                break;
                case Aereo:
                txtDistancia.setVisible(true);
                break;
            }

        });
        lblPeso = new JLabel("Peso");
        lblPeso.setBounds(220, 40, 100, 25);
        lblPeso.setVisible(false);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(320, 40, 100, 25);
        txtPeso.setVisible(false);
        pnlEditarEnvio.add(txtPeso);

        JButton btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        btnGuardarEnvio.addActionListener(evt -> {
            btnGuardarEnvioClick();
        }); 
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(320, 70, 100, 25);
        btnCancelarEnvio.addActionListener(evt -> {
            btnCancelarEnvioClick();
        }); 
        pnlEditarEnvio.add(btnCancelarEnvio);
        pnlEditarEnvio.setVisible(false);

        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        EnviosServicio.mostrar(tblEnvios);

        pnlEnvio.add(pnlEditarEnvio);
        pnlEnvio.add(spListaEnvios);

        JScrollPane spEnvios = new JScrollPane(pnlEnvio);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel pnlTransacciones = new JPanel();
        pnlTransacciones.setLayout(new BoxLayout(pnlTransacciones, BoxLayout.Y_AXIS));
        pnlEditarTransaccion = new JPanel();
        pnlEditarTransaccion.setPreferredSize(new Dimension(pnlEditarTransaccion.getWidth(), 100));
        pnlEditarTransaccion.setLayout(null);

        JLabel lblCuenta = new JLabel("Cliente");
        lblCuenta.setBounds(10, 10, 100, 25);
        pnlEditarTransaccion.add(lblCuenta);

        cmbEnvio = new JComboBox();
        cmbEnvio.setBounds(110, 10, 100, 25);
        pnlEditarTransaccion.add(cmbEnvio);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(10, 40, 100, 25);
        pnlEditarTransaccion.add(lblTipo);


        JLabel lblPesoTransaccion = new JLabel("Peso");
        lblPesoTransaccion.setBounds(10, 70, 100, 25);
        pnlEditarTransaccion.add(lblPesoTransaccion);

        txtPesoTransaccion = new JTextField();
        txtPesoTransaccion.setBounds(110, 70, 100, 25);
        pnlEditarTransaccion.add(txtPesoTransaccion);
        
        JLabel lblDistanciaTransaccion = new JLabel("Distancia");
        lblDistanciaTransaccion.setBounds(10, 100, 100, 25);
        pnlEditarTransaccion.add(lblDistanciaTransaccion);

        txtDistanciaTransaccion = new JTextField();
        txtDistanciaTransaccion.setBounds(110, 100, 100, 25);
        pnlEditarTransaccion.add(txtDistanciaTransaccion);

        JButton btnGuardarTransaccion = new JButton("Guardar");
        btnGuardarTransaccion.setBounds(220, 70, 100, 25);
        btnGuardarTransaccion.addActionListener(evt -> {
            btnGuardarTransaccionClick();
        });
        pnlEditarTransaccion.add(btnGuardarTransaccion);

        JButton btnCancelarTransaccion = new JButton("Cancelar");
        btnCancelarTransaccion.setBounds(320, 70, 100, 25);
        btnCancelarTransaccion.addActionListener(evt -> {
            btnCancelarTransaccionClick();
        });
        pnlEditarTransaccion.add(btnCancelarTransaccion);

        pnlEditarTransaccion.setVisible(false);

        tblTransacciones = new JTable();
        JScrollPane spListaTransacciones = new JScrollPane(tblTransacciones);

        pnlTransacciones.add(pnlEditarTransaccion);
        pnlTransacciones.add(spListaTransacciones);

        JScrollPane spTransacciones = new JScrollPane(pnlTransacciones);
        spTransacciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        tp = new JTabbedPane();
        tp.addTab("Envios", spEnvios);
        tp.addTab("Transacciones", spTransacciones);

        add(tbEmpresaTransporte, BorderLayout.NORTH);
        add(tp, BorderLayout.CENTER);
    }

  private void btnAgregarEnvioClick() {
        pnlEditarEnvio.setVisible(true);
        tp.setSelectedIndex(0);

    }

    private void btnQuitarPedidoClick() {
        if (tblEnvios.getSelectedRow() >= 0) {
            if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la cuenta?", "",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                EnviosServicio.eliminar(tblEnvios.getSelectedRow());
                EnviosServicio.mostrar(tblEnvios);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una cuenta");
        }

    }
    private void btnGuardarEnvioClick() {
        pnlEditarEnvio.setVisible(false);

    }

    private void btnCancelarEnvioClick() {
        pnlEditarEnvio.setVisible(false);

    }
    private void btnTransaccionClick() {
        pnlEditarTransaccion.setVisible(true);
        tp.setSelectedIndex(1);

    }

    private void btnGuardarTransaccionClick() {

    }

    private void btnCancelarTransaccionClick() {
        pnlEditarTransaccion.setVisible(false);

    }
}