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
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import logic.CitaMedica;
import logic.Clinica;
import logic.Medico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDoctor;
	private JTextField txtFiltro;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private JComboBox<String> cbxFiltrarPor;
	private JFormattedTextField txtTelefono;
	private JSpinner spnFecha;
	private JComboBox cbxHorario;
	private JButton btnRegistrar;
	private JLabel lblAvisoNombre;
	private JLabel lblAvisoTelefono;
	private JLabel lblAvisoDoctor;
	private CitaMedica updated = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCita dialog = new RegCita(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCita(CitaMedica citaMedica) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 500, 500);
		updated = citaMedica;
		if(updated == null){
			setTitle("Registrar Cita Medica");
		}else {
			setTitle("Modificar Cita Medica");
		}
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 10, 450, 174);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 24, 55, 23);
		panel.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(10, 54, 55, 23);
		panel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTelefono.setBounds(10, 84, 55, 23);
		panel.add(lblTelefono);
		
		JLabel lblFechaCita = new JLabel("Fecha Cita:");
		lblFechaCita.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFechaCita.setBounds(10, 114, 55, 23);
		panel.add(lblFechaCita);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDoctor.setBounds(10, 144, 55, 23);
		panel.add(lblDoctor);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCodigo.setBounds(70, 24, 206, 23);
		if(updated != null) {
			txtCodigo.setText(updated.getCodigo());
		}
		else {
			txtCodigo.setText("CM-"+Clinica.getInstance().getGeneradorCodigoCitaMedica());
		}
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				validarCamposVacios();
				habilitarBoton();
			}
		});
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNombre.setColumns(10);
		txtNombre.setBounds(70, 54, 358, 23);
		if(updated != null) {
			txtNombre.setText(updated.getNombrePersona());
		}
		panel.add(txtNombre);
		
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
		txtTelefono.setBounds(70, 84, 206, 23);
		if(updated != null) {
			txtTelefono.setText(updated.getNumeroPersona());
		}
		panel.add(txtTelefono);
		
		txtDoctor = new JTextField();
		txtDoctor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDoctor.setEditable(false);
		txtDoctor.setColumns(10);
		txtDoctor.setBounds(70, 144, 358, 23);
		if(updated != null) {
			txtDoctor.setText(updated.getMedico().getCodigoUsuario()+":"+updated.getMedico().getNombre());
		}
		panel.add(txtDoctor);
		
		spnFecha = new JSpinner();
		Date date =  new Date();
		spnFecha.setModel(new SpinnerDateModel(date, null, null, Calendar.YEAR));
		JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
		spnFecha.setEditor(de_spnFecha);
		spnFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spnFecha.setBounds(70, 114, 120, 23);
		if(updated != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				spnFecha.setValue(sdf.parse(updated.getFecha().toString()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		panel.add(spnFecha);
		
		lblAvisoNombre = new JLabel("");
		lblAvisoNombre.setForeground(Color.RED);
		lblAvisoNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoNombre.setBounds(432, 54, 18, 14);
		panel.add(lblAvisoNombre);
		
		lblAvisoTelefono = new JLabel("");
		lblAvisoTelefono.setForeground(Color.RED);
		lblAvisoTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoTelefono.setBounds(280, 84, 18, 14);
		panel.add(lblAvisoTelefono);
		
		lblAvisoDoctor = new JLabel("");
		lblAvisoDoctor.setForeground(Color.RED);
		lblAvisoDoctor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAvisoDoctor.setBounds(432, 144, 18, 14);
		panel.add(lblAvisoDoctor);
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHorario.setBounds(248, 114, 55, 23);
		panel.add(lblHorario);
		
		cbxHorario = new JComboBox();
		cbxHorario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxHorario.setModel(new DefaultComboBoxModel(new String[] {"08:00 - 08:30", "08:30 - 09:00", "09:00 - 09:30", "09:30 - 10:00", "10:00 - 10:30", "10:30 - 11:00", "11:00 - 11:30", "11:30 - 12:00", "12:00 - 12:30", "12:30 - 13:00", "13:00 - 13:30", "13:30 - 14:00", "14:00 - 14:30", "14:30 - 15:00", "15:00 - 15:30", "15:30 - 16:00", "16:00 - 16:30", "16:30 - 17:00", "17:00 - 17:30", "17:30 - 18:00", "19:00 - 19:30", "19:30 - 20:00"}));
		cbxHorario.setBounds(290, 114, 138, 23);
		if(updated != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			String fecha = formatter.format(updated.getFecha());
			cbxHorario.setSelectedItem(fecha);
		}
		panel.add(cbxHorario);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Seleccionar Doctor:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 190, 450, 196);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar por:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 20, 55, 23);
		panel_1.add(lblNewLabel_1);
		
		txtFiltro = new JTextField();
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = txtFiltro.getText();
				filter(query);
			}
		});
		txtFiltro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFiltro.setBounds(140, 20, 206, 23);
		panel_1.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 430, 136);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int aux = table.getSelectedRow();
				if(aux != -1) {
					String doctor = ((String) table.getValueAt(aux, 0)+":"+table.getValueAt(aux, 1));
					txtDoctor.setText(doctor);
				}
				validarCamposVacios();
				habilitarBoton();
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] headers = {"ID","Nombre","Especialidad", "Consultorio"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		
		cbxFiltrarPor = new JComboBox<String>();
		cbxFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxFiltrarPor.setModel(new DefaultComboBoxModel<String>(new String[] {"Codigo:", "Nombre:"}));
		cbxFiltrarPor.setBounds(65, 20, 70, 23);
		panel_1.add(cbxFiltrarPor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("");
				if(updated == null){
					btnRegistrar.setText("Registrar");
				}else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String horario = cbxHorario.getSelectedItem().toString().substring(0, cbxHorario.getSelectedItem().toString().indexOf("-"));
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String fechacita = sdf.format(spnFecha.getValue())+" "+horario;
						Medico medico = (Medico) Clinica.getInstance().buscarUsuario(txtDoctor.getText().substring(0, txtDoctor.getText().indexOf(":")));
						if(!Clinica.getInstance().buscarCitaMedicaExiste(fechacita, updated, txtDoctor.getText().substring(0, txtDoctor.getText().indexOf(":")))) {
							if(updated == null) {
								CitaMedica cita = null;
								cita = new CitaMedica(txtCodigo.getText(), fechacita, txtNombre.getText(), txtTelefono.getText(), medico);
								Clinica.getInstance().insertarCita(cita);
								JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								btnRegistrar.setEnabled(false);
							}
							else {
								updated.setFecha(fechacita);
								updated.setMedico(medico);
								updated.setNombrePersona(txtNombre.getText());
								updated.setNumeroPersona(txtTelefono.getText());
								Clinica.getInstance().modificarCitaMedica(updated);
								JOptionPane.showMessageDialog(null, "Modificacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							clean();
							validarCamposVacios();
							habilitarBoton();
						}
						else {
							JOptionPane.showMessageDialog(null, "Fecha o Horario no disponibles", "Error", JOptionPane.ERROR_MESSAGE);
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
		loadMedicos();
	}
	
	public void loadMedicos() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		int i;
		for (i = 0; i < Clinica.getInstance().getMisUsuarios().size(); i++){
			if(Clinica.getInstance().getMisUsuarios().get(i) instanceof Medico)
			{
				rows[0] = Clinica.getInstance().getMisUsuarios().get(i).getCodigoUsuario();
				rows[1] = Clinica.getInstance().getMisUsuarios().get(i).getNombre();
				rows[2] = ((Medico) Clinica.getInstance().getMisUsuarios().get(i)).getEspecialidad();
				rows[3] = ((Medico) Clinica.getInstance().getMisUsuarios().get(i)).getNumHabitacion();
				model.addRow(rows);
			}
		}
	}
	
	private void filter(String doctor){
		TableRowSorter<DefaultTableModel> tsr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tsr);
		
		tsr.setRowFilter(RowFilter.regexFilter("(?i)"+doctor, cbxFiltrarPor.getSelectedIndex()));
	}
	
	private void clean() {
		txtCodigo.setText("CM-"+Clinica.getInstance().getGeneradorCodigoCitaMedica());
		txtNombre.setText("");
		cbxHorario.setSelectedIndex(0);
		spnFecha.setValue(new Date());
		txtTelefono.setValue(null);
		txtDoctor.setText("");
		txtFiltro.setText("");
		loadMedicos();
	}
	
	private void habilitarBoton() {
		if(txtNombre.getText().isEmpty() || !txtTelefono.isEditValid() || txtDoctor.getText().isEmpty()){
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
		
		if(!txtTelefono.isEditValid()) {
			lblAvisoTelefono.setText("*");
		} else {
			lblAvisoTelefono.setText("");
		}
		
		if(txtDoctor.getText().isEmpty()) {
			lblAvisoDoctor.setText("*");
		} else {
			lblAvisoDoctor.setText("");
		}
	}
}