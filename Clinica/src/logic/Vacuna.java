package logic;

public class Vacuna {

	private String codigo;
	private String nombre;
	private int dosis;
	private String EnContraDe;
	private String fabricante;
	private String efectos;
	
	public Vacuna(String codigo, String nombre, String EnContraDe, int dosis, String fabricante, String efectos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.dosis = dosis;
		this.EnContraDe = EnContraDe;
		this.fabricante = fabricante;
		this.efectos = efectos;
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

	public String getEnContraDe() {
		return EnContraDe;
	}

	public void setEnContraDe(String enContraDe) {
		EnContraDe = enContraDe;
	}

	public String getEfectos() {
		return efectos;
	}

	public void setEfectos(String efectos) {
		this.efectos = efectos;
	}	
}
