package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logic.Clinica;
import logic.Paciente;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar = new JButton();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JSpinner spnFecha;
	private JTextArea txtAlergias;
	private JComboBox cbxTipoSangre;
	private JComboBox cbxGenero;
	private JLabel lblAvisoNombre = new JLabel();
	private JLabel lblAvisoTelefono = new JLabel();
	private JLabel lblAvisoCedula = new JLabel();
	private JLabel lblAvisoDireccion = new JLabel();
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtTelefono;
	private Paciente updated = null;
	private JLabel lblCedula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegPaciente dialog = new RegPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegPaciente(Paciente paciente) {
		setModal(true);
		updated = paciente;
		setBounds(100, 100, 545, 450);
		if(updated == null){
			setTitle("Registrar Paciente");
		}else {
			setTitle("Modificar Paciente");
		}
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel.setBounds(10, 20, 70, 23);
			panel.add(lblNewLabel);
			
			JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
			lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblFechaNacimiento.setBounds(10, 50, 88, 23);
			panel.add(lblFechaNacimiento);
			
			lblCedula = new JLabel("Cedula Tutor:");
			lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblCedula.setBounds(10, 80, 70, 23);
			panel.add(lblCedula);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblTelefono.setBounds(10, 110, 70, 23);
			panel.add(lblTelefono);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDireccion.setBounds(10, 140, 70, 23);
			panel.add(lblDireccion);
			
			JLabel lblGenero = new JLabel("Genero:");
			lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblGenero.setBounds(10, 170, 70, 23);
			panel.add(lblGenero);
			
			JLabel lblTipoSangre = new JLabel("Tipo Sangre:");
			lblTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblTipoSangre.setBounds(10, 200, 70, 23);
			panel.add(lblTipoSangre);
			
			JLabel lblAlergias = new JLabel("Alergias:");
			lblAlergias.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAlergias.setBounds(10, 230, 70, 23);
			panel.add(lblAlergias);
			
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			txtNombre.setBounds(110, 20, 206, 23);
			if(updated != null) {
				txtNombre.setText(updated.getNombre());
			}
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			spnFecha = new JSpinner();
			Date date =  new Date();
			spnFecha.setModel(new SpinnerDateModel(date, null, new Date(), Calendar.DAY_OF_YEAR));
			JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
			spnFecha.setEditor(de_spnFecha);
			spnFecha.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String fecha = sdf.format(spnFecha.getValue());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate date = LocalDate.parse(fecha, formatter);
					Period edad = Period.between(date, LocalDate.now());
					if(edad.getYears() >= 18) {
						lblCedula.setText("Cedula:");
					}
					else {
						lblCedula.setText("Cedula Tutor:");
					}
				}
			});
			spnFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spnFecha.setBounds(110, 50, 206, 23);
			if(updated != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = null;
				try {
					fecha = formatter.parse(updated.getFechaNacimiento());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				spnFecha.setValue(fecha);
				spnFecha.setEnabled(false);
			}
			panel.add(spnFecha);
			
			MaskFormatter cedula = null;
			try {
				cedula = new MaskFormatter("###-#######-#");
				cedula.setPlaceholderCharacter('_');
			} catch (ParseException e) {}
			txtCedula = new JFormattedTextField(cedula);
			txtCedula.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			txtCedula.setBounds(110, 80, 206, 23);
			if(updated != null) {
				txtCedula.setText(updated.getCedula());
				txtCedula.setEditable(false);
			}
			panel.add(txtCedula);
			
			MaskFormatter telefono = null;
			try {
				telefono = new MaskFormatter("###-###-####");
				telefono.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtTelefono = new JFormattedTextField(telefono);
			txtTelefono.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			txtTelefono.setBounds(110, 110, 206, 23);
			if(updated != null) {
				txtTelefono.setText(updated.getTelefono());
			}
			panel.add(txtTelefono);
			
			txtDireccion = new JTextField();
			txtDireccion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(110, 140, 375, 23);
			if(updated != null) {
				txtDireccion.setText(updated.getDireccion());
			}
			panel.add(txtDireccion);
			
			cbxGenero = new JComboBox();
			cbxGenero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarBoton();
					validarCamposVacios();
				}
			});
			cbxGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbxGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
			cbxGenero.setBounds(110, 170, 206, 23);
			if(updated != null) {
				cbxGenero.setSelectedItem(updated.getGenero());
			}
			panel.add(cbxGenero);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarBoton();
					validarCamposVacios();
				}
			});
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"O+ positivo", "O- negativo", "A+ positivo", "A- negativo", "B+ positivo", "B- negativo", "AB+positivo", "AB- negativo"}));
			cbxTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbxTipoSangre.setBounds(110, 200, 206, 23);
			if(updated != null) {
				cbxTipoSangre.setSelectedItem(updated.getTipoSangre());
			}
			panel.add(cbxTipoSangre);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(110, 230, 375, 74);
			panel.add(scrollPane);
			
			txtAlergias = new JTextArea();
			txtAlergias.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					habilitarBoton();
					validarCamposVacios();
				}
			});
			scrollPane.setViewportView(txtAlergias);
			if(updated != null) {
				txtAlergias.setText(updated.getAlergias());
			}
			
			lblAvisoNombre.setForeground(Color.RED);
			lblAvisoNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAvisoNombre.setBounds(320, 20, 18, 14);
			panel.add(lblAvisoNombre);
			
			lblAvisoCedula = new JLabel("");
			lblAvisoCedula.setForeground(Color.RED);
			lblAvisoCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAvisoCedula.setBounds(320, 80, 18, 14);
			panel.add(lblAvisoCedula);
			
			lblAvisoTelefono.setForeground(Color.RED);
			lblAvisoTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAvisoTelefono.setBounds(320, 110, 18, 14);
			panel.add(lblAvisoTelefono);
			
			lblAvisoDireccion.setForeground(Color.RED);
			lblAvisoDireccion.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAvisoDireccion.setBounds(489, 140, 18, 14);
			panel.add(lblAvisoDireccion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar.setEnabled(false);
				if(updated == null){
					btnRegistrar.setText("Registrar");
				}
				else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String birthday = sdf.format(spnFecha.getValue());
						if(updated == null) {
							Paciente paci = null;
							paci = new Paciente(txtCedula.getText(), txtNombre.getText(), cbxGenero.getSelectedItem().toString(), birthday, txtDireccion.getText(), txtTelefono.getText(), txtAlergias.getText(), cbxTipoSangre.getSelectedItem().toString());
							Clinica.getInstance().insertarPaciente(paci);
							JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							RegConsulta registrarConsulta = new RegConsulta(null, paci);
							registrarConsulta.setVisible(true);
						}
						else {
							updated.setAlergias(txtAlergias.getText());
							updated.setDireccion(txtDireccion.getText());
							updated.setGenero(cbxGenero.getSelectedItem().toString());
							updated.setNombre(txtNombre.getText());
							updated.setTelefono(txtTelefono.getText());
							updated.setTipoSangre(cbxTipoSangre.getSelectedItem().toString());
							Clinica.getInstance().modificarPaciente(updated);
							JOptionPane.showMessageDialog(null, "Modificacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void habilitarBoton() {
		if(txtNombre.getText().isEmpty() || !txtCedula.isEditValid() && !txtTelefono.isEditValid() || txtDireccion.getText().isEmpty()){
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
		if(!txtCedula.isEditValid()) {
			lblAvisoCedula.setText("*");
		} else {
			lblAvisoCedula.setText("");
		}
		if(!txtTelefono.isEditValid()) {
			lblAvisoTelefono.setText("*");
		} else {
			lblAvisoTelefono.setText("");
		}
		if(txtDireccion.getText().isEmpty()) {
			lblAvisoDireccion.setText("*");
		} else {
			lblAvisoDireccion.setText("");
		}
	}
}
