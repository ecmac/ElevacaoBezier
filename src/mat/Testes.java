package mat;

public class Testes {
	
	public static void printControles(Ponto[] controles){
		
		for(int i=0; i<controles.length; i++){
			 System.out.println(controles[i].toString());
		}
		
		System.out.println("");
	}

	public static void main(String[] args) {
		
		Ponto[] pontos = new Ponto[5];
		pontos[0] = new Ponto(1.0, 0.0, "0");
		pontos[1] = new Ponto(0.0, 1.0, "1");
		pontos[2] = new Ponto(-2.0, 0.0, "2");
		pontos[3] = new Ponto(-2.0, 2.0, "3");
		pontos[4] = new Ponto(2.0, 2.0, "4");
		
		printControles(pontos);
		
		CurvaBezier curva = new CurvaBezier(4, pontos, 0.5);
		
		printControles(curva.getControles());
		
		System.out.println("Qtd:" + curva.getQtd() + "\n");
		
		curva.calcularPontos();
		
		Ponto pc = curva.pontoDaCurva();
		
		if (pc==null) System.out.println("PC is null");
		else System.out.println(pc.toString());
		
		double xResp = -17.0/16.0, yResp = 14.0/16.0;
		
		System.out.println("A resposta era: (" + xResp + ", " + yResp + ")");
		
		

	}

}
