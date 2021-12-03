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

import logic.Administrador;
import logic.CitaMedica;
import logic.Clinica;
import logic.Medico;
import logic.Usuario;
import logic.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListCitaMedica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String[][] tableData = {};
	CitaMedica selected = null;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListCitaMedica dialog = new ListCitaMedica(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListCitaMedica(Usuario usuario) {
		setTitle("Listado de Citas Medicas");
		setModal(true);
		setBounds(100, 100, 920, 720);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		String[] columnCM0 = { "Codigo", "Fecha", "Nombre", "Telefono"};
		String[] columnCM1 = { "Codigo", "Fecha", "Nombre", "Telefono", "Medico"};
		llenarTabla(usuario);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
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
								selected = Clinica.getInstance().buscarCita(codigo);
								if((usuario instanceof Administrador) && ((Administrador)usuario).getPuestoLaboral().equalsIgnoreCase("Secretario"))
									btnModificar.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					if(usuario instanceof Medico)
						table.setModel(new DefaultTableModel(tableData, columnCM0));
					else
						table.setModel(new DefaultTableModel(tableData, columnCM1));
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegCita dialog = new RegCita(selected);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						llenarTabla(usuario);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						if(usuario instanceof Medico)
							model.setDataVector(tableData, columnCM0);
						else
							model.setDataVector(tableData, columnCM1);
					}
				});
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
	}
	
	private void llenarTabla(Usuario usuario)
	{
		if(usuario instanceof Medico)
		{
			ArrayList<CitaMedica> citasMedicas = Clinica.getInstance().GetCitasByMedico((Medico)usuario);
			tableData = new String[citasMedicas.size()][4];
			int citas = 0;
			for (CitaMedica c : citasMedicas)
			{
				tableData[citas][0] = c.getCodigo();
				tableData[citas][1] = c.getFecha();	
				tableData[citas][2] = c.getNombrePersona();
				tableData[citas][3] = c.getNumeroPersona();
				citas++;
			}
		}
		else
		{
			int cantCitas = Clinica.getInstance().getMisCitas().size();
			tableData = new String[cantCitas][5];
			int citas = 0;
			for (CitaMedica c : Clinica.getInstance().getMisCitas())
			{
				tableData[citas][0] = c.getCodigo();
				tableData[citas][1] = c.getFecha();	
				tableData[citas][2] = c.getNombrePersona();
				tableData[citas][3] = c.getNumeroPersona();
				tableData[citas][4] = c.getMedico().getCodigoUsuario() + ":" +c.getMedico().getNombre();
				citas++;
			}
		}
	}

}
