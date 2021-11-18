package logic;

import java.util.ArrayList;

public class HistorialClinico {
	
	private String codigoHistoria;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	
	public HistorialClinico(String codigoHistoria, ArrayList<Consulta> misConsultas, ArrayList<Vacuna> misVacunas) {
		super();
		this.codigoHistoria = codigoHistoria;
		this.misConsultas = misConsultas;
		this.misVacunas = misVacunas;
	}

	public String getCodigoHistoria() {
		return codigoHistoria;
	}

	public void setCodigoHistoria(String codigoHistoria) {
		this.codigoHistoria = codigoHistoria;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
}
