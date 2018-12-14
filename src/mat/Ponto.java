package mat;

public class Ponto {
	
	private float x;
	private float y;
	private String nome;
	
	public Ponto(float x, float y){
		this.x = x;
		this.y = y;
		this.nome = "ponto";
	}
	
	public Ponto(float x, float y, String nome){
		this.x = x;
		this.y = y;
		this.nome = nome;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Ponto mult(float n) {
		return new Ponto(n * x, n * y);
	}
	
	public Ponto adi(Ponto b) {
		return new Ponto(x+b.getX(), y+b.getY());
	}
	
	public String toString() {
		return "Ponto " + nome + "(" + x + ", " + y + ")";
	}
	
}
