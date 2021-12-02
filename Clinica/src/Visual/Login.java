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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel jlabelImagen;
	private JPasswordField txtPassword;
	private JTextField txtUser;
	private JButton btnLimpiar;
	private JLabel lblContrasena;
	private JCheckBox chckbxPasswordVisible;
	private JLabel PasswordIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica;
				FileOutputStream clinica2;
				ObjectInputStream clinicaRead;
				ObjectOutputStream clinicaWrite;
				try {
					clinica = new FileInputStream ("clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica temp = (Clinica)clinicaRead.readObject();
					Clinica.setClinica(temp);
					clinica.close();
					clinicaRead.close();
				} catch (FileNotFoundException e) {
					try {
						clinica2 = new  FileOutputStream("clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinica2);
						Usuario aux = new Administrador("U-"+Clinica.getInstance().getGeneradorCodigoUserA(), "Admin", "402-3113000-2", "1234", "Ramon Del Villar", "809-546-8976", "Administrador");
						Clinica.getInstance().insertarUsuario(aux);;
						clinicaWrite.writeObject(Clinica.getInstance());
						clinica2.close();
						clinicaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("     Hospital PUCMM");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds((dim.width/4)-125, 5, 250, 29);
		panel.add(lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds((dim.width/4)-100, 187, 200, 23);
		panel.add(txtPassword);
		
		chckbxPasswordVisible = new JCheckBox("");
		chckbxPasswordVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxPasswordVisible.isSelected()) {
					txtPassword.setEchoChar((char)0);
				}
				else {
					txtPassword.setEchoChar('*');
				}
			}
		});
		chckbxPasswordVisible.setForeground(SystemColor.desktop);
		chckbxPasswordVisible.setOpaque(false);
		chckbxPasswordVisible.setBounds((dim.width/4)+100, 180, 60, 37);
		panel.add(chckbxPasswordVisible);
		
		PasswordIcon = new JLabel("");
		PasswordIcon.setBounds((dim.width/4)+120, 187, 30, 23);
		ImageIcon back = new ImageIcon("src/Recursos/ShowPass.png");
		Icon icon = new ImageIcon(back.getImage().getScaledInstance(PasswordIcon.getWidth(), PasswordIcon.getHeight(), Image.SCALE_DEFAULT));
		PasswordIcon.setIcon(icon);
		panel.add(PasswordIcon);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesion: ");
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblIniciarSesion.setBackground(Color.WHITE);
		lblIniciarSesion.setBounds((dim.width/4)-100, 88, 200, 29);
		panel.add(lblIniciarSesion);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds((dim.width/4)-155, 129, 55, 23);
		panel.add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUser.setBounds((dim.width/4)-100, 129, 200, 23);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		lblContrasena = new JLabel("Contrasena:");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasena.setBounds((dim.width/4)-180, 187, 80, 23);
		panel.add(lblContrasena);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(txtPassword.getPassword());
				boolean confirm = Clinica.getInstance().confirmLogin(txtUser.getText(), password);
				if(confirm) {
					if(Clinica.getLoginUser() instanceof Administrador) {
						PrincipalAdministrador admin = new PrincipalAdministrador();
						dispose();
						admin.setVisible(true);
					}
					else if(Clinica.getLoginUser() instanceof Medico) {
						PrincipalAdministrador doc = new PrincipalAdministrador();
						dispose();
						doc.setVisible(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds((dim.width/4)+20, 234, 80, 23);
		panel.add(btnNewButton);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUser.setText("");
				txtPassword.setText("");
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBounds((dim.width/4)-100, 234, 80, 23);
		panel.add(btnLimpiar);
		
		jlabelImagen = new JLabel("New label");
		jlabelImagen.setBounds(0, 0, dim.width/2, dim.height/2);
		ImageIcon fondo = new ImageIcon("src/Recursos/hospital1.jpg");
		Icon icono = new ImageIcon(fondo.getImage().getScaledInstance(jlabelImagen.getWidth(), jlabelImagen.getHeight(), Image.SCALE_DEFAULT));
		jlabelImagen.setIcon(icono);
		panel.add(jlabelImagen);
	}
}
