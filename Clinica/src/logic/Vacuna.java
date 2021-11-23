package logic;

public class Vacuna {

	private String codigo;
	private String nombre;
	int dosis;
	private String fabricante;
	
	public Vacuna(String codigo, String nombre, int dosis, String fabricante) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.dosis = dosis;
		this.fabricante = fabricante;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDosis() {
		return dosis;
	}
	public void setDosis(int dosis) {
		this.dosis = dosis;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	
}
