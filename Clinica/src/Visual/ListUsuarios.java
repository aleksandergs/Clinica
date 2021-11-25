package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.UIManager;
import java.awt.Color;

public class ListUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private String[][] tableData = {};
	private String[][] tableData1 = {};
	private JPanel panel;
	private JPanel panel1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table1;

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
		setBounds(100, 100, 816, 618);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		String[] columnAdmin = { "Codigo", "Cedula", "Nombre", "Usuario", "Telefono", "Puesto" };
		String[] columnMedi = { "Codigo", "Cedula", "Nombre", "Usuario", "Telefono", "Especialidad", "# Consultorio" };
		llenarTabla();
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Administradores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 10, 780, 260);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(new DefaultTableModel(tableData, columnAdmin));
			scrollPane.setViewportView(table);
		}
		{
			panel1 = new JPanel();
			panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00E9dicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel1.setBounds(10, 278, 780, 260);
			contentPanel.add(panel1);
			panel1.setLayout(new BorderLayout(0, 0));
			
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel1.add(scrollPane_1, BorderLayout.CENTER);
			
			table1 = new JTable();
			table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table1.setModel(new DefaultTableModel(tableData1, columnMedi));
			scrollPane_1.setViewportView(table1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
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
	}
	
	private void llenarTabla()
	{
		int[] cantTipos = Clinica.getInstance().cantUsersType();
		tableData = new String[cantTipos[0]][6];
		tableData1 = new String[cantTipos[1]][7];
		int admins = 0, users = 0;
		//{ "Codigo", "Cedula", "Nombre", "Usuario", "Telefono", "Puesto" }
		for (Usuario u : Clinica.getInstance().getMisUsuarios()) 
		{
			if(u instanceof Administrador) {
				tableData[admins][0] = u.getCodigoUsuario(); 
				tableData[admins][1] = u.getCedulaUsuario();	
				tableData[admins][2] = u.getNombre();
				tableData[admins][3] = u.getLogin(); 
				tableData[admins][4] = u.getTelefono(); 
				tableData[admins][5] = ((Administrador) u).getPuestoLaboral();
				admins++;
			}
			else if (u instanceof Medico) {
				tableData1[users][0] = u.getCodigoUsuario(); 
				tableData1[users][1] = u.getCedulaUsuario();	
				tableData1[users][2] = u.getNombre();
				tableData1[users][3] = u.getLogin(); 
				tableData1[users][4] = u.getTelefono();
				tableData1[users][5] = ((Medico) u).getEspecialidad();
				tableData1[users][6] = Integer.toString(((Medico) u).getNumHabitacion());
				users++;
			}
		}
	}
}