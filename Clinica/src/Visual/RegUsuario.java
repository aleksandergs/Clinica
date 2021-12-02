package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logic.Administrador;
import logic.Clinica;
import logic.Medico;
import logic.Usuario;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextField txtLogin = new JTextField();
	private JPasswordField txtContrasena1 = new JPasswordField();
	private JPasswordField txtContrasena2 = new JPasswordField();
	private JRadioButton rdbtnDoctor = new JRadioButton("Doctor");
	private JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
	private JRadioButton rdbtnSecretario = new JRadioButton("Secretario");
	private JPanel panelDoctor = new JPanel();
	private JComboBox cbxEspecialidad = new JComboBox<>();
	private JSpinner spnHabitacion = new JSpinner();
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtTelefono;
	private JButton btnRegistrar = new JButton("");
	private JLabel lblAvisoNombre = new JLabel("");
	private JLabel lblAvisoCedula = new JLabel("");
	private JLabel lblAvisoClave1 = new JLabel("");
	private JLabel lblAvisoLogin = new JLabel("");
	private JLabel lblAvisoTelefono = new JLabel("");
	private JLabel lblAvisoEsp = new JLabel("");
	private JLabel lblAvisoClave2 = new JLabel("");
	private Usuario updated = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUsuario dialog = new RegUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario(Usuario user) {
		setAlwaysOnTop(true);
		setResizable(false);
		setModal(true);
		updated = user;
		setBounds(100, 100, 550, 434);
		if(updated == null){
			setTitle("Registrar Usuario");
		}else {
			setTitle("Modificar Usuario");
		}
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 512, 195);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigoid = new JLabel("Codigo/Id:");
		lblCodigoid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodigoid.setBounds(10, 34, 57, 23);
		panel.add(lblCodigoid);
		
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(75, 34, 144, 23);
		if(updated != null) {
			txtID.setText(updated.getCodigoUsuario());
		}
		else {
			txtID.setText("U-"+Clinica.getInstance().getGeneradorCodigoUserA());
		}
		panel.add(txtID);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(10, 64, 48, 23);
		panel.add(lblNombre);

		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNombre.setColumns(10);
		txtNombre.setBounds(75, 64, 415, 23);
		if(updated != null) {
			txtNombre.setText(updated.getNombre());
		}
		panel.add(txtNombre);
		
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
		txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCedula.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		txtCedula.setBounds(75, 94, 173, 23);
		if(updated != null) {
			txtCedula.setEditable(false);
			txtCedula.setText(updated.getCedulaUsuario());
		}
		panel.add(txtCedula);
		
		JLabel label_1 = new JLabel("C\u00E9dula:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(10, 94, 48, 23);
		panel.add(label_1);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLogin.setBounds(10, 124, 48, 23);
		panel.add(lblLogin);
		
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		txtLogin.setBounds(75, 124, 173, 23);
		if(updated != null) {
			txtLogin.setText(updated.getLogin());
		}
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel label_3 = new JLabel("Telefono:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(265, 94, 48, 23);
		panel.add(label_3);
		
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
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTelefono.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		txtTelefono.setBounds(317, 94, 173, 23);
		if(updated != null) {
			txtTelefono.setText(updated.getTelefono());
		}
		panel.add(txtTelefono);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContrasea.setBounds(10, 154, 66, 23);
		panel.add(lblContrasea);
		
		JLabel lblConfirmar = new JLabel("Confirmar:");
		lblConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmar.setBounds(265, 154, 66, 23);
		panel.add(lblConfirmar);

		txtContrasena1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		txtContrasena1.setBounds(75, 154, 173, 23);
		if(updated != null) {
			txtContrasena1.setText(updated.getPassword());
		}
		panel.add(txtContrasena1);

		txtContrasena2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		txtContrasena2.setBounds(320, 154, 170, 23);
		if(updated != null) {
			txtContrasena2.setText(updated.getPassword());
		}
		panel.add(txtContrasena2);
		
		lblAvisoNombre.setForeground(Color.RED);
		lblAvisoNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoNombre.setBounds(494, 64, 18, 14);
		panel.add(lblAvisoNombre);
		
		lblAvisoCedula.setForeground(Color.RED);
		lblAvisoCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoCedula.setBounds(252, 94, 18, 14);
		panel.add(lblAvisoCedula);
		
		lblAvisoTelefono.setForeground(Color.RED);
		lblAvisoTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoTelefono.setBounds(494, 94, 18, 14);
		panel.add(lblAvisoTelefono);
		
		lblAvisoLogin.setForeground(Color.RED);
		lblAvisoLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoLogin.setBounds(252, 124, 18, 14);
		panel.add(lblAvisoLogin);
		
		lblAvisoClave1.setForeground(Color.RED);
		lblAvisoClave1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoClave1.setBounds(252, 154, 18, 14);
		panel.add(lblAvisoClave1);
		
		lblAvisoClave2.setForeground(Color.RED);
		lblAvisoClave2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoClave2.setBounds(494, 154, 18, 14);
		panel.add(lblAvisoClave2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tipo Usuario:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBounds(10, 210, 512, 95);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnDoctor.setSelected(true);
				panelDoctor.setVisible(true);
				rdbtnAdministrador.setSelected(false);
				rdbtnSecretario.setSelected(false);
				txtID.setText("M-"+Clinica.getInstance().getGeneradorCodigoDoctor());
			}
		});
		rdbtnDoctor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnDoctor.setBounds(195, 41, 121, 23);
		panel_1.add(rdbtnDoctor);
		
		rdbtnAdministrador.setSelected(true);
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAdministrador.setSelected(true);
				panelDoctor.setVisible(false);
				rdbtnDoctor.setSelected(false);
				rdbtnSecretario.setSelected(false);
				txtID.setText("U-"+Clinica.getInstance().getGeneradorCodigoUserA());
			}
		});
		rdbtnAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnAdministrador.setBounds(55, 41, 121, 23);
		panel_1.add(rdbtnAdministrador);
		
		rdbtnSecretario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSecretario.setSelected(true);
				panelDoctor.setVisible(false);
				rdbtnDoctor.setSelected(false);
				rdbtnAdministrador.setSelected(false);
				txtID.setText("U-"+Clinica.getInstance().getGeneradorCodigoUserA());
			}
		});
		rdbtnSecretario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnSecretario.setBounds(335, 41, 121, 23);
		panel_1.add(rdbtnSecretario);
		
		panelDoctor.setVisible(false);
		panelDoctor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDoctor.setBounds(10, 310, 512, 40);
		if(updated != null){
			if(updated instanceof Medico) {
				rdbtnDoctor.setSelected(true);
				rdbtnDoctor.setEnabled(false);
				rdbtnAdministrador.setSelected(false);
				rdbtnAdministrador.setEnabled(false);
				rdbtnSecretario.setSelected(false);
				rdbtnSecretario.setEnabled(false);
				panelDoctor.setVisible(true);
			}
			else if(((Administrador) updated).getPuestoLaboral().equalsIgnoreCase("Administrador")) {
				rdbtnDoctor.setSelected(false);
				rdbtnDoctor.setEnabled(false);
				rdbtnAdministrador.setSelected(true);
				rdbtnAdministrador.setEnabled(false);
				rdbtnSecretario.setSelected(false);
				rdbtnSecretario.setEnabled(false);
			}
			else {
				rdbtnDoctor.setSelected(false);
				rdbtnDoctor.setEnabled(false);
				rdbtnAdministrador.setSelected(false);
				rdbtnAdministrador.setEnabled(false);
				rdbtnSecretario.setSelected(true);
				rdbtnSecretario.setEnabled(false);
			}
		}
		contentPanel.add(panelDoctor);
		panelDoctor.setLayout(null);
		
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEspecialidad.setBounds(10, 10, 66, 23);
		panelDoctor.add(lblEspecialidad);
		
		cbxEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		cbxEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxEspecialidad.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Alergolog\u00EDa", "Anestesiolog\u00EDa y reanimaci\u00F3n", "Aparato digestivo", "Cardiolog\u00EDa", "Endocrinolog\u00EDa y nutrici\u00F3n", "Geriatr\u00EDa", "Hematolog\u00EDa y hemoterapia", "Medicina de la educaci\u00F3n f\u00EDsica y del deporte", "Medicina espacial", "Medicina intensiva", "Medicina interna", "Medicina legal y forense", "Medicina preventiva y salud p\u00FAblica", "Medicina del trabajo", "Nefrolog\u00EDa", "Neumolog\u00EDa", "Neurolog\u00EDa", "Neurofisiolog\u00EDa Cl\u00EDnica", "Oncolog\u00EDa m\u00E9dica", "Oncolog\u00EDa radioter\u00E1pica", "Pediatr\u00EDa", "Psiquiatr\u00EDa", "Rehabilitaci\u00F3n", "Reumatolog\u00EDa", "Medicina familiar y comunitaria", "Cirug\u00EDa cardiovascular", "Cirug\u00EDa general y del aparato digestivo", "Cirug\u00EDa oral y maxilofacial", "Cirug\u00EDa ortop\u00E9dica y traumatolog\u00EDa", "Cirug\u00EDa pedi\u00E1trica", "Cirug\u00EDa pl\u00E1stica, est\u00E9tica y reparadora", "Cirug\u00EDa tor\u00E1cica", "Neurocirug\u00EDa", "Angiolog\u00EDa y cirug\u00EDa vascular", "Dermatolog\u00EDa m\u00E9dico-quir\u00FArgica y venereolog\u00EDa", "Obstetricia y ginecolog\u00EDa", "Oftalmolog\u00EDa", "Otorrinolaringolog\u00EDa", "Urolog\u00EDa"}));
		cbxEspecialidad.setBounds(75, 10, 223, 23);
		if(updated != null && updated instanceof Medico) {
			cbxEspecialidad.setSelectedItem(((Medico) updated).getEspecialidad());
		}
		panelDoctor.add(cbxEspecialidad);
		
		JLabel lblNoHabitacion = new JLabel("No. Habitacion:");
		lblNoHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNoHabitacion.setBounds(320, 10, 75, 23);
		panelDoctor.add(lblNoHabitacion);
		
		spnHabitacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		spnHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spnHabitacion.setBounds(395, 10, 95, 23);
		if(updated != null && updated instanceof Medico) {
			Integer consultorio = Integer.valueOf(((Medico) updated).getNumHabitacion());
			spnHabitacion.setValue(consultorio);
		}
		panelDoctor.add(spnHabitacion);
		
		lblAvisoEsp.setForeground(Color.RED);
		lblAvisoEsp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoEsp.setBounds(302, 10, 18, 14);
		panelDoctor.add(lblAvisoEsp);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				if(updated == null){
					btnRegistrar.setText("Registrar");
				}else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String password1 = new String(txtContrasena1.getPassword());
						String password2 = new String(txtContrasena2.getPassword());
						boolean loginUser = Clinica.getInstance().buscarLoginExiste(txtLogin.getText(), updated);
						if(password1.equals(password2) && !loginUser) {
							if(updated == null) {
								Usuario user = null;
								if(rdbtnDoctor.isSelected()) {
									user = new Medico(txtID.getText(), txtLogin.getText(), txtCedula.getText(), password1, txtNombre.getText(), txtTelefono.getText(), cbxEspecialidad.getSelectedItem().toString(), Integer.valueOf(spnHabitacion.getValue().toString()));
									Clinica.getInstance().insertarDoctor(user);
								}
								if(rdbtnAdministrador.isSelected()) {
									user = new Administrador(txtID.getText(), txtLogin.getText(), txtCedula.getText(), password1, txtNombre.getText(), txtTelefono.getText(), "Administrador");
									Clinica.getInstance().insertarUsuario(user);
								}
								if(rdbtnSecretario.isSelected()) {
									user = new Administrador(txtID.getText(), txtLogin.getText(), txtCedula.getText(), password1, txtNombre.getText(), txtTelefono.getText(), "Secretario");
									Clinica.getInstance().insertarUsuario(user);
								}
								setAlwaysOnTop(false);
								JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								setAlwaysOnTop(true);
								btnRegistrar.setEnabled(false);
								clean();
							}
							else {
								updated.setNombre(txtNombre.getText());
								updated.setLogin(txtLogin.getText());
								updated.setTelefono(txtTelefono.getText());
								updated.setPassword(password1);
								if(updated instanceof Medico) {
									((Medico) updated).setEspecialidad(cbxEspecialidad.getSelectedItem().toString());
									((Medico) updated).setNumHabitacion(Integer.valueOf(spnHabitacion.getValue().toString()));
								}
								else {
									if(rdbtnAdministrador.isSelected()) {
										((Administrador) updated).setPuestoLaboral("Administrador");
									}
									else {
										((Administrador) updated).setPuestoLaboral("Secretario");
									}
										
								}
								Clinica.getInstance().modificarUsuario(updated);
								setAlwaysOnTop(false);
								JOptionPane.showMessageDialog(null, "Modificacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								setAlwaysOnTop(true);
								dispose();
							}
						}
						else if(loginUser) {
							setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Este Usuario ya Existe", "Error", JOptionPane.ERROR_MESSAGE);
							setAlwaysOnTop(true);
						}
						else {
							setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Las Contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
							setAlwaysOnTop(true);
						}
					}
				});
				btnRegistrar.setEnabled(false);
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private void clean() {
		txtID.setText("U-"+Clinica.getInstance().getGeneradorCodigoUserA());
		txtCedula.setValue(null);
		txtContrasena1.setText("");
		txtContrasena2.setText("");
		txtLogin.setText("");
		txtNombre.setText("");
		txtTelefono.setValue(null);
		rdbtnAdministrador.setSelected(true);
		rdbtnDoctor.setSelected(false);
		rdbtnSecretario.setSelected(false);
		spnHabitacion.setValue(Integer.valueOf(0));
		panelDoctor.setVisible(false);
		cbxEspecialidad.setSelectedIndex(0);
	}
	
	private void habilitarBoton() {
		if(!txtCedula.isEditValid() || txtContrasena1.getPassword().length == 0 || txtContrasena2.getPassword().length == 0 || txtLogin.getText().isEmpty() || txtNombre.getText().isEmpty() || !txtTelefono.isEditValid() || (rdbtnDoctor.isSelected() && (cbxEspecialidad.getSelectedIndex() == 0))){
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
		
		if(txtLogin.getText().isEmpty()) {
			lblAvisoLogin.setText("*");
		} else {
			lblAvisoLogin.setText("");
		}
		
		if(txtContrasena1.getPassword().length == 0) {
			lblAvisoClave1.setText("*");
		} else {
			lblAvisoClave1.setText("");
		}
		
		if(txtContrasena2.getPassword().length == 0) {
			lblAvisoClave2.setText("*");
		} else {
			lblAvisoClave2.setText("");
		}
		
		if(rdbtnDoctor.isSelected() && (cbxEspecialidad.getSelectedIndex() == 0)) {
			lblAvisoEsp.setText("*");
		} else {
			lblAvisoEsp.setText("");
		}
	}
}
