package mat;

public class CurvaBezier {
	
	private int grau;
	private Ponto[] controles;
	private float t;
	private Ponto[] curva;
	
	public CurvaBezier(int grau, Ponto[] controles, float t) {
		this.grau = grau;
		this.controles = controles;
		this.t = t;
		this.curva =  new Ponto[Funcoes.fat(grau)];
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

	public float getT() {
		return t;
	}
	
	public Ponto getPontoCurva() {
		return curva[curva.length - 1];
	}

	public Ponto formaGeral(int j, int r) {
		
		Ponto bjr = new Ponto(0,0);
		
		for(int i=0; i<r; i++) {
			bjr = bjr.adi(controles[i+j].mult(Funcoes.bi(i, r, t)));
		}
		
		bjr.setNome(j + "" + r);
		
		return bjr;
	}
	
	public void calculaPontos() {
		int fim = 0;
		
		for(int r=0; r<grau; r++) {
			for(int i=0; i<grau-r; i++) {
				Ponto p = formaGeral(i, r);
				p.setNome(i + "" + r);
				curva[fim] = p;
				fim++;
			}
		}
			
	}

}
