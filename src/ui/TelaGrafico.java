package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import mat.CurvaBezier;
import mat.Ponto;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class TelaGrafico extends JPanel {
	
	private ArrayList<Ponto> pontos;
	private ArrayList<Ponto> curvaaa;
	
	private boolean mostrarCurva = true;
	private boolean mostrarPontos = true;
	private boolean mostrarPoligonal = true;
	
	
	private boolean atualizarCurva = true;
	private boolean atualizarPontos = true;
	private boolean atualizarPoligonal = true;
	
	private boolean arrastar = false;
	private int indiceArrastado;
	
	private double avalInc;
	private boolean apagarPonto;
	
	public TelaGrafico(double avalInc){
		
		this.avalInc = avalInc;
		apagarPonto = false;
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			/**
			 * Arrastar para mover o ponto
			 * de controle de posição
			 */
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				if(arrastar){
					double x = arg0.getX();
					double y = arg0.getY();
					double realY = converter(y, getHeight());
					
					pontos.get(indiceArrastado).arrastar(x, realY);
					repaint();
				}
			}
		});
		pontos = new ArrayList<Ponto>();
		setBackground(Color.WHITE);
		
		addMouseListener(new MouseAdapter() {
			
			/**
			 * Se apertou mas não soltou na mesma
			 * posição, então quer mover o ponto.
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				double x = arg0.getX();
				double y = arg0.getY();
				double realY = converter(y, getHeight());
				
				int indice = temPonto(x, realY);
				if(indice != -1) {
					indiceArrastado = indice;
					arrastar = true;
				}
			}
			
			/**
			 * Se soltou e estava arrastando
			 * então pare de arrastar o ponto de controle
			 */
			@Override
			public void mouseReleased(MouseEvent arg0) {
				arrastar = false;
			}
			
			//Apenas criar ponto de controle novo
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(apagarPonto) {
					
					double x = arg0.getX();
					double y = arg0.getY();
					double realY = converter(y, getHeight());
					
					int indice = temPonto(x, realY);
					if(indice != -1) {
						pontos.remove(indice);
					}
				}
				else {
					double x = arg0.getX();
					double y = arg0.getY();
					double realY = converter(y, getHeight());
					
					pontos.add(new Ponto(x, realY));
				}
				repaint();
			}
		});
	}
	
	public double converter(double y, int height) {
		return height - y;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.blue);
		
		if(mostrarPontos && atualizarPontos){
			atualizaPontos(g2d);
		}
		
		if(mostrarPoligonal && atualizarPoligonal){
			atualizaPoligonal(g2d);
		}
		
		if(mostrarCurva && atualizarCurva){
			atualizaCurva(g2d);		
		}
	}
	
	private void atualizaPontos(Graphics2D g2d) {
		for(Ponto ponto : pontos){
			double yVirtual = converter(ponto.getY(), getHeight());
			Ellipse2D.Double circ = new Ellipse2D.Double(
					ponto.getX(), yVirtual, 5, 5);
			g2d.fill(circ);
		}	
	}
	
	private void atualizaPoligonal(Graphics2D g2d) {
		for(int i=0; i<pontos.size()-1; i++){
			
			Ponto p = pontos.get(i);
			double yVirtual = converter(p.getY(), getHeight());
			
			Ponto prox = pontos.get(i+1);
			double yProxV = converter(prox.getY(), getHeight());
			
			Line2D.Double line = new Line2D.Double(
					p.getX(), yVirtual, prox.getX(), yProxV);
			g2d.draw(line);
			
		}
	}
	
	
	private void atualizaCurva(Graphics2D g2d) {
		if(pontos.size()>=2){
			
			calcularCurvaPontos();
			
			for(int i=0; i<curvaaa.size(); i++){
				
				Ponto p = curvaaa.get(i);
				double yVirtual = converter(p.getY(), getHeight());
				
				//Reta do primeiro ponto de controle ao primeiro subponto
				if(i==0){
					Ponto p1 = pontos.get(0);
					double y00V = converter(p1.getY(), getHeight());
					Line2D.Double line1 = new Line2D.Double(p1.getX(), y00V,
							p.getX(), yVirtual);
					g2d.draw(line1);
				}
				
				//Reta do ultimo subponto ao ultimo ponto de controle
				if(i==(curvaaa.size()-1)){
					Ponto pF = pontos.get(pontos.size()-1);
					double yUltimoV = converter(pF.getY(), getHeight());
					Line2D.Double lineLast = new Line2D.Double(p.getX(), yVirtual, 
							pF.getX(), yUltimoV);
					g2d.draw(lineLast);
				}
				
				//Desenhar a reta ao proximo subponto
				else {
					Ponto pProx = curvaaa.get(i+1);
					double yProxV = converter(pProx.getY(), getHeight());
					
					Line2D.Double line = new Line2D.Double(
							p.getX(), yVirtual, pProx.getX(), yProxV);
					g2d.draw(line);
				}
			}
		}
	}
	
	/* FONTE:
	 * Farin & Hansford - The Essentials of CAGD
	 * Slides do capítulo 4
	 * Slides 16 a 20
	 */
	public ArrayList<Ponto> elevarGrau(){
		
		ArrayList<Ponto> antes = pontos;
		
		double n = antes.size() - 1.0;
		
		ArrayList<Ponto> depois = new ArrayList<Ponto>();
		
		depois.add(antes.get(0));
		
		
		for(double i=1.0; i<=n; i++){
			
			double m1 = i/(n+1.0);
			Ponto p1 = antes.get((int)i - 1).mult(m1);
			
			double m2 = 1.0 - (i/(n+1.0));
			Ponto p2 = antes.get((int)i).mult(m2);
			
			depois.add(p1.adi(p2));
		}
		
		depois.add(antes.get((int)n));
		
		return depois;
	}
	
	public void toggleCurva(){
		mostrarCurva = !mostrarCurva;
		
		atualizarCurva = true;
		atualizarPoligonal = false;
		atualizarPontos = false;
		
		repaint();
		
		atualizarPoligonal = true;
		atualizarPontos = true;
	}
	
	public void togglePontos(){
		mostrarPontos = !mostrarPontos;
		
		atualizarCurva = false;
		atualizarPoligonal = false;
		atualizarPontos = true;
		
		repaint();
		
		atualizarCurva = true;
		atualizarPoligonal = true;
	}
	
	public void togglePoligonal(){
		mostrarPoligonal = !mostrarPoligonal;
		
		atualizarCurva = false;
		atualizarPoligonal = true;
		atualizarPontos = false;
		
		repaint();
		
		atualizarCurva = true;
		atualizarPontos = true;
	}
	
	public void resetar(){
		pontos.clear();
		repaint();
	}
	
	public Ponto fazerPontoDaCurva(double t){
		
		int grau = pontos.size() - 1;
		CurvaBezier curvaBezier = new CurvaBezier(grau, pontos, t);
		return curvaBezier.deCasteljauRecursivoOtimizado(0, grau);
	}
	
	public void calcularCurvaPontos(){
		
		curvaaa= new ArrayList<Ponto>();
		for(double t=avalInc; t<1; t=t+avalInc){
			curvaaa.add(fazerPontoDaCurva(t));
		}
	}
	
	private boolean algumPerto(Ponto p, double xMouse, double yMouse) {
		
		double distancia = Math.sqrt(
				Math.pow( p.getX() - xMouse, 2) +
				Math.pow( p.getY() - yMouse, 2));
		
		return (distancia <= 5);
	}
	
	public int temPonto(double xMouse, double yMouse) {
		
		int indice = -1;
		boolean achou = false;
		
		for(int i=0; i<pontos.size() && !achou; i++) {
			
			Ponto p = pontos.get(i);
			achou = algumPerto(p, xMouse, yMouse);
			if(achou) indice = i;
		}
		
		return indice;
	}
	
	public void setAvalInc(double avalInc) {
		this.avalInc = avalInc;
	}
	
	public void setPontos(ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}

	public ArrayList<Ponto> getPontos() {
		return pontos;
	}

	public void setApagarPonto(boolean apagarPonto) {
		this.apagarPonto = apagarPonto;
	}
	
}
