package mat;

public class CurvaBezier {
	
	private int grau;
	private Ponto[] controles;
	private double t;
	private int qtd;
	
	private Ponto[][] pontos;
	
	public CurvaBezier(int grau, Ponto[] controles, double t) {
		this.grau = grau;
		this.controles = controles;
		this.t = t;
		this.qtd = controles.length;
		this.pontos = new Ponto[qtd][qtd];
	}
	
	private void controlesParaMatriz(){
		
		for(int i=0; i<qtd; i++){
			 pontos[i][0] = controles[i];
		}
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
	
	public Ponto pontoDaCurva(){
		return pontos[0][grau];
	}
	
	
	public Ponto formaGeral(int j, int r) {
		
		Ponto bjr = new Ponto(0,0);
		
		for(int i=0; i<r; i++) {
			bjr = bjr.adi(controles[i+j].mult(Funcoes.bi(i, r, t)));
		}
		
		bjr.setNome(j + "" + r);
		
		return bjr;
	}
	
	public Ponto birt(int i, int r){
		
		if(r==0) return controles[i];
		
		Ponto esq = birt(i, r-1);
		Ponto dir = birt(i+1, r-1);
		
		return Funcoes.interp(esq, dir, t);
	}
	
	public void calcularPontos(){
		
		controlesParaMatriz();
		
		for(int r=1; r < qtd; r++){
			//System.out.println("R: " + r);
			for(int i=0; i<(qtd - r); i++){
				//System.out.println("R: " + r + ", I: " + i);
				Ponto p = Funcoes.interp(pontos[i][r-1], pontos[i+1][r-1], t);
				p.setNome(i + "" + r);
				pontos[i][r] = p;
				
				//System.out.println("Adicionei: " + p.toString());
			}
		}
	}
	
	
	
	
	/**
	public void elevarGrau(Ponto[] controle){
		
		double[][] matriz = gerarMatriz(controle.length);
	}
	
	public double[][] gerarMatriz(int qtd){
		
		double[][] valores = new double[qtd+1][qtd];
		
		for(int i=0; i<qtd+1; i++){
			for(int j=0; j<qtd; j++){
				
			}
		}
		
		return null;
	}
	**/
}























