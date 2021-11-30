package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logic.Clinica;
import logic.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class ListVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String[][] tableData = {};
	Vacuna selected = null;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListVacunas dialog = new ListVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListVacunas() {
		setTitle("Listado de vacunas");
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 868, 686);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		String[] columnVac = { "Codigo", "Nombre", "dosis", "Contra", "Fabricante"};
		llenarTabla();
		contentPanel.setLayout(null);
		{
			panel = new JPanel();
			panel.setBounds(5, 5, 842, 488);
			panel.setBorder(new TitledBorder(null, "Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux = table.getSelectedRow();
							if(aux != -1) {
								String codigo = (String) table.getValueAt(aux, 0);
								selected = Clinica.getInstance().buscarVacuna(codigo);
								textArea.setText(selected.getEfectos());
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(new DefaultTableModel(tableData, columnVac));
					scrollPane.setViewportView(table);
				}
			}
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Efectos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 504, 842, 96);
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
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void llenarTabla()
	{
		int cantVac = Clinica.getInstance().getMisVacunas().size();
		tableData = new String[cantVac][5];
		int vac = 0;
		//{ "Codigo", "Nombre", "dosis", "Contra", "Fabricante"}
		for (Vacuna v : Clinica.getInstance().getMisVacunas())
		{
			tableData[vac][0] = v.getCodigo();
			tableData[vac][1] = v.getNombre();	
			tableData[vac][2] = Integer.toString(v.getDosis());
			tableData[vac][3] = v.getEnContraDe();
			tableData[vac][4] = v.getFabricante();
			vac++;

		}
	}
}
