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
import logic.Medico;
import logic.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] rows;
	private Vacuna selected = null;
	private Vacuna vaccineAgregar = null;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JTextArea textArea;
	private JButton btnModificar;

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
								btnModificar.setEnabled(true);
							}
							else {
								btnModificar.setEnabled(false);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					String[] columnVac = { "Codigo", "Nombre", "dosis", "Contra", "Fabricante"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnVac);
					table.setModel(model);
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
				btnModificar = new JButton("");
				if(Clinica.getLoginUser() instanceof Medico) {
					btnModificar.setText("Agregar");
				}
				else {
					btnModificar.setText("Modificar");
				}
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(Clinica.getLoginUser() instanceof Medico) {
							vaccineAgregar = selected;
							dispose();
						}
						else {
							RegVacunas modVacunas = new RegVacunas(selected);
							setAlwaysOnTop(false);
							modVacunas.setVisible(true);
							setAlwaysOnTop(true);
							llenarTabla();
							btnModificar.setEnabled(false);
						}
					}
				});
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		llenarTabla();
	}
	
	private void llenarTabla()
	{
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		//{ "Codigo", "Nombre", "dosis", "Contra", "Fabricante"}
		for (Vacuna v : Clinica.getInstance().getMisVacunas())
		{
			rows[0] = v.getCodigo();
			rows[1] = v.getNombre();	
			rows[2] = Integer.toString(v.getDosis());
			rows[3] = v.getEnContraDe();
			model.addRow(rows);
		}
		textArea.setText("");
	}
	
	public Vacuna getVaccineAgregar() {
		return vaccineAgregar;
	}
}