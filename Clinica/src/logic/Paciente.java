package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Paciente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private String genero;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private HistorialClinico historia;
	private ArrayList<Consulta> misConsultas;
	private String alergias;
	
	public Paciente(String cedula, String nombre, String genero, Date fechaNacimiento, String direccion,
			String telefono, HistorialClinico historia, ArrayList<Consulta> misConsultas, String alergias) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.historia = historia;
		this.misConsultas = misConsultas;
		this.alergias = alergias;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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
	
}
