package logic;

import java.util.Date;

public class Consulta {
	
	private String codigoConsulta;
	private Date fechaConsulta;
	private String Sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	
	public Consulta(String codigoConsulta, Date fechaConsulta, String sintomas, String diagnostico,
			Enfermedad enfermedad) {
		super();
		this.codigoConsulta = codigoConsulta;
		this.fechaConsulta = fechaConsulta;
		Sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.enfermedad = enfermedad;
	}

	public String getCodigoConsulta() {
		return codigoConsulta;
	}

	public void setCodigoConsulta(String codigoConsulta) {
		this.codigoConsulta = codigoConsulta;
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
