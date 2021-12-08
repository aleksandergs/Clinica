package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logic.Administrador;
import logic.CitaMedica;
import logic.Clinica;
import logic.Medico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListCitaMedica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] rows;
	CitaMedica selected = null;
	private JButton btnModificar;
	private JComboBox cbxFiltro;
	private JTextField txtFiltro;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListCitaMedica dialog = new ListCitaMedica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListCitaMedica() {
		setTitle("Listado de Citas Medicas");
		setModal(true);
		setBounds(100, 100, 920, 720);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(6, 50, 882, 578);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux = table.getSelectedRow();
							if(aux != -1) {
								String codigo = (String) table.getValueAt(aux, 0);
								selected = Clinica.getInstance().buscarCita(codigo);
								if((Clinica.getLoginUser() instanceof Administrador) && ((Administrador)Clinica.getLoginUser()).getPuestoLaboral().equalsIgnoreCase("Secretario") || Clinica.getLoginUser() instanceof Medico)
									btnModificar.setEnabled(true);
									btnEliminar.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					String[] columnCM1 = { "Codigo", "Nombre", "Fecha", "Telefono", "Medico"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnCM1);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			
			JLabel lblBuscar = new JLabel("Buscar por:");
			lblBuscar.setBounds(10, 24, 68, 14);
			panel.add(lblBuscar);
			
			cbxFiltro = new JComboBox();
			cbxFiltro.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Nombre", "Fecha", "Telefono", "Medico"}));
			cbxFiltro.setBounds(82, 20, 82, 24);
			panel.add(cbxFiltro);
			
			txtFiltro = new JTextField();
			txtFiltro.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String filter = txtFiltro.getText();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					TableRowSorter<DefaultTableModel> tsr = new TableRowSorter<DefaultTableModel>(model);
					table.setRowSorter(tsr);
					
					tsr.setRowFilter(RowFilter.regexFilter("(?i).*"+filter, cbxFiltro.getSelectedIndex()));
				}
			});
			txtFiltro.setBounds(174, 21, 196, 20);
			panel.add(txtFiltro);
			txtFiltro.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("");
				if(Clinica.getLoginUser() instanceof Medico) {
					btnModificar.setText("Buscar Paciente");
				}
				else {
					btnModificar.setText("Modificar");
				}
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Clinica.getLoginUser() instanceof Administrador)
						{
							RegCita dialog = new RegCita(selected);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							llenarTabla();
							btnModificar.setEnabled(false);
						}
						else {
							
							ListPaciente dialog = new ListPaciente(selected.getNombrePersona());
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							llenarTabla();
							btnModificar.setEnabled(false);
						}
					}
				});
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.setEnabled(false);
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(selected != null) {
								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog (null, "¿Esta seguro que desea eliminar la cita medica "+selected.getCodigo()+" ?","Confirmar",dialogButton);
								if(dialogResult == JOptionPane.YES_OPTION){
									Clinica.getInstance().deleteCita(selected.getCodigo());
									JOptionPane.showMessageDialog(null, "Eliminacion Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
									btnEliminar.setEnabled(false);
									llenarTabla();
								}
							}
						}
					});
					buttonPane.add(btnEliminar);
				}
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		llenarTabla();
	}
	
	private void llenarTabla()
	{
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		if(Clinica.getLoginUser() instanceof Medico)
		{
			ArrayList<CitaMedica> misCitas = Clinica.getInstance().GetCitasByMedico();
			for (CitaMedica citas : misCitas)
			{
					rows[0] = citas.getCodigo();
					rows[1] = citas.getNombrePersona();	
					rows[2] = citas.getFecha();
					rows[3] = citas.getNumeroPersona();
					rows[4] = citas.getMedico().getCodigoUsuario() + ":" +citas.getMedico().getNombre();
					model.addRow(rows);
			}
		}
		else
		{
			for (CitaMedica citas : Clinica.getInstance().getMisCitas())
			{
				rows[0] = citas.getCodigo();
				rows[1] = citas.getNombrePersona();	
				rows[2] = citas.getFecha();
				rows[3] = citas.getNumeroPersona();
				rows[4] = citas.getMedico().getCodigoUsuario() + ":" +citas.getMedico().getNombre();
				model.addRow(rows);
			}
		}
		btnModificar.setEnabled(false);
	}
}

