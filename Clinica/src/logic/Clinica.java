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
	private int generadorCodigoCitaMedica = 1;
	private int generadorCodigoConsulta = 1;
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
		generadorCodigoCitaMedica++;
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
	
	public Paciente buscarPaciente(String cedula, String fechaNacimiento)
	{
		Paciente paciente = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misPacientes.size()) {
			if(misPacientes.get(i).getCedula().equalsIgnoreCase(cedula) && misPacientes.get(i).getFechaNacimiento().equalsIgnoreCase(fechaNacimiento)) {
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
		int[] cant = {0,0,0};
		
		for(Usuario user : misUsuarios)
		{
			if(user instanceof Administrador && ((Administrador) user).getPuestoLaboral().equalsIgnoreCase("Administrador"))
				cant[0]++;
			if(user instanceof Administrador && ((Administrador) user).getPuestoLaboral().equalsIgnoreCase("Secretario"))
				cant[1]++;
			if(user instanceof Medico)
				cant[2]++;
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
	
	public int getGeneradorCodigoCitaMedica() {
		return generadorCodigoCitaMedica;
	}
	
	public int getGeneradorCodigoConsulta() {
		return generadorCodigoConsulta;
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
	
	public void modificarCitaMedica(CitaMedica updated) {
		CitaMedica cita = buscarCita(updated.getCodigo());
		if(cita != null) {
			cita.setFecha(updated.getFecha());
			cita.setMedico(updated.getMedico());
			cita.setNombrePersona(updated.getNombrePersona());
			cita.setNumeroPersona(updated.getNumeroPersona());
		}
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Clinica.loginUser = loginUser;
	}
	
	public boolean buscarLoginExiste(String login, Usuario user) {
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < misUsuarios.size()) {
			if(user != null) {
				if(login.equals(user.getLogin())) {
					return false;
				}
			}
			if (misUsuarios.get(i).getLogin().equals(login)) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}
	
	public boolean buscarCitaMedicaExiste(String fecha, CitaMedica cita, String doc) {
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < misCitas.size()) {
			if(cita != null) {
				if(cita.getFecha().equalsIgnoreCase(fecha) && cita.getMedico().getCodigoUsuario().equalsIgnoreCase(doc)) {
					return false;
				}
			}
			if (misCitas.get(i).getFecha().equalsIgnoreCase(fecha) && misCitas.get(i).getMedico().getCodigoUsuario().equalsIgnoreCase(doc)) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}
	
	public Paciente buscarPacienteByCodConsulta(String codConsulta) {
		Paciente paci = null;
		int i = 0, j = 0;
		boolean encontradoConsulta = false;
		boolean encontradoPaci = false;
		while (!encontradoPaci && i < misPacientes.size()) {
			while (!encontradoConsulta && j < misPacientes.get(i).getMisConsultas().size())
			if (misPacientes.get(i).getMisConsultas().get(j).getCodConsulta().equalsIgnoreCase(codConsulta)) {
				encontradoPaci = true;
				encontradoConsulta = true;
				paci = misPacientes.get(i);
			}
			i++;
			j++;
		}
		return paci;
	}
	
	public void insertarConsulta(Consulta aux, Paciente paci) {
		if(paci != null) {
			paci.getMisConsultas().add(aux);
			generadorCodigoConsulta++;
		}
	}
	
	public void modificarConsulta(Consulta updated) {
		Paciente paci = buscarPacienteByCodConsulta(updated.getCodConsulta());
		Consulta consulta = paci.buscarConsulta(updated.getCodConsulta());
		if(consulta != null) {
			consulta.setDiagnostico(updated.getDiagnostico());
			consulta.setEnfermedad(updated.getEnfermedad());
			consulta.setFechaConsulta(updated.getFechaConsulta());
			consulta.setSintomas(updated.getSintomas());
		}
	}
	
	public void modificarEnfermedad(Enfermedad updated) {
		Enfermedad disease = buscarEnfermedad(updated.getCodigo());
		if(disease != null) {
			disease.setNombre(updated.getNombre());
			disease.setTipo(updated.getTipo());
			disease.setDiagnostico(updated.getDiagnostico());
		}
	}
}
