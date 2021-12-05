package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class HistorialClinico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Consulta> misConsultas;
	
	public HistorialClinico() {
		super();
		this.misConsultas = new ArrayList<Consulta>();
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
}
