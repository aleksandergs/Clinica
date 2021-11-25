package logic;

import java.util.ArrayList;

public class Clinica {

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
	
	public Usuario validarUser(String user, String password) {
		boolean encontrado = false;
		Usuario usuario = null;
		int i = 0;
		while(!encontrado && i < misUsuarios.size()) {
			if(misUsuarios.get(i).getLogin().equals(user) && misUsuarios.get(i).getPassword().equals(password)) {
				encontrado = true;
				usuario = misUsuarios.get(i);
			}
			i++;
		}
		return usuario;
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
}
