package logic;

import java.util.Date;

public class Consulta {
	
	private String codConsulta;
	private Date fechaConsulta;
	private String Sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	
	public Consulta(String codConsulta, Date fechaConsulta, String sintomas, String diagnostico,
			Enfermedad enfermedad) {
		super();
		this.codConsulta = codConsulta;
		this.fechaConsulta = fechaConsulta;
		Sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.enfermedad = enfermedad;
	}

	public String getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(String codConsulta) {
		this.codConsulta = codConsulta;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getSintomas() {
		return Sintomas;
	}

	public void setSintomas(String sintomas) {
		Sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
}
