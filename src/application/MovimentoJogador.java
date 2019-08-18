package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovimentoJogador extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	
	public static final int ESCALA = 2;
	public static final int LARGURA = 400 * ESCALA;
	public static final int ALTURA = 300 * ESCALA;
	public static final int TAM_JOGADOR = 30;
	
	int velocidade = 1;
	int jogadorPosX = 390; 
	boolean praCima = false;
	boolean praEsquerda = false;
	boolean praBaixo = false;
	boolean praDireita = false;
	int jogadorPosY = 210;
	
	public static void main(String [] args) {
		
		
		JFrame janela = new JFrame();
		janela.setSize(LARGURA, ALTURA);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		
		MovimentoJogador canvas = new MovimentoJogador();
		canvas.setBounds(0, 0, LARGURA, ALTURA);
		canvas.setVisible(true);
		janela.setLayout(null);
		janela.add(canvas);
		
		janela.addKeyListener(canvas);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(jogadorPosX, jogadorPosY, TAM_JOGADOR, TAM_JOGADOR);
	}
	
	public void run() {
		while(true) {
			atualizar();
			repaint();
			dormeUmPouco();
		}
	}
	
	public void atualizar() {
		if(praCima && jogadorPosY > 0) {
			jogadorPosY -= velocidade;
		}
		else if(praEsquerda && jogadorPosX > 0) {
			jogadorPosX -= velocidade;
		}
		else if(praBaixo && jogadorPosY < (561 - TAM_JOGADOR)) {
			jogadorPosY += velocidade;
		} else if(praDireita && jogadorPosX < (785 - TAM_JOGADOR)) {
			jogadorPosX += velocidade;
		}
		
	}
	
	public void dormeUmPouco() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MovimentoJogador() {
		Thread processoDoJogo = new Thread(this);
		processoDoJogo.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			praCima = true;
		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			praEsquerda = true;
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			praBaixo = true;
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			praDireita = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			praCima = false;
		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			praEsquerda = false;
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			praBaixo = false;
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			praDireita = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
