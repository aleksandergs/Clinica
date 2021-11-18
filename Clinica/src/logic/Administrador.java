package logic;

public class Administrador extends Usuario {
	
	private String puestoLaboral;

	public Administrador(String codigoUsuario, String login, String cedulaUsuario, String password, String nombre,
			String telefono, String puestoLaboral) {
		super(codigoUsuario, login, cedulaUsuario, password, nombre, telefono);
		this.puestoLaboral = puestoLaboral;
	}

	public String getPuestoLaboral() {
		return puestoLaboral;
	}

	public void setPuestoLaboral(String puestoLaboral) {
		this.puestoLaboral = puestoLaboral;
	}

}
