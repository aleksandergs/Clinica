package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logic.Administrador;
import logic.Clinica;
import logic.Medico;
import logic.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private DefaultTableModel modelTable;
	private DefaultTableModel modelTable1;
	private Object[] rowsTable;
	private Object[] rowsTable1;
	private JPanel panel;
	private JPanel panel1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table1;
	private Usuario selected = null;
	private JButton btnModificar;
	private JLabel lblFilter;
	private JComboBox cbxFilter;
	private JTextField txtFilter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListUsuarios dialog = new ListUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListUsuarios() {
		setTitle("Listado de Usuarios");
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 936, 720);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		String[] columnAdmin = { "Codigo", "Cedula", "Nombre", "Usuario", "Telefono", "Puesto" };
		String[] columnMedi = { "Codigo", "Cedula", "Nombre", "Usuario", "Telefono", "Especialidad", "# Consultorio" };
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Administradores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 42, 900, 292);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = table.getSelectedRow();
					if(aux != -1) {
						table1.getSelectionModel().clearSelection();
						btnModificar.setEnabled(true);
						String codigo = (String) table.getValueAt(aux, 0);
						selected = Clinica.getInstance().buscarUsuario(codigo);
					}
					else {
						btnModificar.setEnabled(false);
					}
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelTable = new DefaultTableModel();
			modelTable.setColumnIdentifiers(columnAdmin);
			table.setModel(modelTable);
			scrollPane.setViewportView(table);
		}
		{
			panel1 = new JPanel();
			panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00E9dicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel1.setBounds(10, 342, 900, 292);
			contentPanel.add(panel1);
			panel1.setLayout(new BorderLayout(0, 0));
			
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setSize(900, 237);
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel1.add(scrollPane_1, BorderLayout.CENTER);
			
			table1 = new JTable();
			table1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = table1.getSelectedRow();
					if(aux != -1) {
						table.getSelectionModel().clearSelection();
						btnModificar.setEnabled(true);
						String codigo = (String) table1.getValueAt(aux, 0);
						selected = Clinica.getInstance().buscarUsuario(codigo);
					}
					else {
						btnModificar.setEnabled(false);
					}
				}
			});
			table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelTable1 = new DefaultTableModel();
			modelTable1.setColumnIdentifiers(columnMedi);
			table1.setModel(modelTable1);
			scrollPane_1.setViewportView(table1);
		}
		
		lblFilter = new JLabel("Buscar por:");
		lblFilter.setBounds(10, 17, 82, 14);
		contentPanel.add(lblFilter);
		
		cbxFilter = new JComboBox();
		cbxFilter.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Cedula", "Nombre"}));
		cbxFilter.setBounds(80, 12, 96, 24);
		contentPanel.add(cbxFilter);
		
		txtFilter = new JTextField();
		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filter = txtFilter.getText();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				TableRowSorter<DefaultTableModel> tsr = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tsr);
				tsr.setRowFilter(RowFilter.regexFilter("(?i)"+filter, cbxFilter.getSelectedIndex()));
			}
		});
		txtFilter.setBounds(186, 14, 196, 20);
		contentPanel.add(txtFilter);
		txtFilter.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected != null) {
							RegUsuario modUsuario = new RegUsuario(selected);
							setAlwaysOnTop(false);
							modUsuario.setVisible(true);
							setAlwaysOnTop(true);
							llenarTabla();
							btnModificar.setEnabled(false);
						}
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		llenarTabla();
	}
	
	private void llenarTabla()
	{
		int[] cantTipos = Clinica.getInstance().cantUsersType();
		modelTable.setRowCount(0);
		modelTable1.setRowCount(0);
		rowsTable = new Object[modelTable.getColumnCount()];
		rowsTable1 = new Object[modelTable1.getColumnCount()];
		//{ "Codigo", "Cedula", "Nombre", "Usuario", "Telefono", "Puesto" }
		for (Usuario u : Clinica.getInstance().getMisUsuarios()) 
		{
			if(u instanceof Administrador) {
				rowsTable[0] = u.getCodigoUsuario(); 
				rowsTable[1] = u.getCedulaUsuario();	
				rowsTable[2] = u.getNombre();
				rowsTable[3] = u.getLogin(); 
				rowsTable[4] = u.getTelefono(); 
				rowsTable[5] = ((Administrador) u).getPuestoLaboral();
				modelTable.addRow(rowsTable);
			}
			else if (u instanceof Medico) {
				rowsTable1[0] = u.getCodigoUsuario(); 
				rowsTable1[1] = u.getCedulaUsuario();	
				rowsTable1[2] = u.getNombre();
				rowsTable1[3] = u.getLogin(); 
				rowsTable1[4] = u.getTelefono();
				rowsTable1[5] = ((Medico) u).getEspecialidad();
				rowsTable1[6] = Integer.toString(((Medico) u).getNumHabitacion());
				modelTable1.addRow(rowsTable1);
			}
		}
	}
}