package Minado.Campo.Projeto.Visao;

import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Minado.Campo.Projeto.Excecao.SairException;
import Minado.Campo.Projeto.Logica.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar=true;
			
			while(continuar) {
				cicloDoJogo();
			
			String entrada = JOptionPane.showInputDialog("Deseja jogar denovo? (S/n) ");
			
			if("n".equalsIgnoreCase(entrada)) {
				continuar=false;
			}else {
				tabuleiro.reiniciar();
			}
				
			}
			
		}catch (Exception e) {
			System.out.println("Volte sempre!!");
		}
		
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				JOptionPane.showInternalMessageDialog(null,"Observe as opções e veja qual irá marcar\n"+ tabuleiro);
				String digitado=capturarValorDigitado("Digite (A linha, A coluna): ");
				
				
				Iterator<Integer> xy=Arrays.stream(digitado.split(","))
				.map(e->Integer.parseInt(e.trim())).iterator();
				
			
				digitado=capturarValorDigitado("1- Abrir ou 2-(Des)Marcar");
				try {
					
					if("1".equals(digitado)) {
						tabuleiro.abrir(xy.next(), xy.next());
					}else if("2".equals(digitado)) {
						tabuleiro.marcar(xy.next(), xy.next());
						}
					else {
							JOptionPane.showInternalMessageDialog(null, "Você digitou errado, tente novamente");
						}
					
				}catch (Exception e) {
					JOptionPane.showInternalMessageDialog(null, "Você digitou errado, tente novamente");
				}
				
				
				
			}
			
			JOptionPane.showInternalMessageDialog(null,tabuleiro);
			JOptionPane.showInternalMessageDialog(null,"Você Ganhou!!");
			
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,tabuleiro);
			JOptionPane.showInternalMessageDialog(null,"Você Perdeu!!");
			
		}
		
	}
	
	private String capturarValorDigitado(String texto) {
		try {
		System.out.println(texto);
		String entrada = JOptionPane.showInputDialog(texto);
		if("sair".equalsIgnoreCase(entrada)){
			throw new SairException();
		}
		return entrada;
		}catch (Exception e) {
			 JOptionPane.showInternalMessageDialog(null, "Você digitou algo de errado, tente novamente");
			 return "Erro";
		}
	}
		
	
	
	
}
