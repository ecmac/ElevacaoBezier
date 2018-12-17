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

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mat.CurvaBezier;
import mat.Funcoes;
import mat.Ponto;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class TelaGrafico extends JPanel {
	
	private ArrayList<Ponto> pontos;
	private ArrayList<Ponto> curvaaa;
	
	private Graphics2D g2d;
	private int nomePontoControle = 0;
	
	private boolean mostrarCurva = true;
	private boolean mostrarPontos = true;
	private boolean mostrarPoligonal = true;
	
	private boolean arrastar = false;
	private int indiceArrastado;
	
	public TelaGrafico(){
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				if(arrastar){
					double x = arg0.getX();
					double y = arg0.getY();
					double realY = converter(y, getHeight());
					
					pontos.get(indiceArrastado).arrastar(x, realY);
					repaint();
				}
				
				//TODO
			}
		});
		pontos = new ArrayList<Ponto>();
		setBackground(Color.WHITE);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				double x = arg0.getX();
				double y = arg0.getY();
				double realY = converter(y, getHeight());
				
				boolean tem = false;
				
				for(int i=0; i<pontos.size(); i++){
					Ponto pt = pontos.get(i);
					double xP = pt.getX();
					double yP = pt.getY();
					
					if(x==xP && y==realY){
						tem = true;
						arrastar = true;
						indiceArrastado = i;
					}
					
					if(tem) i=pontos.size();
				}
				
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				arrastar = false;
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				double x = arg0.getX();
				double y = arg0.getY();
				double realY = converter(y, getHeight());
				
				pontos.add(new Ponto(x, realY,"" + nomePontoControle));
				nomePontoControle++;
				
				repaint();
				
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.blue);
		
		if(mostrarPontos){
			for(Ponto ponto : pontos){
				double yVirtual = converter(ponto.getY(), getHeight());
				Ellipse2D.Double circ = new Ellipse2D.Double(ponto.getX(), yVirtual, 5, 5);
				g2d.fill(circ);
			}			
		}
		
		
		
		if(mostrarPoligonal){
			
			for(int i=0; i<pontos.size()-1; i++){
				
				Ponto p = pontos.get(i);
				double yVirtual = converter(p.getY(), getHeight());
				
				Ponto prox = pontos.get(i+1);
				double yProxV = converter(prox.getY(), getHeight());
				
				Line2D.Double line = new Line2D.Double(p.getX(), yVirtual, prox.getX(), yProxV);
				g2d.draw(line);
				
			}
		}
		
		
		if(mostrarCurva){
			
			if(pontos.size()>=2){
				
				calcularCurvaPontos();
				
				for(int i=0; i<curvaaa.size(); i++){
					
					Ponto p = curvaaa.get(i);
					double yVirtual = converter(p.getY(), getHeight());
					
					//Desenhar o subponto
					Ellipse2D.Double circ = new Ellipse2D.Double(p.getX(), yVirtual, 1, 1);
					g2d.fill(circ);
					
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
						
						Line2D.Double line = new Line2D.Double(p.getX(), yVirtual, pProx.getX(), yProxV);
						g2d.draw(line);
						
						
					}
				}
			}			
		}
		
		
		
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
	
	public Ponto fazerPontoDaCurva(double t){
		
		int grau = pontos.size() - 1;
		Ponto[] controles = getArray(pontos);
		CurvaBezier curvaBezier = new CurvaBezier(grau, controles, t);
		return curvaBezier.birt(0, grau);
		
	}
	
	public void calcularCurvaPontos(){
		
		curvaaa= new ArrayList<Ponto>();
		for(double t=0.01; t<1; t=t+0.01){
			curvaaa.add(fazerPontoDaCurva(t));
		}
	}
	
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
	
	public void setPontos(ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}

	public ArrayList<Ponto> getPontos() {
		return pontos;
	}

	public void printArrayList(){
		for(Ponto ponto : pontos){
			System.out.println(ponto.toString() + ", yVirtual: " + converter(ponto.getY(), getHeight()));
		}
	}
	
	public double converter(double y, int height) {
		return height - y;
	}
	
	/* FONTE:
	 * Farin & Hansford - The Essentials of CAGD
	 * Slides do cap�tulo 4
	 * Slides 16 a 20
	 */
	
	public ArrayList<Ponto> elevarGrau(){
		
		Ponto[] antes = getArray(pontos);
		
		double n = antes.length - 1.0;
		
		//Ponto[] depois = new Ponto[antes.length + 1];
		
		ArrayList<Ponto> depois = new ArrayList<Ponto>();
		
		depois.add(antes[0]);
		
		
		for(double i=1.0; i<=n; i++){
			//depois[i] = ((double)i/(n+1))*antes[i-1] + 
			double m1 = i/(n+1.0);
			Ponto p1 = antes[(int)i - 1].mult(m1);
			
			//(1.0 - (double)i/(n+1))*antes[i]
			double m2 = 1.0 - (i/(n+1.0));
			Ponto p2 = antes[(int)i].mult(m2);
			
			depois.add(p1.adi(p2));
		}
		
		depois.add(antes[(int)n]);
		
		return depois;
	}
	
}
