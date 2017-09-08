package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {
	private boolean isPlaying;
	private int x=60,y=40; //Rows, Columns
	private boolean[][] currentM= new boolean[y][x], nextM= new boolean [y][x]; //Matriz de booleanos en arreglo bidimensional (Aplicar estructura aqui)
	private SparseArray <Boolean> arregloDisperso;
	private boolean play;
	private Timer iniciar;
	private TimerTask thread;
	private Color celulas;
	private int generaciones;
	public int GameSpeed;
	public Game(){
		super();
		this.generaciones=0;
		this.GameSpeed=100;
		this.arregloDisperso=new SparseArray(this.x,this.y);
		this.setPreferredSize(new Dimension(1024,768));
		this.setBounds(0, 0, 450, 228);
		this.setBackground(Color.BLACK);
		this.celulas=Color.CYAN;
		this.play=false;
		//this.setLayout(null);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=x*e.getX()/Game.this.getWidth();
				int i=y*e.getY()/Game.this.getHeight();
				Game.this.currentM[i][j]=!Game.this.currentM[i][j];
				Game.this.repaint();
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				int j=x*e.getX()/Game.this.getWidth();
				int i=y*e.getY()/Game.this.getHeight();
				Game.this.currentM[i][j]=!Game.this.currentM[i][j];
				Game.this.repaint();
				
			}
		});
		this.iniciar= new Timer();
		this.thread= new TimerTask(){
			public void run(){
                if(play){
                    for(int i = 0; i < y; i++){
                        for(int j = 0; j < x; j++){
                            nextM[i][j] = Jugar(i,j);
                            System.out.println(System.currentTimeMillis());
                        }
                    }
                   Game.this.generaciones++;
                    for(int i = 0; i < y; i++){
                        for(int j = 0; j < x; j++){
                            Game.this.currentM[i][j] = Game.this.nextM[i][j];
                        }
                    }
                    Game.this.repaint();
                    //Game.this.arregloDisperso.printArray(); * O(n^2)
                }
               
            }
		};
		this.iniciar.scheduleAtFixedRate(thread, 0, this.GameSpeed);
		this.repaint();
		System.out.println(System.currentTimeMillis());
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		for(int i=0;i<y;i++){
			int hei=i*this.getHeight()/y;
			g.drawLine(0, hei, this.getWidth(),hei);
		}
		for(int i=0;i<x;i++){
			int wid=i*this.getWidth()/x;
			g.drawLine(wid, 0, wid,this.getWidth());
		}
		for(int i=0; i<y;i++){
			for(int j=0; j<x;j++){
				if(this.currentM[i][j]){
					g.setColor(celulas);
					int height=i*this.getHeight()/y;
					int width=j*this.getWidth()/x;
					g.fillRect(width, height, this.getWidth()/x, this.getHeight()/y);
			}
			}
		}
	}
	 private boolean Jugar(int i, int j){ //Reglas del "juego" que se iteran cada generacion
	        int neighbors = 0;
	        if(j > 0){
	            if(currentM[i][j-1]){
	            	neighbors++;
	            }
	            if(i>0) if(currentM[i-1][j-1]){
	            	neighbors++;
	            }
	            if(i<y-1) if(currentM[i+1][j-1]){
	            	neighbors++;
	            }
	        }
	        if(j < x-1){
	            if(currentM[i][j+1]) {
	            	neighbors++;
	            }
	            if(i>0) if(currentM[i-1][j+1]) {
	            	neighbors++;
	            }
	            if(i<y-1) if(currentM[i+1][j+1]){
	            	neighbors++;
	            }
	        }
	        if(i>0) if(currentM[i-1][j]){
	        	neighbors++;
	        }
	        if(i<y-1) if(currentM[i+1][j]) {
	        	neighbors++;
	        }
	        if(neighbors == 3) {
	        	return true;
	        }
	        if(currentM[i][j] && neighbors == 2) {
	        	return true;
	        }
	        return false;
	    }
	public void setPlay(boolean play){
		this.play=play;
	}
	public boolean getPlay(){
		return this.play;
	}
	public void setColorCelulas(Color celulas){
		this.celulas=celulas;
		this.repaint();
	}
	public void setGameSpeed(int GameSpeed){
		this.GameSpeed=GameSpeed;
		this.repaint();
	}
	public void contarPoblacion(){
		for(int i=0;i<y;i++){
			for(int j=0;j<x;j++){
				if(this.currentM[i][j]!=false){
					this.arregloDisperso.addData(this.currentM[i][j], j, i);
				}
			}
		}
		 JOptionPane.showMessageDialog(this, "+Generacion Actual:"+this.generaciones+"\n"
		 		+ "+Poblacion Neta:"+this.arregloDisperso.elementCount()+"\n +Se ha generado un mapa de poblacion");
		 this.crearDocumento();
	}
	public void crearDocumento(){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Generación.txt"));
			for(int i=0;i<y;i++){
				for(int j=0;j<x;j++){
					if(this.arregloDisperso.getData(j, i)!=null){
						pw.print("1 ");
					}
					else{
						pw.print("0 ");
					}
				}
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
