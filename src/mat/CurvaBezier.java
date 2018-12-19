package mat;

import java.util.ArrayList;

public class CurvaBezier {
	
	private int grau;
	private ArrayList<Ponto> controles;
	private double t;
	private int qtd;
	private Ponto[][] matriz = null;
	
	public CurvaBezier(int grau, ArrayList<Ponto> controles, double t) {
		this.grau = grau;
		this.controles = controles;
		this.t = t;
		this.qtd = controles.size();
	}

	public int getQtd() {
		return qtd;
	}

	public ArrayList<Ponto> getControles() {
		return controles;
	}

	public void setControles(ArrayList<Ponto> controles) {
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
	
	public Ponto deCasteljauRecursivoOtimizado(int i, int r){
		
		if(matriz==null) {
			matriz = new Ponto[r+1][r+1];
		}
		
		if(r==0) {
			matriz[i][0] = controles.get(i);
			return controles.get(i);
		}
		
		if(matriz[i][r-1]==null) {
			matriz[i][r-1] = deCasteljauRecursivoOtimizado(i, r-1);
		}
		
		if(matriz[i+1][r-1]==null) {
			matriz[i+1][r-1] = deCasteljauRecursivoOtimizado(i+1, r-1);
		}
		
		return interp(matriz[i][r-1], matriz[i+1][r-1], t);
	}
	
}























