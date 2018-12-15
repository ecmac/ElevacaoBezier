package mat;

public class CurvaBezier {
	
	private int grau;
	private Ponto[] controles;
	private double t;
	//private Ponto[] curva;
	
	private Ponto[][] pontos;
	
	public CurvaBezier(int grau, Ponto[] controles, double t) {
		this.grau = grau;
		this.controles = controles;
		this.t = t;
		//this.curva =  new Ponto[Funcoes.numPontos(grau)];
		this.pontos = new Ponto[grau+1][grau+1];
	}
	
	private void controlesParaMatriz(){
		
		for(int i=0; i<controles.length; i++){
			 pontos[i][0] = controles[i];
		}
		
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
	
	/**
	public Ponto getPontoCurva() {
		return curva[curva.length - 1];
	}
	**/
	
	public Ponto pontoDaCurva(){
		return pontos[0][grau];
	}
	
	/**

	public Ponto formaGeral(int j, int r) {
		
		Ponto bjr = new Ponto(0,0);
		
		for(int i=0; i<r; i++) {
			bjr = bjr.adi(controles[i+j].mult(Funcoes.bi(i, r, t)));
		}
		
		bjr.setNome(j + "" + r);
		
		return bjr;
	}
	
	**/
	
	public void calcularPontos(){
		
		controlesParaMatriz();
		
		for(int r=1; r<=grau; r++){
			for(int i=0; i<(grau - r); i++){
				Ponto p = Funcoes.interp(pontos[i][r-1], pontos[i+1][r-1], t);
				p.setNome(i + "" + r);
				pontos[i][r] = p;
				
				System.out.println("Adicionei ponto: " + p.toString());
			}
		}
	}
	
	/**
	public void calculaPontos() {
		int last = -1;
		
		for(int r=1; r<=grau; r++) {
			for(int i=0; i<(grau-r); i++) {
				Ponto p = formaGeral(i, r);
				p.setNome(i + "" + r);
				
				System.out.println("Adicionei ponto: " + p.toString());
				
				last++;
				curva[last] = p;
			}
		}
			
	}
	
	
	public String curvaToString(){
		String s = "Pontos da curva:";
		
		for(int i=0; i<curva.length; i++){
			s = s + "\n" + curva[i].toString();
		}
		
		return s;
	}
	
	public int curvaLength(){
		return curva.length;
	}
	
	**/

}
