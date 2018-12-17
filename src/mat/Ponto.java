package mat;

public class Ponto {
	
	private double x;
	private double y;
	private String nome;
	
	public Ponto(double x, double y){
		this.x = x;
		this.y = y;
		this.nome = "ponto";
	}
	
	public Ponto(double x, double y, String nome){
		this.x = x;
		this.y = y;
		this.nome = nome;
	}
	
	public void arrastar(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Ponto mult(double n) {
		return new Ponto(n * x, n * y);
	}
	
	public Ponto adi(Ponto b) {
		return new Ponto(x+b.getX(), y+b.getY());
	}
	
	public String toString() {
		return "Ponto " + nome + "(" + x + ", " + y + ")";
	}
	
}
