package mat;

public class CurvaBezier {
	
	private int grau;
	private Ponto[] controles;
	private double t;
	private int qtd;
	
	public CurvaBezier(int grau, Ponto[] controles, double t) {
		this.grau = grau;
		this.controles = controles;
		this.t = t;
		this.qtd = controles.length;
	}

	public int getQtd() {
		return qtd;
	}

	public Ponto[] getControles() {
		return controles;
	}

	public void setControles(Ponto[] controles) {
		this.controles = controles;
	}

	public int getGrau() {
		return grau;
	}

	public double getT() {
		return t;
	}
	
	private Ponto interp (Ponto a, Ponto b, double t) {
		return (a.mult(1-t)).adi(b.mult(t));
	}
	
	public Ponto birt(int i, int r){
		
		if(r==0) return controles[i];
		
		Ponto esq = birt(i, r-1);
		Ponto dir = birt(i+1, r-1);
		
		return interp(esq, dir, t);
	}
}























