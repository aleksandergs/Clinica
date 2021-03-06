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
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import logic.Clinica;
import logic.Consulta;
import logic.Enfermedad;
import logic.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JSpinner spnFecha;
	private JTextArea txtSintomas;
	private JTextArea txtDiagnostico = new JTextArea();
	private Consulta updated = null;
	private Paciente paciente = null;
	private JButton btnCancelar;
	private JButton btnRegistrar = new JButton("");
	private JTextField txtPaciente;
	private JLabel lblAvisoDiagnostico = new JLabel("*");
	private JComboBox cbxEnfermedad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta(Consulta consulta, Paciente customer) {
		setModal(true);
		setBounds(100, 100, 500, 477);
		updated = consulta;
		paciente = customer;
		if(updated == null){
			setTitle("Registrar Consulta");
		}else {
			setTitle("Modificar Consulta");
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
			
			JLabel lblNewLabel = new JLabel("Codigo:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel.setBounds(12, 54, 45, 23);
			panel.add(lblNewLabel);
			
			txtCodigo = new JTextField();
			txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(83, 54, 206, 23);
			if(updated != null) {
				txtCodigo.setText(updated.getCodConsulta());
			}
			else {
				txtCodigo.setText("CS-"+Clinica.getInstance().getGeneradorCodigoConsulta());
			}
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			{
				JLabel lblFecha = new JLabel("Fecha:");
				lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblFecha.setBounds(12, 84, 45, 23);
				panel.add(lblFecha);
			}
			
			spnFecha = new JSpinner();
			spnFecha.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			Date date =  new Date();
			spnFecha.setModel(new SpinnerDateModel(date, null, null, Calendar.YEAR));
			JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
			spnFecha.setEditor(de_spnFecha);
			spnFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spnFecha.setBounds(83, 84, 206, 23);
			if(updated != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					spnFecha.setValue(sdf.parse(updated.getFechaConsulta().toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			panel.add(spnFecha);
			
			JLabel lblEnfermedad = new JLabel("Enfermedad:");
			lblEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblEnfermedad.setBounds(12, 114, 62, 23);
			panel.add(lblEnfermedad);
			
			JLabel lblSintomas = new JLabel("Sintomas:");
			lblSintomas.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblSintomas.setBounds(12, 144, 62, 23);
			panel.add(lblSintomas);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 169, 200, 179);
			panel.add(scrollPane);
			
			txtSintomas = new JTextArea();
			txtSintomas.setLineWrap(true);
			scrollPane.setViewportView(txtSintomas);
			if(updated != null) {
				txtSintomas.setText(updated.getSintomas());
			}
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(262, 169, 200, 179);
			panel.add(scrollPane_1);
			txtDiagnostico.setLineWrap(true);
			
			txtDiagnostico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			scrollPane_1.setViewportView(txtDiagnostico);
			if(updated != null) {
				txtDiagnostico.setText(updated.getDiagnostico());
			}
			
			JLabel lblDiagnostico = new JLabel("Diagnostico:");
			lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDiagnostico.setBounds(262, 144, 62, 23);
			panel.add(lblDiagnostico);
			
			JLabel lblPaciente = new JLabel("Paciente:");
			lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblPaciente.setBounds(10, 24, 45, 23);
			panel.add(lblPaciente);
			
			txtPaciente = new JTextField();
			txtPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtPaciente.setEditable(false);
			txtPaciente.setColumns(10);
			txtPaciente.setBounds(83, 24, 206, 23);
			if(paciente != null) {
				txtPaciente.setText(paciente.getNombre());
			}
			panel.add(txtPaciente);
			
			lblAvisoDiagnostico.setForeground(Color.RED);
			lblAvisoDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAvisoDiagnostico.setBounds(325, 144, 18, 14);
			panel.add(lblAvisoDiagnostico);
			
			cbxEnfermedad = new JComboBox();
			cbxEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					validarCamposVacios();
					habilitarBoton();
				}
			});
			cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
			addComoboBox();
			cbxEnfermedad.setBounds(83, 114, 319, 23);
			panel.add(cbxEnfermedad);
			if(updated != null) {
				cbxEnfermedad.setSelectedItem(updated.getEnfermedad().getCodigo()+":"+updated.getEnfermedad().getNombre());
			}
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
					public void actionPerformed(ActionEvent e) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String fechaConsulta = sdf.format(spnFecha.getValue());
						Enfermedad disease = null;
						if(cbxEnfermedad.getSelectedIndex() != 0) {
							disease = Clinica.getInstance().buscarEnfermedad(cbxEnfermedad.getSelectedItem().toString().substring(0, cbxEnfermedad.getSelectedItem().toString().indexOf(":")));
						}
						if(updated == null) {
							Consulta consulta = null;
							consulta = new Consulta(txtCodigo.getText(), fechaConsulta, txtSintomas.getText(), txtDiagnostico.getText(), disease);
							Clinica.getInstance().insertarConsulta(consulta, paciente);
							JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							updated.setDiagnostico(txtDiagnostico.getText());
							updated.setEnfermedad(disease);
							updated.setFechaConsulta(fechaConsulta);
							updated.setSintomas(txtSintomas.getText());
							Clinica.getInstance().modificarConsulta(updated);
							JOptionPane.showMessageDialog(null, "Modificacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						clean();
						validarCamposVacios();
						habilitarBoton();
					}
				});
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
		txtCodigo.setText("CS-"+Clinica.getInstance().getGeneradorCodigoConsulta());
		spnFecha.setValue(new Date());
		txtSintomas.setText("");
		txtDiagnostico.setText("");
		cbxEnfermedad.setSelectedIndex(0);
	}
	
	private void habilitarBoton() {
		if(txtDiagnostico.getText().isEmpty()){
			btnRegistrar.setEnabled(false);
		}
		else {
			btnRegistrar.setEnabled(true);
		}
	}
	
	private void validarCamposVacios() {
		if(txtDiagnostico.getText().isEmpty()) {
			lblAvisoDiagnostico.setText("*");
		} else {
			lblAvisoDiagnostico.setText("");
		}
	}
	
	private void addComoboBox() {
		String addValue;
		for(Enfermedad enf : Clinica.getInstance().getMisEnfermedades()) {
			addValue = enf.getCodigo()+":"+enf.getNombre();
			cbxEnfermedad.addItem(addValue);
		}
	}
}
