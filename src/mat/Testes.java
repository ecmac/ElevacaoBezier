package mat;

//import java.util.Scanner;

public class Testes {

	public static void main(String[] args) {
		
		//int aaaa = Funcoes.numPontos(4);
		//System.out.println("Numero de pontos: " + aaaa);
		
		// TODO Auto-generated method stub
		
		/**
		
		Scanner in = new Scanner(System.in);
		double Ax = in.nextdouble();
		double Ay = in.nextdouble();
		double Bx = in.nextdouble();
		double By = in.nextdouble();
		double t = in.nextdouble();
		
		Ponto A = new Ponto(Ax, Ay, "0");
		Ponto B = new Ponto(Bx, By, "1");
		
		System.out.println(A.toString());
		System.out.println(B.toString());
		
		Ponto P = Funcoes.interp(A, B, t);
		
		System.out.println(P.toString());
		
		in.close();
		**/
		
		Ponto[] pontos = new Ponto[5];
		pontos[0] = new Ponto(1,0, "0");
		pontos[1] = new Ponto(0,1, "1");
		pontos[2] = new Ponto(-2,0, "2");
		pontos[3] = new Ponto(-2, 2, "3");
		pontos[4] = new Ponto(-2, 2, "4");
		
		
		
		
		CurvaBezier curva = new CurvaBezier(4, pontos, 0.5);
		curva.calcularPontos();
		
		Ponto pc = curva.pontoDaCurva();
		
		//System.out.println("Numero: " + curva.curvaLength());
		
		/**curva.calculaPontos();
		
		System.out.println(curva.curvaToString());
		
		Ponto pc = curva.getPontoCurva();
		
		**/
		if (pc==null) System.out.println("PC is null");
		else System.out.println(pc.toString());
		
		

	}

}
