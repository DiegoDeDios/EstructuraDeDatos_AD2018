package gameOfLife;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
public class GameOfLifeWindow {
	private JFrame frame;
	private Game game;
	public GameOfLifeWindow() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    game = new Game();
		game.setBounds(0, 0, 800, 528);
		frame.getContentPane().add(game);
		game.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton Start = new JButton("Jugar");
		Start.setFont(new Font("TI-Nspire", Font.ITALIC, 13));
		Start.setBounds(0, 535, 100, 36);
		frame.getContentPane().add(Start);
		JButton jbColor = new JButton("Color");
		jbColor.setFont(new Font("TI-Nspire", Font.ITALIC, 13));
		jbColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color cambiarClr= JColorChooser.showDialog(null, "Escoge el color", Color.CYAN);
				game.setColorCelulas(cambiarClr);
			}
		});
		jbColor.setBounds(102, 535, 100, 36);
		frame.getContentPane().add(jbColor);
		
		JButton jbStats = new JButton("Estadísticas");
		jbStats.setFont(new Font("TI-Nspire", Font.ITALIC, 13));
		jbStats.setBounds(203, 534, 100, 36);
		frame.getContentPane().add(jbStats);
		jbStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.contarPoblacion();
				jbStats.setEnabled(false);
				
			}
		});
		
		JButton jbSalir = new JButton("Salir");
		jbSalir.setFont(new Font("TI-Nspire", Font.BOLD, 13));
		jbSalir.setBounds(677, 535, 100, 35);
		frame.getContentPane().add(jbSalir);
		
		Start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(game.getPlay()==false){
				game.setPlay(true);
				Start.setText("Pausar");
				}
				else{
					Start.setText("Reanudar");
					game.setPlay(false);
				}
			}
		});
		jbSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeWindow window = new GameOfLifeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
