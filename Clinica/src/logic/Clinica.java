package logic;

import java.util.ArrayList;

public class Clinica {

	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<CitaMedica> misCitas;
	private static Clinica clinica;
	
	private Clinica() {
		super();
		misPacientes = new ArrayList<>();
		misVacunas = new ArrayList<>();
	}
	
	public static Clinica getInstance() {
		if(clinica == null)
			clinica = new Clinica();
		return clinica;
	}
}
