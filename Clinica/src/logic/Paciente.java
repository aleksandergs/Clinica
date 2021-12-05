package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Paciente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private String genero;
	private String fechaNacimiento;
	private String direccion;
	private String telefono;
	private HistorialClinico historia;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	private String alergias;
	private String tipoSangre;
	
	public Paciente(String cedula, String nombre, String genero, String fechaNacimiento, String direccion,
			String telefono, String alergias, String tipoSangre) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.historia = new HistorialClinico();
		this.misConsultas = new ArrayList<Consulta>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.alergias = alergias;
		this.tipoSangre = tipoSangre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public HistorialClinico getHistoria() {
		return historia;
	}

	public void setHistoria(HistorialClinico historia) {
		this.historia = historia;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}
	
	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public Consulta buscarConsulta(String codigo)
	{
		Consulta consulta = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misConsultas.size()) {
			if(misConsultas.get(i).getCodConsulta().equalsIgnoreCase(codigo)) {
				encontrado=true;
				consulta = misConsultas.get(i);
			}
			i++;
		}
		return consulta;
	}
	
}
