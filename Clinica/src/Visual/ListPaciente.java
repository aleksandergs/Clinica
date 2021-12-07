package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logic.Clinica;
import logic.Consulta;
import logic.Medico;
import logic.Paciente;
import logic.Vacuna;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ListPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelListado;
	private DefaultTableModel modelHistorial;
	private DefaultTableModel modelConsultas;
	private DefaultTableModel modelVacunas;
	private Object[] rowsPacientes;
	private Object[] rowsHistorial;
	private Object[] rowsConsultas;
	private Object[] rowsVacunas;
	private JTextField txtFiltro;
	private JTable tableListado = new JTable();
	private Paciente selectedPaciente = null;
	private Consulta selectedConsulta = null;
	private JPanel panelListado;
	private JTextField txtNombreH;
	private JTextField txtNombreC;
	private JTextField txtNombreV;
	private JTable tableVacunas;
	private JTable tableHistorial;
	private JComboBox cbxFiltrarPor;
	private JTextArea txtAlergias;
	private JButton btnModificar = new JButton("Modificar");
	private JTable tableConsulta = new JTable();
	private JTextArea txtDiagConsulta;
	private JTextArea txtSintConsul;
	private JTextArea txtDiagHistorial;
	private JTextArea txtSintHistorial;
	private JTextArea txtEfectos;
	private JButton btnAgregarHistorial = new JButton("Agregar al Historial");
	private JButton btnAgregar = new JButton("Agregar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListPaciente dialog = new ListPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListPaciente() {
		setBounds(100, 100, 700, 575);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					int indice = tabbedPane.getSelectedIndex();
					if(indice != -1 && Clinica.getLoginUser() instanceof Medico) {
						if(indice == 0) {
							btnAgregarHistorial.setVisible(false);
							btnAgregar.setVisible(false);
							btnModificar.setVisible(true);
							if(tableListado.getSelectedRow() == -1) {
								btnModificar.setEnabled(false);
							}
							else {
								btnModificar.setEnabled(true);
							}
						}
						if(indice == 1) {
							btnAgregarHistorial.setVisible(false);
							btnModificar.setVisible(false);
							btnAgregar.setVisible(false);
						}
						if(indice == 2) {
							btnAgregarHistorial.setVisible(true);
							btnAgregar.setVisible(true);
							btnModificar.setVisible(true);
							if(tableConsulta.getSelectedRow() == -1) {
								btnModificar.setEnabled(false);
								btnAgregarHistorial.setEnabled(false);
							}
							else {
								btnModificar.setEnabled(true);
								btnAgregarHistorial.setEnabled(true);
							}
						}
						if(indice == 3) {
							btnModificar.setVisible(false);
							btnAgregarHistorial.setVisible(false);
							btnAgregar.setVisible(true);
						}
					}
				}
			});
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			
			panelListado = new JPanel();
			panelListado.setLayout(null);
			panelListado.setBorder(new TitledBorder(null, "Filtrar por:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			tabbedPane.addTab("Listado", null, panelListado, null);
			
			cbxFiltrarPor = new JComboBox();
			cbxFiltrarPor.setModel(new DefaultComboBoxModel(new String[] {"Cedula:", "Nombre:", "Fecha:", "Telefono:", "Tipo Sangre:"}));
			cbxFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbxFiltrarPor.setBounds(10, 20, 110, 23);
			panelListado.add(cbxFiltrarPor);
			
			txtFiltro = new JTextField();
			txtFiltro.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					filter(txtFiltro.getText());
				}
			});
			txtFiltro.setColumns(10);
			txtFiltro.setBounds(127, 20, 206, 23);
			panelListado.add(txtFiltro);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 50, 647, 294);
			panelListado.add(scrollPane);
			
			tableListado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int aux = tableListado.getSelectedRow();
					if(aux != -1) {
						String cedula = (String) tableListado.getValueAt(aux, 0);
						String fecha = (String) tableListado.getValueAt(aux, 2);
						selectedPaciente = Clinica.getInstance().buscarPaciente(cedula, fecha);
						if(selectedPaciente != null) {
							btnModificar.setEnabled(true);
							txtAlergias.setText(selectedPaciente.getAlergias());
							loadConsultas(selectedPaciente);
							loadHistorial(selectedPaciente);
							loadVacunas(selectedPaciente);
							if(Clinica.getLoginUser() instanceof Medico) {
								tabbedPane.setEnabledAt(1, true);
								tabbedPane.setEnabledAt(2, true);
								tabbedPane.setEnabledAt(3, true);
								txtNombreH.setText(selectedPaciente.getNombre());
								txtNombreC.setText(selectedPaciente.getNombre());
								txtNombreV.setText(selectedPaciente.getNombre());
							}
						}
					}
					else {
						btnModificar.setEnabled(true);
						txtAlergias.setText("");
						tabbedPane.setEnabledAt(1, false);
						tabbedPane.setEnabledAt(2, false);
						tabbedPane.setEnabledAt(3, false);
						txtNombreH.setText("");
						txtNombreC.setText("");
						txtNombreV.setText("");
					}
				}
			});
			tableListado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			String[] headers = {"Cedula","Nombre","Nacimiento","Telefono","Sangre"};
			modelListado = new DefaultTableModel();
			tableListado.setModel(modelListado);
			modelListado.setColumnIdentifiers(headers);
			scrollPane.setViewportView(tableListado);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alergias:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(10, 350, 647, 100);
			panelListado.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, BorderLayout.CENTER);
			
			txtAlergias = new JTextArea();
			txtAlergias.setLineWrap(true);
			scrollPane_1.setViewportView(txtAlergias);
			{
				JPanel panelHistorial = new JPanel();
				panelHistorial.setBorder(new TitledBorder(null, "Paciente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				tabbedPane.addTab("Historial", null, panelHistorial, null);
				panelHistorial.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Nombre:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel.setBounds(10, 20, 65, 23);
				panelHistorial.add(lblNewLabel);
				
				txtNombreH = new JTextField();
				txtNombreH.setEditable(false);
				txtNombreH.setBounds(65, 20, 332, 23);
				panelHistorial.add(txtNombreH);
				txtNombreH.setColumns(10);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Historial Clinico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 50, 647, 188);
				panelHistorial.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_2 = new JScrollPane();
				panel.add(scrollPane_2, BorderLayout.CENTER);
				
				tableHistorial = new JTable();
				tableHistorial.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int aux = tableHistorial.getSelectedRow();
						if(aux != -1 && selectedPaciente != null) {
							Consulta selectedConsultaH = selectedPaciente.buscarConsulta((String) tableHistorial.getValueAt(aux, 0));
							if(selectedConsultaH != null) {
								txtDiagHistorial.setText(selectedConsultaH.getDiagnostico());
								txtSintHistorial.setText(selectedConsultaH.getSintomas());
							}
						}
						else {
							txtDiagHistorial.setText("");
							txtSintHistorial.setText("");
						}
					}
				});
				tableHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] headersHistorial = {"Codigo","Fecha Consulta","Enfermedad"};
				modelHistorial = new DefaultTableModel();
				tableHistorial.setModel(modelHistorial);
				modelHistorial.setColumnIdentifiers(headersHistorial);
				scrollPane_2.setViewportView(tableHistorial);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sintomas:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(10, 350, 647, 100);
				panelHistorial.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_3 = new JScrollPane();
				panel_1.add(scrollPane_3, BorderLayout.CENTER);
				
				txtSintHistorial = new JTextArea();
				txtSintHistorial.setLineWrap(true);
				scrollPane_3.setViewportView(txtSintHistorial);
				
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Diagnostico:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_3.setBounds(10, 244, 647, 100);
				panelHistorial.add(panel_3);
				panel_3.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_4 = new JScrollPane();
				panel_3.add(scrollPane_4, BorderLayout.CENTER);
				
				txtDiagHistorial = new JTextArea();
				scrollPane_4.setViewportView(txtDiagHistorial);
				tabbedPane.setEnabledAt(1, false);
			}
			{
				JPanel panelConsultas = new JPanel();
				panelConsultas.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				tabbedPane.addTab("Consultas", null, panelConsultas, null);
				panelConsultas.setLayout(null);
				
				txtNombreC = new JTextField();
				txtNombreC.setEditable(false);
				txtNombreC.setColumns(10);
				txtNombreC.setBounds(65, 20, 332, 23);
				panelConsultas.add(txtNombreC);
				
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				label.setBounds(10, 20, 65, 23);
				panelConsultas.add(label);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(10, 50, 647, 188);
				panelConsultas.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_2 = new JScrollPane();
				panel.add(scrollPane_2, BorderLayout.CENTER);
				
				tableConsulta.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int auxConsulta = tableConsulta.getSelectedRow();
						if(auxConsulta != -1) {
							selectedConsulta = selectedPaciente.buscarConsulta(tableConsulta.getValueAt(auxConsulta, 0).toString());
							if(selectedConsulta != null) {
								btnModificar.setEnabled(true);
								btnAgregarHistorial.setEnabled(true);
								txtSintConsul.setText(selectedConsulta.getSintomas());
								txtDiagConsulta.setText(selectedConsulta.getDiagnostico());
							}
						}
						else {
							btnModificar.setEnabled(false);
							btnAgregarHistorial.setEnabled(false);
							txtSintConsul.setText("");
							txtDiagConsulta.setText("");
						}
					}
				});
				String[] headersConsultas = {"Codigo","Fecha Consulta","Enfermedad"};
				modelConsultas = new DefaultTableModel();
				tableConsulta.setModel(modelConsultas);
				modelConsultas.setColumnIdentifiers(headersConsultas);
				scrollPane_2.setViewportView(tableConsulta);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Diagnostico:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(10, 244, 647, 100);
				panelConsultas.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_3 = new JScrollPane();
				panel_1.add(scrollPane_3, BorderLayout.CENTER);
				
				txtDiagConsulta = new JTextArea();
				scrollPane_3.setViewportView(txtDiagConsulta);
				
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sintomas:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_3.setBounds(10, 350, 647, 100);
				panelConsultas.add(panel_3);
				panel_3.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_4 = new JScrollPane();
				panel_3.add(scrollPane_4, BorderLayout.CENTER);
				
				txtSintConsul = new JTextArea();
				scrollPane_4.setViewportView(txtSintConsul);
				tabbedPane.setEnabledAt(2, false);
			}
			{
				JPanel panelVacunas = new JPanel();
				panelVacunas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				tabbedPane.addTab("Vacunas", null, panelVacunas, null);
				panelVacunas.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(10, 50, 647, 294);
				panelVacunas.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_2 = new JScrollPane();
				panel.add(scrollPane_2, BorderLayout.CENTER);
				
				tableVacunas = new JTable();
				tableVacunas.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int aux = tableVacunas.getSelectedRow();
						if(aux != -1 && selectedPaciente != null) {
							Vacuna selectedVacuna = Clinica.getInstance().buscarVacuna(tableVacunas.getValueAt(aux, 0).toString());
							if(selectedVacuna != null) {
								txtEfectos.setText(selectedVacuna.getEfectos());
							}
						}
						else {
							txtEfectos.setText("");
						}
					}
				});
				tableVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] headersVacunas = {"Codigo","Nombre","Fabricante","Contra","Dosis"};
				modelVacunas = new DefaultTableModel();
				tableVacunas.setModel(modelVacunas);
				modelVacunas.setColumnIdentifiers(headersVacunas);
				scrollPane_2.setViewportView(tableVacunas);
				
				txtNombreV = new JTextField();
				txtNombreV.setEditable(false);
				txtNombreV.setColumns(10);
				txtNombreV.setBounds(65, 20, 332, 23);
				panelVacunas.add(txtNombreV);
				
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				label.setBounds(10, 20, 65, 23);
				panelVacunas.add(label);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Efectos Secundarios:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(10, 350, 647, 100);
				panelVacunas.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_3 = new JScrollPane();
				panel_1.add(scrollPane_3, BorderLayout.CENTER);
				
				txtEfectos = new JTextArea();
				scrollPane_3.setViewportView(txtEfectos);
				tabbedPane.setEnabledAt(3, false);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				if(Clinica.getLoginUser() instanceof Medico) {
					btnModificar.setVisible(true);
				}
				else {
					btnModificar.setVisible(false);
				}
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(tabbedPane.getSelectedIndex() == 0 && selectedPaciente != null) {
							RegPaciente modPaciente = new RegPaciente(selectedPaciente);
							setAlwaysOnTop(false);
							modPaciente.setVisible(true);
							setAlwaysOnTop(true);
							loadPacientes();
							loadHistorial(null);
							loadVacunas(null);
							loadConsultas(null);
							btnModificar.setEnabled(false);
						}
						else if(tabbedPane.getSelectedIndex() == 2 && selectedPaciente != null && selectedConsulta != null) {
							RegConsulta modConsulta = new RegConsulta(selectedConsulta, selectedPaciente);
							setAlwaysOnTop(false);
							modConsulta.setVisible(true);
							setAlwaysOnTop(true);
							loadConsultas(selectedPaciente);
							loadHistorial(selectedPaciente);
							btnModificar.setEnabled(false);
						}
					}
				});
				
				btnAgregarHistorial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selectedConsulta != null) {
							boolean consul = Clinica.getInstance().buscarConsultaHistorial(selectedPaciente, selectedConsulta.getCodConsulta());;
							if(!consul) {
								Clinica.getInstance().insertarConsultaHistorial(selectedConsulta, selectedPaciente);
								JOptionPane.showMessageDialog(null, "Agregacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Ya existe esta consulta en el Historial", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						loadHistorial(selectedPaciente);
					}
				});
				btnAgregarHistorial.setVisible(false);
				
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 if(tabbedPane.getSelectedIndex() == 2 && selectedPaciente != null) {
							RegConsulta regConsulta = new RegConsulta(null, selectedPaciente);
							setAlwaysOnTop(false);
							regConsulta.setVisible(true);
							setAlwaysOnTop(true);
							loadConsultas(selectedPaciente);
						 }
						 else if(tabbedPane.getSelectedIndex() == 3 && selectedPaciente != null) {
							ListVacunas agregarVacuna = new ListVacunas();
							setAlwaysOnTop(false);
							agregarVacuna.setVisible(true);
							Vacuna vac = agregarVacuna.getVaccineAgregar();
							if(vac != null) {
								boolean vaccine = Clinica.getInstance().buscarVacunaPaciente(selectedPaciente, vac.getCodigo());
								if(!vaccine) {
									Clinica.getInstance().insertarVacunaPaciente(vac, selectedPaciente);
									JOptionPane.showMessageDialog(null, "Agregacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								}
								else {
									JOptionPane.showMessageDialog(null, "El paciente ya tiene esta vacuna", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
							setAlwaysOnTop(true);
							loadVacunas(selectedPaciente);
						 }
					}
				});
				btnAgregar.setVisible(false);
				btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				buttonPane.add(btnAgregar);
				btnAgregarHistorial.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnAgregarHistorial.setEnabled(false);
				btnAgregarHistorial.setActionCommand("OK");
				buttonPane.add(btnAgregarHistorial);
				btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPacientes();
	}
	
	public void loadPacientes() {
		modelListado.setRowCount(0);
		rowsPacientes = new Object[modelListado.getColumnCount()];
		for (Paciente paci : Clinica.getInstance().getMisPacientes()){
			rowsPacientes[0] = paci.getCedula();
			rowsPacientes[1] = paci.getNombre();
			rowsPacientes[2] = paci.getFechaNacimiento();
			rowsPacientes[3] = paci.getTelefono();
			rowsPacientes[4] = paci.getTipoSangre();
			modelListado.addRow(rowsPacientes);
		}
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		txtAlergias.setText("");
	}
	
	public void loadHistorial(Paciente paci) {
		modelHistorial.setRowCount(0);
		rowsHistorial = new Object[modelHistorial.getColumnCount()];
		if(paci != null) {
			for (Consulta consul : paci.getHistoria().getMisConsultas()){
				rowsHistorial[0] = consul.getCodConsulta();
				rowsHistorial[1] = consul.getFechaConsulta();
				rowsHistorial[2] = consul.getEnfermedad().getNombre();
				modelHistorial.addRow(rowsHistorial);
			}
		}
		else {
			modelHistorial.setRowCount(0);
		}
		txtDiagConsulta.setText("");
		txtSintConsul.setText("");
	}
	
	public void loadConsultas(Paciente paci) {
		modelConsultas.setRowCount(0);
		rowsConsultas = new Object[modelConsultas.getColumnCount()];
		if(paci != null) {
			for (Consulta consul : paci.getMisConsultas()){
				rowsConsultas[0] = consul.getCodConsulta();
				rowsConsultas[1] = consul.getFechaConsulta();
				rowsConsultas[2] = consul.getEnfermedad().getNombre();
				modelConsultas.addRow(rowsConsultas);
			}
		}
		else {
			modelConsultas.setRowCount(0);
		}
		btnAgregarHistorial.setEnabled(false);
		txtDiagHistorial.setText("");
		txtSintHistorial.setText("");
	}
	
	public void loadVacunas(Paciente paci) {
		modelVacunas.setRowCount(0);
		rowsVacunas = new Object[modelVacunas.getColumnCount()];
		//String[] headersVacunas = {"Codigo","Nombre","Fabricante","Contra","Dosis"};
		if(paci != null) {
			for (Vacuna vacu : paci.getMisVacunas()){
				rowsVacunas[0] = vacu.getCodigo();
				rowsVacunas[1] = vacu.getNombre();
				rowsVacunas[2] = vacu.getFabricante();
				rowsVacunas[3] = vacu.getEnContraDe();
				rowsVacunas[4] = vacu.getDosis();
				modelVacunas.addRow(rowsVacunas);
			}
		}
		else {
			modelVacunas.setRowCount(0);
		}
		txtEfectos.setText("");
	}
	
	private void filter(String query){
		TableRowSorter<DefaultTableModel> tsr = new TableRowSorter<DefaultTableModel>(modelListado);
		tableListado.setRowSorter(tsr);
		
		tsr.setRowFilter(RowFilter.regexFilter("(?i).*"+query, cbxFiltrarPor.getSelectedIndex()));
	}
}

