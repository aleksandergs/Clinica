package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Clinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<CitaMedica> misCitas;
	private static Clinica clinica;
	private int generadorCodigoEnfermedad = 1;
	private int generadorCodigoVacuna = 1;
	private int generadorCodigoUserA = 1;
	private int generadorCodigoDoctor = 1;
	private static Usuario loginUser;
	
	private Clinica() {
		super();
		misPacientes = new ArrayList<>();
		misVacunas = new ArrayList<>();
		misUsuarios = new ArrayList<>();
		misEnfermedades = new ArrayList<>();
		misCitas = new ArrayList<>();
	}
	
	public static Clinica getInstance() {
		if(clinica == null)
			clinica = new Clinica();
		return clinica;
	}
	
	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
	}

	
	public void insertarPaciente(Paciente p) {
		misPacientes.add(p);
	}
	
	public void insertarVacuna(Vacuna v) {
		misVacunas.add(v);
		generadorCodigoVacuna++;
	}

	public void insertarUsuario(Usuario u) {
		misUsuarios.add(u);
		generadorCodigoUserA++;
	}
	
	public void insertarDoctor(Usuario u) {
		misUsuarios.add(u);
		generadorCodigoDoctor++;
	}
	
	public void insertarEnfermedad(Enfermedad e) {
		misEnfermedades.add(e);
		generadorCodigoEnfermedad++;
	}
	
	public void insertarCita(CitaMedica c) {
		misCitas.add(c);
	}
	
	public boolean confirmLogin(String userLog, String password) {
		boolean login = false;
		for (Usuario user : misUsuarios) {
			if(user.getLogin().equals(userLog) && user.getPassword().equals(password)){
				setLoginUser(user);
				login = true;
			}
		}
		return login;
	}
	
	public Paciente buscarPaciente(String cedula)
	{
		Paciente paciente = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misPacientes.size()) {
			if(misPacientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				encontrado=true;
				paciente = misPacientes.get(i);
			}
			i++;
		}
		return paciente;
	}
	
	public Usuario buscarUsuario(String codigo)
	{
		Usuario usuario = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misUsuarios.size()) {
			if(misUsuarios.get(i).getCodigoUsuario().equalsIgnoreCase(codigo)) {
				encontrado=true;
				usuario = misUsuarios.get(i);
			}
			i++;
		}
		return usuario;
	}
	
	public Enfermedad buscarEnfermedad(String codigo)
	{
		Enfermedad enfermedad = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misEnfermedades.size()) {
			if(misEnfermedades.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado=true;
				enfermedad = misEnfermedades.get(i);
			}
			i++;
		}
		return enfermedad;
	}
	
	public CitaMedica buscarCita(String codigo)
	{
		CitaMedica cita = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misCitas.size()) {
			if(misCitas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado=true;
				cita = misCitas.get(i);
			}
			i++;
		}
		return cita;
	}
	
	public Vacuna buscarVacuna(String codigo)
	{
		Vacuna vacuna = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misVacunas.size()) {
			if(misVacunas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado=true;
				vacuna = misVacunas.get(i);
			}
			i++;
		}
		return vacuna;
	}
	
	public int[] cantUsersType()
	{
		int[] cant = {0,0};
		
		for(Usuario user : misUsuarios)
		{
			if(user instanceof Administrador)
				cant[0]++;
			if(user instanceof Medico)
				cant[1]++;
		}
		
		return cant;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public ArrayList<CitaMedica> getMisCitas() {
		return misCitas;
	}
	
	public int getGeneradorCodigoEnfermedad() {
		return generadorCodigoEnfermedad;
	}

	public int getGeneradorCodigoVacuna() {
		return generadorCodigoVacuna;
	}

	public int getGeneradorCodigoUserA() {
		return generadorCodigoUserA;
	}

	public int getGeneradorCodigoDoctor() {
		return generadorCodigoDoctor;
	}
	
	public void modificarUsuario(Usuario updated) {
		Usuario user = buscarUsuario(updated.getCodigoUsuario());
		if(user != null) {
			user.setLogin(updated.getLogin());
			user.setTelefono(updated.getTelefono());
			user.setNombre(updated.getNombre());
			user.setPassword(updated.getPassword());
			if(updated instanceof Medico) {
				((Medico) user).setNumHabitacion(((Medico) updated).getNumHabitacion());
				((Medico) user).setEspecialidad(((Medico) updated).getEspecialidad());
			}
			else {
				((Administrador) user).setPuestoLaboral(((Administrador) updated).getPuestoLaboral());
			}
		}
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Clinica.loginUser = loginUser;
	}
}
