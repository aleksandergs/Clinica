package logic;

public class Medico extends Usuario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;
	private int numHabitacion;
	
	public Medico(String codigoUsuario, String login, String cedulaUsuario, String password, String nombre,
			String telefono, String especialidad, int numHabitacion) {
		super(codigoUsuario, login, cedulaUsuario, password, nombre, telefono);
		this.especialidad = especialidad;
		this.numHabitacion = numHabitacion;
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
