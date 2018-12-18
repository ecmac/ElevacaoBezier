package mat;

public class Ponto {
	
	private double x;
	private double y;
	
	public Ponto(double x, double y){
		this.x = x;
		this.y = y;
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

	public Ponto mult(double n) {
		return new Ponto(n * x, n * y);
	}
	
	public Ponto adi(Ponto b) {
		return new Ponto(x+b.getX(), y+b.getY());
	}
	
	public String toString() {
		return "Ponto " + "(" + x + ", " + y + ")";
	}
	
}
