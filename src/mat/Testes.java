package mat;

import java.util.Scanner;

public class Testes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		float Ax = in.nextFloat();
		float Ay = in.nextFloat();
		float Bx = in.nextFloat();
		float By = in.nextFloat();
		float t = (float)in.nextFloat();
		
		Ponto A = new Ponto(Ax, Ay, "A");
		Ponto B = new Ponto(Bx, By, "B");
		
		System.out.println(A.toString());
		System.out.println(B.toString());
		
		Ponto P = Funcoes.interp(A, B, t);
		
		System.out.println(P.toString());
		
		
		

	}

}
