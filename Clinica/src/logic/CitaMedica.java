package logic;

import java.io.Serializable;
import java.util.Date;

public class CitaMedica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Date fecha;
	private String nombrePersona;
	private String numeroPersona;
	Medico medico;
	
	public CitaMedica(String codigo, Date fecha, String nombrePersona, String numeroPersona, Medico medico) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.nombrePersona = nombrePersona;
		this.numeroPersona = numeroPersona;
		this.medico = medico;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getNumeroPersona() {
		return numeroPersona;
	}
	public void setNumeroPersona(String numeroPersona) {
		this.numeroPersona = numeroPersona;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}
