package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Administrador;
import logic.Clinica;
import logic.Medico;
import logic.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PrincipalAdministrador extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JLabel lblCantAdministradores;
	private JLabel lblCantPacientes;
	private JLabel lblCantCitas;
	private JLabel lblCantDoctores;
	private JLabel lblCantEnfermedades;
	private JLabel lblCantSecretarios;
	private JButton btnPacientes;
	private JButton btnVerCitasMedicas;
	private JButton btnVerUsuarios;
	private JButton btnVerEnfermedades;
	private JPanel panelPacientes;
	private JPanel panelCitas;
	private JPanel panelAdmin;
	private JPanel panelSecre;
	private JPanel panelDoctores;
	private JPanel panelEnfermedades;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalAdministrador frame = new PrincipalAdministrador();
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
	public PrincipalAdministrador() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream clinica2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica2 = new	FileOutputStream("clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica2);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-40);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/Recursos/logo.jpg");
		setIconImage(logo.getImage());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuUsuario = new JMenu("Usuarios");
		menuUsuario.setVisible(false);
		menuUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuUsuario);
		if(Clinica.getLoginUser() instanceof Administrador) {
			Administrador admin = (Administrador)Clinica.getLoginUser();
			if(admin.getPuestoLaboral().equalsIgnoreCase("Administrador")) {
				menuUsuario.setVisible(true);
			}
		}
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario regUser = new RegUsuario(null);
				regUser.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuUsuario.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listado");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsuarios dialog = new ListUsuarios();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuUsuario.add(mntmNewMenuItem_1);
		
		JMenu menuVacunas = new JMenu("Vacunas");
		menuVacunas.setVisible(false);
		menuVacunas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuVacunas);
		if(Clinica.getLoginUser() instanceof Administrador) {
			Administrador admin = (Administrador)Clinica.getLoginUser();
			if(admin.getPuestoLaboral().equalsIgnoreCase("Administrador")) {
				menuVacunas.setVisible(true);
			}
		}
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registrar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacunas registrarVacuna = new RegVacunas();
				registrarVacuna.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuVacunas.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListVacunas dialog = new ListVacunas();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuVacunas.add(mntmNewMenuItem_3);
		
		JMenu menuEnfermedad = new JMenu("Enfermedad");
		menuEnfermedad.setVisible(false);
		menuEnfermedad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuEnfermedad);
		if(Clinica.getLoginUser() instanceof Administrador) {
			Administrador admin = (Administrador)Clinica.getLoginUser();
			if(admin.getPuestoLaboral().equalsIgnoreCase("Administrador")) {
				menuEnfermedad.setVisible(true);
			}
		}
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedades registrarEnfermedad = new RegEnfermedades(null);
				registrarEnfermedad.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuEnfermedad.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Listado");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEnfermedades dialog = new ListEnfermedades();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuEnfermedad.add(mntmNewMenuItem_5);
		
		JMenu menuCitasMed = new JMenu("Citas Medicas");
		menuCitasMed.setVisible(false);
		menuCitasMed.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuCitasMed);
		if(Clinica.getLoginUser() instanceof Administrador) {
			Administrador admin = (Administrador)Clinica.getLoginUser();
			if(admin.getPuestoLaboral().equalsIgnoreCase("Secretario")) {
				menuCitasMed.setVisible(true);
			}
		}
		else if(Clinica.getLoginUser() instanceof Medico) {
			menuCitasMed.setVisible(true);
		}
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegCita regitrarCita = new RegCita(null);
				regitrarCita.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuCitasMed.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Listado");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCitaMedica dialog = new ListCitaMedica(Clinica.getLoginUser());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuCitasMed.add(mntmNewMenuItem_10);
		
		JMenu menuPacientes = new JMenu("Pacientes");
		menuPacientes.setVisible(false);
		menuPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuPacientes);
		if((Clinica.getLoginUser() instanceof Medico)) {
			menuPacientes.setVisible(true);
		}
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Registrar");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegPaciente registrarPaciente = new RegPaciente(null);
				registrarPaciente.setVisible(true);
			}
		});
		mntmNewMenuItem_13.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuPacientes.add(mntmNewMenuItem_13);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuPacientes.add(mntmNewMenuItem_6);
		
		JMenu menuPerfil = new JMenu("Perfil");
		menuPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuPerfil);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Ver/Editar perfil");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegUsuario modUser = new RegUsuario(Clinica.getLoginUser());
				modUser.setVisible(true);
				actualizarDashboard();
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuPerfil.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Salir");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream clinica2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica2 = new  FileOutputStream("clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica2);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Login login = new Login();
				dispose();
				login.setVisible(true);
			}
		});
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuPerfil.add(mntmNewMenuItem_9);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DashBoard", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panelSecre = new JPanel();
		panelSecre.setVisible(false);
		panelSecre.setBackground(new Color(65, 105, 225));
		panelSecre.setBounds(570, 342, 240, 145);
		panel.add(panelSecre);
		panelSecre.setLayout(null);
		
		JLabel lblSecreatrios = new JLabel("Secretarios");
		lblSecreatrios.setBackground(new Color(100, 149, 237));
		lblSecreatrios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSecreatrios.setBounds(0, 0, 240, 23);
		lblSecreatrios.setOpaque(true);
		panelSecre.add(lblSecreatrios);
		
		lblCantSecretarios = new JLabel("");
		lblCantSecretarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantSecretarios.setForeground(Color.WHITE);
		lblCantSecretarios.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCantSecretarios.setBounds(0, 57, 240, 33);
		lblCantSecretarios.setText(String.valueOf(Clinica.getInstance().cantUsersType()[1]));
		panelSecre.add(lblCantSecretarios);
		
		JLabel lblCantidadDeSecretarios = new JLabel("Cantidad de Secretarios registrados");
		lblCantidadDeSecretarios.setForeground(Color.WHITE);
		lblCantidadDeSecretarios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadDeSecretarios.setBounds(0, 122, 240, 23);
		panelSecre.add(lblCantidadDeSecretarios);
		
		JButton btnSecret = new JButton("Ver Usuarios ->");
		btnSecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsuarios dialog = new ListUsuarios();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		btnSecret.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSecret.setBounds(0, 98, 240, 23);
		panelSecre.add(btnSecret);
		
		panelAdmin = new JPanel();
		panelAdmin.setVisible(false);
		panelAdmin.setBackground(new Color(255, 140, 0));
		panelAdmin.setBounds(200, 342, 240, 145);
		panel.add(panelAdmin);
		panelAdmin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administradores");
		lblNewLabel.setBackground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 0, 240, 22);
		lblNewLabel.setOpaque(true);
		panelAdmin.add(lblNewLabel);
		
		lblCantAdministradores = new JLabel("");
		lblCantAdministradores.setForeground(Color.WHITE);
		lblCantAdministradores.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantAdministradores.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCantAdministradores.setBounds(0, 57, 240, 33);
		lblCantAdministradores.setText(String.valueOf(Clinica.getInstance().cantUsersType()[0]));
		panelAdmin.add(lblCantAdministradores);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de Administradores registrados");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(0, 122, 240, 23);
		panelAdmin.add(lblNewLabel_1);
		
		JButton btnAdmin = new JButton("Ver Usuarios ->");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsuarios dialog = new ListUsuarios();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdmin.setBounds(0, 98, 240, 23);
		panelAdmin.add(btnAdmin);
		
		panelDoctores = new JPanel();
		panelDoctores.setVisible(false);
		panelDoctores.setBackground(new Color(0, 128, 128));
		panelDoctores.setBounds(940, 100, 240, 145);
		panel.add(panelDoctores);
		panelDoctores.setLayout(null);
		
		JLabel lblDoctores = new JLabel("Doctores");
		lblDoctores.setBackground(new Color(60, 179, 113));
		lblDoctores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoctores.setBounds(0, 0, 240, 23);
		lblDoctores.setOpaque(true);
		panelDoctores.add(lblDoctores);
		
		lblCantDoctores = new JLabel("");
		lblCantDoctores.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantDoctores.setForeground(Color.WHITE);
		lblCantDoctores.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCantDoctores.setBounds(0, 57, 240, 33);
		lblCantDoctores.setText(String.valueOf(Clinica.getInstance().cantUsersType()[2]));
		panelDoctores.add(lblCantDoctores);
		
		JLabel lblCantidadDeDoctores = new JLabel("Cantidad de Doctores Registrados");
		lblCantidadDeDoctores.setForeground(Color.WHITE);
		lblCantidadDeDoctores.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadDeDoctores.setBounds(0, 122, 240, 23);
		panelDoctores.add(lblCantidadDeDoctores);
		
		btnVerUsuarios = new JButton("Ver Usuarios ->");
		btnVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsuarios dialog = new ListUsuarios();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		btnVerUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerUsuarios.setBounds(0, 98, 240, 23);
		panelDoctores.add(btnVerUsuarios);
		
		panelPacientes = new JPanel();
		panelPacientes.setVisible(false);
		panelPacientes.setBackground(new Color(70, 130, 180));
		panelPacientes.setBounds(200, 100, 240, 145);
		panel.add(panelPacientes);
		panelPacientes.setLayout(null);
		
		JLabel lblPacientes = new JLabel("Pacientes");
		lblPacientes.setBackground(new Color(95, 158, 160));
		lblPacientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPacientes.setBounds(0, 0, 240, 23);
		lblPacientes.setOpaque(true);
		panelPacientes.add(lblPacientes);
		
		lblCantPacientes = new JLabel("");
		lblCantPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantPacientes.setForeground(Color.WHITE);
		lblCantPacientes.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCantPacientes.setBounds(0, 57, 240, 33);
		lblCantPacientes.setText(String.valueOf(Clinica.getInstance().getMisPacientes().size()));
		panelPacientes.add(lblCantPacientes);
		
		JLabel lblCantidadDePacientes = new JLabel("Cantidad de Pacientes registrados");
		lblCantidadDePacientes.setForeground(Color.WHITE);
		lblCantidadDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadDePacientes.setBounds(0, 122, 240, 23);
		panelPacientes.add(lblCantidadDePacientes);
		
		btnPacientes = new JButton("Ver Pacientes ->");
		btnPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarDashboard();
			}
		});
		btnPacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPacientes.setBounds(0, 98, 240, 23);
		panelPacientes.add(btnPacientes);
		
		panelCitas = new JPanel();
		panelCitas.setVisible(false);
		panelCitas.setBackground(new Color(255, 215, 0));
		panelCitas.setBounds(570, 100, 240, 145);
		panel.add(panelCitas);
		panelCitas.setLayout(null);
		
		JLabel lblCitasMedicas = new JLabel("Citas Medicas");
		lblCitasMedicas.setBackground(new Color(218, 165, 32));
		lblCitasMedicas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCitasMedicas.setBounds(0, 0, 240, 23);
		lblCitasMedicas.setOpaque(true);
		panelCitas.add(lblCitasMedicas);
		
		lblCantCitas = new JLabel("");
		lblCantCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantCitas.setForeground(Color.BLACK);
		lblCantCitas.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCantCitas.setBounds(0, 57, 240, 33);
		if(Clinica.getLoginUser() instanceof Medico)
			lblCantCitas.setText(String.valueOf(Clinica.getInstance().GetCitasByMedico((Medico)Clinica.getLoginUser()).size()));
		else
			lblCantCitas.setText(String.valueOf(Clinica.getInstance().getMisCitas().size()));
		panelCitas.add(lblCantCitas);
		
		JLabel lblCantidadDeCitas = new JLabel("Cantidad de Citas Medicas registradas");
		lblCantidadDeCitas.setForeground(Color.BLACK);
		lblCantidadDeCitas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadDeCitas.setBounds(0, 122, 240, 23);
		panelCitas.add(lblCantidadDeCitas);
		
		btnVerCitasMedicas = new JButton("Ver Citas Medicas ->");
		btnVerCitasMedicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDashboard();
				ListCitaMedica dialog = new ListCitaMedica(Clinica.getLoginUser());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnVerCitasMedicas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerCitasMedicas.setBounds(0, 98, 240, 23);
		panelCitas.add(btnVerCitasMedicas);
		
		panelEnfermedades = new JPanel();
		panelEnfermedades.setVisible(false);
		panelEnfermedades.setBackground(new Color(186, 85, 211));
		panelEnfermedades.setBounds(940, 342, 240, 145);
		panel.add(panelEnfermedades);
		panelEnfermedades.setLayout(null);
		
		Usuario user = Clinica.getLoginUser();
		if(user instanceof Medico) {
			panelCitas.setVisible(true);
			panelPacientes.setVisible(true);
		}
		else if(user instanceof Administrador && ((Administrador) user).getPuestoLaboral().equalsIgnoreCase("Administrador")) {
			panelAdmin.setVisible(true);
			panelCitas.setVisible(true);
			panelDoctores.setVisible(true);
			panelEnfermedades.setVisible(true);
			panelPacientes.setVisible(true);
			panelSecre.setVisible(true);
		}
		else {
			panelCitas.setVisible(true);
		}
		
		JLabel lblEnfermedades = new JLabel("Enfermedades");
		lblEnfermedades.setBackground(new Color(147, 112, 219));
		lblEnfermedades.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnfermedades.setBounds(0, 0, 240, 23);
		lblEnfermedades.setOpaque(true);
		panelEnfermedades.add(lblEnfermedades);
		
		lblCantEnfermedades = new JLabel("");
		lblCantEnfermedades.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantEnfermedades.setForeground(Color.WHITE);
		lblCantEnfermedades.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCantEnfermedades.setBounds(0, 57, 240, 33);
		lblCantEnfermedades.setText(String.valueOf(Clinica.getInstance().getMisEnfermedades().size()));
		panelEnfermedades.add(lblCantEnfermedades);
		
		JLabel lblCantidadDeEnfermedades = new JLabel("Cantidad de Enfermedades registradas");
		lblCantidadDeEnfermedades.setForeground(Color.WHITE);
		lblCantidadDeEnfermedades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadDeEnfermedades.setBounds(0, 122, 240, 23);
		panelEnfermedades.add(lblCantidadDeEnfermedades);
		
		btnVerEnfermedades = new JButton("Ver Enfermedades ->");
		btnVerEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEnfermedades dialog = new ListEnfermedades();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				actualizarDashboard();
			}
		});
		btnVerEnfermedades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerEnfermedades.setBounds(0, 98, 240, 23);
		panelEnfermedades.add(btnVerEnfermedades);
	}
	
	public void actualizarDashboard() {
		lblCantEnfermedades.setText(String.valueOf(Clinica.getInstance().getMisEnfermedades().size()));
		lblCantCitas.setText(String.valueOf(Clinica.getInstance().getMisCitas().size()));
		lblCantPacientes.setText(String.valueOf(Clinica.getInstance().getMisPacientes().size()));
		lblCantDoctores.setText(String.valueOf(Clinica.getInstance().cantUsersType()[2]));
		lblCantAdministradores.setText(String.valueOf(Clinica.getInstance().cantUsersType()[0]));
		lblCantSecretarios.setText(String.valueOf(Clinica.getInstance().cantUsersType()[1]));
	}
}