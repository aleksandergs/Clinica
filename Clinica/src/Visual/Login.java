package Visual;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JOptionPane;
import logic.Administrador;
import logic.Clinica;
import logic.Medico;
import logic.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JLabel lblNewLabel_1;
	private JLabel jlabelImagen;
	private JPasswordField txtPassword;
	private JTextField txtUser;
	private JButton btnLimpiar;
	private JLabel lblContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Hospital PUCMM");
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width/2, dim.height/2);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_1 = new JLabel("     Hospital PUCMM");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds((dim.width/4)-125, 5, 250, 29);
		contentPane.add(lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds((dim.width/4)-100, 187, 200, 23);
		txtPassword.setText("1234");
		contentPane.add(txtPassword);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesion: ");
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblIniciarSesion.setBackground(Color.WHITE);
		lblIniciarSesion.setBounds((dim.width/4)-100, 88, 200, 29);
		contentPane.add(lblIniciarSesion);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds((dim.width/4)-155, 129, 55, 23);
		contentPane.add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUser.setBounds((dim.width/4)-100, 129, 200, 23);
		txtUser.setText("Admin");
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		lblContrasena = new JLabel("Contrasena:");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasena.setBounds((dim.width/4)-175, 187, 80, 23);
		contentPane.add(lblContrasena);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(txtPassword.getPassword());
				Usuario user = Clinica.getInstance().validarUser(txtUser.getText(), password);
				if(txtUser.getText().equals("Admin") && password.equals("1234")) {
					dispose();
					PrincipalAdministrador admin = new PrincipalAdministrador();
					admin.setVisible(true);
				}
				else if(user != null) {
					if(user instanceof Administrador) {
						PrincipalAdministrador admin = new PrincipalAdministrador();
						admin.setVisible(true);
					}
					else if(user instanceof Medico) {
						//PrincipalDoctor doc = new PrincipalAdmin();
						//doc.setVisible(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds((dim.width/4)+20, 234, 80, 23);
		contentPane.add(btnNewButton);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUser.setText("");
				txtPassword.setText("");
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBounds((dim.width/4)-100, 234, 80, 23);
		contentPane.add(btnLimpiar);
		
		jlabelImagen = new JLabel("New label");
		jlabelImagen.setBounds(0, 0, dim.width/2, dim.height/2);
		ImageIcon fondo = new ImageIcon("src/Recursos/hospital1.jpg");
		Icon icono = new ImageIcon(fondo.getImage().getScaledInstance(jlabelImagen.getWidth(), jlabelImagen.getHeight(), Image.SCALE_DEFAULT));
		jlabelImagen.setIcon(icono);
		contentPane.add(jlabelImagen);
	}
}
