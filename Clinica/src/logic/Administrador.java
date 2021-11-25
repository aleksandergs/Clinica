package logic;

public class Administrador extends Usuario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
