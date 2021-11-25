package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import logic.Administrador;
import logic.Clinica;
import logic.Enfermedad;
import logic.Medico;
import logic.Usuario;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String[][] tableData = {};
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	Enfermedad selected = null;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListEnfermedades dialog = new ListEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListEnfermedades() {
		setTitle("Listado de Enfermedades");
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 700, 618);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		String[] columnEnf = { "Codigo", "Nombre", "Tipo"};
		llenarTabla();
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Administradores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 10, 668, 424);
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
						String codigo = (String) table.getValueAt(aux, 0);
						selected = Clinica.getInstance().buscarEnfermedad(codigo);
						textArea.setText(selected.getDiagnostico());
					}
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(new DefaultTableModel(tableData, columnEnf));
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			scrollPane.setViewportView(table);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Diagnostico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 442, 668, 96);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		panel_1.add(textArea, BorderLayout.CENTER);
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
				JButton btnCancelar = new JButton("Cancelar");
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
		int cantEnf = Clinica.getInstance().getMisEnfermedades().size();
		tableData = new String[cantEnf][4];
		int enf = 0;
		//{ "Codigo", "Nombre", "Tipo", "Diagnostico" }
		for (Enfermedad e : Clinica.getInstance().getMisEnfermedades()) 
		{
			tableData[enf][0] = e.getCodigo(); 
			tableData[enf][1] = e.getNombre();	
			tableData[enf][2] = e.getTipo();
			tableData[enf][3] = e.getDiagnostico();
			enf++;

		}
	}
}
