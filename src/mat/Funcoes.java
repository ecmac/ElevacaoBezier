package mat;

public class Funcoes {
	
	public Ponto interp (Ponto a, Ponto b, float t) {
		return (a.mult(1-t)).adi(b.mult(t));
	}

}
