package mat;

public class Funcoes {
	
	public static Ponto interp (Ponto a, Ponto b, double t) {
		return (a.mult(1-t)).adi(b.mult(t));
	}
	
	public static double bi(int i, int n, double t) {
		return (binomialCoeff(n, i) * Math.pow((1-t), n-i) * Math.pow(t, i));
	}	
	
	//https://www.geeksforgeeks.org/space-and-time-efficient-binomial-coefficient/
	public static int binomialCoeff(int n, int k){ 
		int res = 1; 

		// Since C(n, k) = C(n, n-k) 
		if (k > n - k) {
			k = n - k; 
		}

		// Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1] 
		for (int i = 0; i < k; ++i){
			res *= (n - i); 
			res /= (i + 1); 
		} 

		return res; 
	} 
	
	public static int numPontos(int n) {
		
		int soma = 0;
		for (int i = n; i>0; i--){
			soma = soma + i;
		}
		
		return soma;
	}
	
	
}
