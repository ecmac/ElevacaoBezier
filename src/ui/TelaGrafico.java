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
import mat.Funcoes;
import mat.Ponto;

@SuppressWarnings("serial")
public class TelaGrafico extends JPanel {
	
	private ArrayList<Ponto> pontos;
	
	private double t;
	private Graphics2D g2d;
	private int nomePontoControle = 0;
	
	private boolean mostrarCurva = true;
	private boolean mostrarPontos = true;
	private boolean mostrarPoligonal = true;
	
	public TelaGrafico(){
		pontos = new ArrayList<Ponto>();
		setBackground(Color.WHITE);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				double x = arg0.getX();
				double y = arg0.getY();
				double realY = Funcoes.converter(y, getHeight());
				
				pontos.add(new Ponto(x, realY,"" + nomePontoControle));
				
				nomePontoControle++;
				
				repaint();
				
				System.out.println("Posição do mouse (" + x + ", " + y + ")");
			}
		});
	}

	public void setT(double t) {
		this.t = t;
	}

	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.blue);
		
		if(mostrarPontos){
			for(Ponto ponto : pontos){
				double yVirtual = Funcoes.converter(ponto.getY(), getHeight());
				Ellipse2D.Double circ = new Ellipse2D.Double(ponto.getX(), yVirtual, 5, 5);
				g2d.fill(circ);
			}
		}
		
		/**
		
		if(mostrarPoligonal){
			
			//TODO
		}
		
		if(mostrarCurva){
			
			//TODO
			
			Ponto daCurva = fazerCurva();
			
			double yVirt = Funcoes.converter(daCurva.getY(), getHeight());
			Ellipse2D.Double pto = new Ellipse2D.Double(daCurva.getX(), yVirt, 5, 5);
			g2d.fill(pto);
		}
		
		**/
		
	}
	
	public void toggleCurva(){
		mostrarCurva = !mostrarCurva;
		repaint();
	}
	
	public void togglePontos(){
		mostrarPontos = !mostrarPontos;
		repaint();
	}
	
	public void togglePoligonal(){
		mostrarPoligonal = !mostrarPoligonal;
		repaint();
	}
	
	public void resetar(){
		pontos.clear();
		repaint();
	}
	
	public Ponto fazerCurva(){
		
		int grau = pontos.size() - 1;
		Ponto[] controles = getArray(pontos);
		CurvaBezier curvaBezier = new CurvaBezier(grau, controles, t);
		return curvaBezier.formaGeral(0, grau);
		
	}
	
	/**
	public void gerarCurva(){
		if(pontos.size()>2){
			Ponto[] ptos = getArray(pontos);
			CurvaBezier curva = new CurvaBezier(pontos.size() - 1, ptos, t);
			curva.calcularPontos();
			pontoDaCurva = curva.pontoDaCurva();
		}
		else if(pontos.size()==2){
			pontoDaCurva = Funcoes.interp(pontos.get(0), pontos.get(1), t);
		}
		
		if(pontoDaCurva!=null){
			double yVirtual = Funcoes.converter(pontoDaCurva.getY(), getHeight());
			Ellipse2D.Double pc = new Ellipse2D.Double(pontoDaCurva.getX(), yVirtual, 5, 5);
			g2d.fill(pc);
			System.out.println("Ponto da curva parcial: (" + pontoDaCurva.getX() + ", " + yVirtual + ")");
		}		
	}
	**/
	
	public Ponto[] getArray(ArrayList<Ponto> al){
		
		int length = al.size();
		Ponto[] array = new Ponto[length];
		
		int i = 0;
		for(Ponto ponto : al){
			array[i] = ponto;
			i++;
		}
		
		return array;
	}
	
	public void printArrayList(){
		for(Ponto ponto : pontos){
			System.out.println(ponto.toString() + ", yVirtual: " + Funcoes.converter(ponto.getY(), getHeight()));
		}
	}
}
