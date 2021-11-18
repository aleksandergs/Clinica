package logic;

public class Medico extends Usuario {
	
	private String codigoMedico;
	private String especialidad;
	private int numHabitacion;
	
	public Medico(String codigoUsuario, String login, String cedulaUsuario, String password, String nombre,
			String telefono, String codigoMedico, String especialidad, int numHabitacion) {
		super(codigoUsuario, login, cedulaUsuario, password, nombre, telefono);
		this.codigoMedico = codigoMedico;
		this.especialidad = especialidad;
		this.numHabitacion = numHabitacion;
	}
	
	public String getCodigoMedico() {
		return codigoMedico;
	}
	public void setCodigoMedico(String codigoMedico) {
		this.codigoMedico = codigoMedico;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public int getNumHabitacion() {
		return numHabitacion;
	}
	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	
	

}
