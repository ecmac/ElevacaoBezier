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

import mat.Funcoes;
import mat.Ponto;

@SuppressWarnings("serial")
public class TelaGrafico extends JPanel {
	
	private ArrayList<Ponto> pontos;
	//private int t;
	private Graphics2D g2d;
	private int nomePontoControle = 0;
	
	private boolean mostrarCurva = true;
	private boolean mostrarPontos = true;
	private boolean mostrarPoligonal = true;
	
	public TelaGrafico(){
		pontos = new ArrayList<Ponto>();
		setBackground(Color.WHITE);
		
		//this.t = t;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				double x = arg0.getX();
				double y = arg0.getY();
				double realY = Funcoes.converter(y, getHeight());
				
				pontos.add(new Ponto(x, realY,"" + nomePontoControle));
				
				nomePontoControle++;
				
				//g2d.fillOval(x, virtualY, 20, 200);
				repaint();
				
				//JOptionPane.showMessageDialog(frmPrincipal, "(" + x + ", " + y + ")" , "Posição do mouse", JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println("Posição do mouse (" + x + ", " + y + ")");
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
				//g2d.fillOval(ponto.getX(), ponto.getY(), 10, 10);
				double yVirtual = Funcoes.converter(ponto.getY(), getHeight());
				Ellipse2D.Double circ = new Ellipse2D.Double(ponto.getX(), yVirtual, 5, 5);
				g2d.fill(circ);
			}
		}
		
		if(mostrarPoligonal){
			
			//TODO
		}
		
		if(mostrarCurva){
			
			//TODO
			
			if(pontos.size()>=2){
				//TODO
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
}
