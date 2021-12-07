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
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logic.Clinica;
import logic.Enfermedad;
import logic.Medico;
import javax.swing.JTextArea;
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

public class ListEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private Object[] rows;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	Enfermedad selected = null;
	private Enfermedad enfAgregar = null;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JButton btnModificar;
	private JTextField txtFiltro;
	private JComboBox cbxFiltro;

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
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		String[] columnEnf = { "Codigo", "Nombre", "Tipo"};
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 10, 668, 424);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 48, 656, 368);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = table.getSelectedRow();
					if(aux != -1) {
						btnModificar.setEnabled(true);
						String codigo = (String) table.getValueAt(aux, 0);
						selected = Clinica.getInstance().buscarEnfermedad(codigo);
						textArea.setText(selected.getDiagnostico());
					}
					else {
						btnModificar.setEnabled(false);
					}
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnEnf);
			table.setModel(model);
			scrollPane.setViewportView(table);
			
			JLabel lblFitro = new JLabel("Buscar por:");
			lblFitro.setBounds(6, 22, 72, 14);
			panel.add(lblFitro);
			
			cbxFiltro = new JComboBox();
			cbxFiltro.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Nombre", "Tipo"}));
			cbxFiltro.setBounds(88, 18, 82, 24);
			panel.add(cbxFiltro);
			
			txtFiltro = new JTextField();
			txtFiltro.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String filter = txtFiltro.getText();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					TableRowSorter<DefaultTableModel> tsr = new TableRowSorter<DefaultTableModel>(model);
					table.setRowSorter(tsr);
					
					tsr.setRowFilter(RowFilter.regexFilter("(?i)"+filter, cbxFiltro.getSelectedIndex()));
				}
			});
			txtFiltro.setBounds(180, 20, 196, 20);
			panel.add(txtFiltro);
			txtFiltro.setColumns(10);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Diagnostico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 442, 668, 96);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
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
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Clinica.getLoginUser() instanceof Medico) {
							enfAgregar = selected;
							dispose();
						}
						else {
							RegEnfermedades modEnfermedad = new RegEnfermedades(selected);
							setAlwaysOnTop(false);
							modEnfermedad.setVisible(true);
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
		llenarTabla();
	}
	
	private void llenarTabla()
	{
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		for (Enfermedad e : Clinica.getInstance().getMisEnfermedades()) 
		{
			rows[0] = e.getCodigo(); 
			rows[1] = e.getNombre();	
			rows[2] = e.getTipo();
			model.addRow(rows);
		}
		textArea.setText("");
	}
	
	public Enfermedad getEnfAgregar() {
		return enfAgregar;
	}
}
