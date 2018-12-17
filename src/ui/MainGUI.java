package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class MainGUI {

	private JFrame frmPrincipal;
	private JTextField tValorField;
	
	private TelaGrafico tela;
	
	private boolean mostraCurva = true;
	JButton btnEsconderCurva;
	
	private boolean mostraPoligonal = true;
	JButton btnEsconderPoligonal;
	
	private boolean mostraPontos = true;
	JButton btnEsconderPontos;
	
	private JLabel lblValorDeT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmPrincipal = new JFrame();
		frmPrincipal.getContentPane().setBackground(new Color(204, 255, 255));
		frmPrincipal.setResizable(false);
		frmPrincipal.setTitle("Eleva\u00E7\u00E3o de grau de curva de B\u00E9zier");
		frmPrincipal.setBounds(100, 100, 700, 500);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		tela = new TelaGrafico();
		tela.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tela.setBackground(Color.WHITE);
		tela.setBounds(10, 11, 510, 449);
		frmPrincipal.getContentPane().add(tela);
		tela.setLayout(null);
		
		JButton btnResetar = new JButton("Resetar");
		btnResetar.setBounds(530, 21, 154, 40);
		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				resetar();
			}
		});
		frmPrincipal.getContentPane().add(btnResetar);
		
		btnEsconderCurva = new JButton("Esconder curva");
		btnEsconderCurva.setBounds(530, 72, 154, 40);
		btnEsconderCurva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				escondeCurva();
			}
		});
		frmPrincipal.getContentPane().add(btnEsconderCurva);
		
		btnEsconderPontos = new JButton("Esconder pontos");
		btnEsconderPontos.setBounds(530, 123, 154, 40);
		btnEsconderPontos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				escondePontos();
			}
		});
		frmPrincipal.getContentPane().add(btnEsconderPontos);
		
		btnEsconderPoligonal = new JButton("Esconder poligonal");
		btnEsconderPoligonal.setBounds(530, 174, 154, 40);
		btnEsconderPoligonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				escondePoligonal();
			}
		});
		frmPrincipal.getContentPane().add(btnEsconderPoligonal);
		
		lblValorDeT = new JLabel("Valor de t");
		lblValorDeT.setBounds(564, 247, 86, 20);
		lblValorDeT.setHorizontalAlignment(SwingConstants.CENTER);
		frmPrincipal.getContentPane().add(lblValorDeT);
		
		tValorField = new JTextField();
		tValorField.setBounds(564, 278, 86, 20);
		frmPrincipal.getContentPane().add(tValorField);
		tValorField.setColumns(10);
		
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(530, 309, 154, 40);
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String tString = tValorField.getText();
					tString = tString.replaceAll(",", ".");
					double tDouble =  java.lang.Double.parseDouble(tString);
					if(tDouble>=1 || tDouble<=0) throw new Exception();
					
					System.out.println("t=" + tDouble);
					
					tela.setT(tDouble);
					
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(frmPrincipal, "Insira um número válido entre 0 e 1", "Valor inválido", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frmPrincipal.getContentPane().add(btnAplicar);
		
		JButton btnTest = new JButton("test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tela.printArrayList();
				
			}
		});
		btnTest.setBounds(544, 389, 89, 23);
		frmPrincipal.getContentPane().add(btnTest);
		
		
		
	}
	
	private void resetar(){
		tela.resetar();
	}
	
	private void escondePoligonal(){
		
		tela.togglePoligonal();
		
		if(mostraPoligonal){
			btnEsconderPoligonal.setText("Mostrar poligonal");
		}
		else{
			btnEsconderPoligonal.setText("Esconder poligonal");
		}
		
		mostraPoligonal = !mostraPoligonal;
		
	}
	
	private void escondeCurva(){
		
		tela.toggleCurva();
		
		if(mostraCurva){
			btnEsconderCurva.setText("Mostrar curva");
		}
		else{
			btnEsconderCurva.setText("Esconder curva");
		}		
		
		mostraCurva = !mostraCurva;
	}
	
	private void escondePontos(){
		tela.togglePontos();
		
		if(mostraPontos){
			btnEsconderPontos.setText("Mostrar pontos");
		}
		else{
			btnEsconderPontos.setText("Esconder pontos");
		}
		
		mostraPontos = !mostraPontos;
	}
}
