package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import logic.Clinica;
import logic.Enfermedad;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextArea txtDiagnostico = new JTextArea();
	private JLabel lblAvisoNombre = new JLabel();
	private JLabel lblAvisoTipo = new JLabel();
	private JComboBox cbxTipo = new JComboBox<>();
	private JButton btnRegistrar = new JButton();
	private Enfermedad updated = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedades dialog = new RegEnfermedades(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedades(Enfermedad enfermedad) {
		updated = enfermedad;
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 480, 309);
		if(updated == null){
			setTitle("Registrar Enfermedad");
		}else {
			setTitle("Modificar Enfermedad");
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
			{
				JLabel label = new JLabel("Id:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				label.setBounds(10, 30, 40, 23);
				panel.add(label);
			}
			{
				txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtId.setEditable(false);
				txtId.setColumns(10);
				txtId.setBounds(85, 30, 178, 23);
				if(updated != null) {
					txtId.setText(updated.getCodigo());
				}
				else {
					txtId.setText("E-"+Clinica.getInstance().getGeneradorCodigoEnfermedad());
				}
				panel.add(txtId);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				label.setBounds(10, 60, 53, 23);
				panel.add(label);
			}
			{
				txtNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						validarCamposVacios();
						habilitarBoton();
					}
				});
				txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtNombre.setColumns(10);
				txtNombre.setBounds(85, 60, 357, 23);
				if(updated != null) {
					txtNombre.setText(updated.getNombre());
				}
				panel.add(txtNombre);
			}
			{
				JLabel lblTipo = new JLabel("Tipo:");
				lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblTipo.setBounds(10, 90, 53, 23);
				panel.add(lblTipo);
			}
			{
				cbxTipo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						validarCamposVacios();
						habilitarBoton();
					}
				});
				cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Enfermedad oncol\u00F3gica", "Enfermedad infecciosa y parasitaria", "Enfermedad de la sangre", "Enfermedad del sistema inmunitario", "Enfermedad endocrina", "Trastorno mental, del comportamiento y del desarrollo", "Enfermedad del sistema nervioso", "Enfermedad oftalmol\u00F3gica y de la visi\u00F3n", "Enfermedad auditiva", "Enfermedad cardiovascular", "Enfermedad respiratoria", "Enfermedad del sistema digestivo", "Enfermedad de la piel", "Enfermedad del aparato genitourinario", "Enfermedad cong\u00E9nita y alteracion cromos\u00F3mica"}));
				cbxTipo.setBounds(85, 90, 357, 23);
				if(updated != null) {
					cbxTipo.setSelectedItem(updated.getTipo());
				}
				panel.add(cbxTipo);
			}
			{
				JLabel lblDiagnostico = new JLabel("Diagnostico:");
				lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblDiagnostico.setBounds(10, 120, 64, 23);
				panel.add(lblDiagnostico);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(85, 126, 357, 70);
			panel.add(scrollPane);
			txtDiagnostico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					habilitarBoton();
					validarCamposVacios();
				}
			});
			
			txtDiagnostico.setLineWrap(true);
			scrollPane.setViewportView(txtDiagnostico);
			if(updated != null) {
				txtDiagnostico.setText(updated.getDiagnostico());
			}
			{
				lblAvisoNombre.setForeground(Color.RED);
				lblAvisoNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblAvisoNombre.setBounds(446, 60, 18, 14);
				panel.add(lblAvisoNombre);
			}
			{
				lblAvisoTipo.setForeground(Color.RED);
				lblAvisoTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblAvisoTipo.setBounds(446, 90, 18, 14);
				panel.add(lblAvisoTipo);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				if(updated == null){
					btnRegistrar.setText("Registrar");
				}else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.setEnabled(false);
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(updated == null) {
							Enfermedad disease = null;
							disease = new Enfermedad(txtId.getText(), txtNombre.getText(), cbxTipo.getSelectedItem().toString(), txtDiagnostico.getText());
							Clinica.getInstance().insertarEnfermedad(disease);
							setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							setAlwaysOnTop(true);
							btnRegistrar.setEnabled(false);
							clean();
							habilitarBoton();
							validarCamposVacios();
						}
						else {
							updated.setNombre(txtNombre.getText());
							updated.setTipo(cbxTipo.getSelectedItem().toString());
							updated.setDiagnostico(txtDiagnostico.getText());
							Clinica.getInstance().modificarEnfermedad(updated);
							setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Modificacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							setAlwaysOnTop(true);
							dispose();
						}
					}
				});
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void clean() {
		txtId.setText("E-"+Clinica.getInstance().getGeneradorCodigoEnfermedad());
		txtNombre.setText("");
		cbxTipo.setSelectedIndex(0);
		txtDiagnostico.setText("");
	}
	
	private void habilitarBoton() {
		if(txtNombre.getText().isEmpty() || cbxTipo.getSelectedIndex() == 0){
			btnRegistrar.setEnabled(false);
		}
		else {
			btnRegistrar.setEnabled(true);
		}
	}
	
	private void validarCamposVacios() {
		if(txtNombre.getText().isEmpty()) {
			lblAvisoNombre.setText("*");
		} else {
			lblAvisoNombre.setText("");
		}
		
		if(cbxTipo.getSelectedIndex() == 0) {
			lblAvisoTipo.setText("*");
		} else {
			lblAvisoTipo.setText("");
		}
	}
}

