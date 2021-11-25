package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import logic.Clinica;
import logic.Vacuna;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtFabricante;
	private JTextField txtContra;
	private JLabel lblAvisoContra;
	private JLabel lblAvisoFabricante;
	private JSpinner spnDosis;
	private JTextArea txtEfectos;
	private JLabel lblAvisoNombre;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacunas dialog = new RegVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacunas() {
		setAlwaysOnTop(true);
		setTitle("Registro de Vacunas");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 388);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Id:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblNewLabel.setBounds(10, 33, 40, 23);
				panel.add(lblNewLabel);
			}
			{
				txtId = new JTextField();
				txtId.setEditable(false);
				txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtId.setBounds(114, 33, 186, 23);
				txtId.setText("V-"+Clinica.getInstance().getGeneradorCodigoVacuna());
				panel.add(txtId);
				txtId.setColumns(10);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblNombre.setBounds(10, 63, 53, 23);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						validarCamposVacios();
						habilitarBoton();
					}
				});
				txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtNombre.setColumns(10);
				txtNombre.setBounds(114, 63, 298, 23);
				panel.add(txtNombre);
			}
			{
				JLabel lblFabricante = new JLabel("Fabricante:");
				lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblFabricante.setBounds(10, 123, 65, 23);
				panel.add(lblFabricante);
			}
			{
				txtFabricante = new JTextField();
				txtFabricante.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						validarCamposVacios();
						habilitarBoton();
					}
				});
				txtFabricante.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtFabricante.setColumns(10);
				txtFabricante.setBounds(114, 123, 298, 23);
				panel.add(txtFabricante);
			}
			{
				JLabel lblEnContraDe = new JLabel("Contra de:");
				lblEnContraDe.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblEnContraDe.setBounds(10, 93, 53, 23);
				panel.add(lblEnContraDe);
			}
			{
				txtContra = new JTextField();
				txtContra.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						validarCamposVacios();
						habilitarBoton();
					}
				});
				txtContra.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtContra.setColumns(10);
				txtContra.setBounds(114, 93, 298, 23);
				panel.add(txtContra);
			}
			{
				JLabel lblDosis = new JLabel("Dosis:");
				lblDosis.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblDosis.setBounds(10, 153, 65, 23);
				panel.add(lblDosis);
			}
			{
				spnDosis = new JSpinner();
				spnDosis.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						validarCamposVacios();
						habilitarBoton();
					}
				});
				spnDosis.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnDosis.setFont(new Font("Tahoma", Font.PLAIN, 12));
				spnDosis.setBounds(114, 153, 186, 23);
				panel.add(spnDosis);
			}
			
			JLabel lblEfectosSecundarios = new JLabel("Efectos Secundarios:");
			lblEfectosSecundarios.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblEfectosSecundarios.setBounds(10, 183, 108, 23);
			panel.add(lblEfectosSecundarios);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(114, 183, 298, 93);
			panel.add(scrollPane);
			
			txtEfectos = new JTextArea();
			txtEfectos.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			scrollPane.setViewportView(txtEfectos);
			{
				lblAvisoNombre = new JLabel("");
				lblAvisoNombre.setForeground(Color.RED);
				lblAvisoNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblAvisoNombre.setBounds(416, 63, 18, 14);
				panel.add(lblAvisoNombre);
			}
			{
				lblAvisoContra = new JLabel("");
				lblAvisoContra.setForeground(Color.RED);
				lblAvisoContra.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblAvisoContra.setBounds(416, 93, 18, 14);
				panel.add(lblAvisoContra);
			}
			{
				lblAvisoFabricante = new JLabel("");
				lblAvisoFabricante.setForeground(Color.RED);
				lblAvisoFabricante.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblAvisoFabricante.setBounds(416, 123, 18, 14);
				panel.add(lblAvisoFabricante);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Vacuna vaccine = null;
						vaccine = new Vacuna(txtId.getText(), txtNombre.getText(), txtContra.getText(), Integer.valueOf(spnDosis.getValue().toString()), txtFabricante.getText(), txtEfectos.getText());
						Clinica.getInstance().insertarVacuna(vaccine);
						JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						btnRegistrar.setEnabled(false);
						clean();
					}
				});
				btnRegistrar.setEnabled(false);
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void clean() {
		txtId.setText("V-"+Clinica.getInstance().getGeneradorCodigoVacuna());
		txtFabricante.setText("");
		txtNombre.setText("");
		txtContra.setText("");
		txtNombre.setText("");
		txtEfectos.setText("");
		spnDosis.setValue(Integer.valueOf(1));
	}
	
	private void habilitarBoton() {
		if(txtNombre.getText().isEmpty() || txtContra.getText().isEmpty() || txtFabricante.getText().isEmpty()){
			btnRegistrar.setEnabled(false);
		}
		else {
			btnRegistrar.setEnabled(true);
		}
	}
	
	private void validarCamposVacios() {
		if(txtNombre.getText().isEmpty()) {
			lblAvisoNombre.setText("*");
		} else {
			lblAvisoNombre.setText("");
		}
		
		if(txtContra.getText().isEmpty()) {
			lblAvisoContra.setText("*");
		} else {
			lblAvisoContra.setText("");
		}
		
		if(txtFabricante.getText().isEmpty()) {
			lblAvisoFabricante.setText("*");
		} else {
			lblAvisoFabricante.setText("");
		}
	}
}
