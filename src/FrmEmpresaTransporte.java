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
    private JLabel lblDistancia, lblPeso, lblTipo;
    private JTabbedPane tp;
    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtCliente, txtDistancia,txtPeso;
    private JComboBox<TipoDeEnvio> cmbTipoDeEnvio;

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

        JPanel pnlEnvio = new JPanel();
        pnlEnvio.setLayout(new BoxLayout(pnlEnvio, BoxLayout.Y_AXIS));
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 100));
        pnlEditarEnvio.setLayout(null);

        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtCodigo);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        lblDistancia = new JLabel("Distancia en km");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        lblTipo.setVisible(true);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoDeEnvio = new JComboBox<>();
        cmbTipoDeEnvio .setBounds(320, 10, 100, 25);

        DefaultComboBoxModel mdlTipoDeEnvio = new DefaultComboBoxModel<>(TipoDeEnvio.values());
        cmbTipoDeEnvio.setModel(mdlTipoDeEnvio);
        pnlEditarEnvio.add(cmbTipoDeEnvio);

        cmbTipoDeEnvio.addActionListener(e -> {
                txtDistancia.setVisible(true);
            });
        lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        lblPeso.setVisible(true);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        txtPeso.setVisible(true);
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

        tp = new JTabbedPane();
        tp.addTab("Envios", spEnvios);

        add(tbEmpresaTransporte, BorderLayout.NORTH);
        add(tp, BorderLayout.CENTER);
    }
    

    private void btnAgregarEnvioClick() {
        pnlEditarEnvio.setVisible(true);
        tp.setSelectedIndex(0);

    }

    private void btnQuitarPedidoClick() {
        if (tblEnvios.getSelectedRow() >= 0) {
            if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el envio?", "",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                EnviosServicio.eliminar(tblEnvios.getSelectedRow());
                EnviosServicio.mostrar(tblEnvios);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un envio");
        }

    }
    private void btnGuardarEnvioClick() {
        pnlEditarEnvio.setVisible(true);

    }

    private void btnCancelarEnvioClick() {
        pnlEditarEnvio.setVisible(true);

    }
}