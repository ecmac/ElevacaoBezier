package ui;

import javax.swing.JFrame;
import java.awt.EventQueue;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import java.awt.Color;
import javax.swing.border.LineBorder;
import mat.Ponto;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{
	
	private TelaGrafico tela;
	
	private boolean mostraCurva = true;
	JButton btnEsconderCurva;
	
	private boolean mostraPoligonal = true;
	JButton btnEsconderPoligonal;
	
	private boolean mostraPontos = true;
	JButton btnEsconderPontos;
	
	private double avalInc;
	private int numAval;
	
	private boolean apagarPonto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI(int numAval) {
		this.numAval = numAval;
		initialize();
	}
	
	public MainGUI() {
		this.numAval = 99;
		initialize();
	}

	public TelaGrafico getTela() {
		return tela;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		getContentPane().setBackground(new Color(204, 255, 255));
		setResizable(false);
		setTitle("Eleva\u00E7\u00E3o de grau de curva de "
				+ "B\u00E9zier - janela principal");
		setBounds(100, 100, 800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAvaliaes = new JLabel("Avalia\u00E7\u00F5es");
		lblAvaliaes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvaliaes.setBounds(663, 322, 100, 30);
		getContentPane().add(lblAvaliaes);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			
			//Número de avaliações modificado
			public void stateChanged(ChangeEvent arg0) {
				
				Integer integer = (Integer) spinner.getValue();
				numAval = integer.intValue();
				avalInc = calcularValorInc(numAval);
				tela.setAvalInc(avalInc);
				tela.repaint();
			}
		});
		spinner.setModel(new SpinnerNumberModel(new Integer(numAval), 
				new Integer(1), null, new Integer(1)));
		spinner.setBounds(689, 356, 48, 23);
		getContentPane().add(spinner);
		
		avalInc = calcularValorInc(numAval);
		
		tela = new TelaGrafico(avalInc);
		tela.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tela.setBackground(Color.WHITE);
		tela.setBounds(10, 11, 622, 499);
		getContentPane().add(tela);
		tela.setLayout(null);
		
		JButton btnResetar = new JButton("Resetar");
		btnResetar.setBounds(664, 24, 98, 40);
		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetar();
			}
		});
		getContentPane().add(btnResetar);
		
		btnEsconderCurva = new JButton("Esconder curva");
		btnEsconderCurva.setBounds(646, 75, 134, 40);
		btnEsconderCurva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escondeCurva();
			}
		});
		getContentPane().add(btnEsconderCurva);
		
		btnEsconderPontos = new JButton("Esconder pontos");
		btnEsconderPontos.setBounds(646, 126, 134, 40);
		btnEsconderPontos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escondePontos();
			}
		});
		getContentPane().add(btnEsconderPontos);
		
		btnEsconderPoligonal = new JButton("Esconder poligonal");
		btnEsconderPoligonal.setBounds(642, 178, 142, 40);
		btnEsconderPoligonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escondePoligonal();
			}
		});
		getContentPane().add(btnEsconderPoligonal);
		
		JButton btnElevarGrau = new JButton("Elevar grau");
		btnElevarGrau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Ponto> novosPontos = tela.elevarGrau();
				tela.setPontos(novosPontos);
				
				tela.repaint();
				
			}
		});
		btnElevarGrau.setBounds(659, 271, 108, 40);
		getContentPane().add(btnElevarGrau);
		
		JCheckBox chckbxClicarApagar = new JCheckBox("Clicar \u00E9 apagar");
		chckbxClicarApagar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				apagarPonto = chckbxClicarApagar.isSelected();
				tela.setApagarPonto(apagarPonto);
			}
		});
		chckbxClicarApagar.setBackground(new Color(255, 153, 0));
		chckbxClicarApagar.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxClicarApagar.setBounds(652, 413, 123, 40);
		getContentPane().add(chckbxClicarApagar);
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
	
	private double calcularValorInc(int numAval) {
		return 1.0/((double)numAval + 1.0);
	}
}
