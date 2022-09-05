package Minado.Campo.Projeto;

import javax.swing.JOptionPane;

import Minado.Campo.Projeto.Logica.Tabuleiro;
import Minado.Campo.Projeto.Visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {
		
		boolean verificador=true;
		while(verificador==true) {
			
			try {
				
		String l = JOptionPane.showInputDialog("Digita a quantidade de linhas ");
		String c = JOptionPane.showInputDialog("Digite a quantidade de colunas ");
		String m = JOptionPane.showInputDialog("Digite a quantidade de bombas ");
		int linhas=Integer.parseInt(l);
		int colunas=Integer.parseInt(c);
		int minas=Integer.parseInt(m);
		
		
		Tabuleiro tabuleiro= new Tabuleiro(linhas, colunas, minas);

	
		new TabuleiroConsole(tabuleiro);
		verificador=false;
			}catch (Exception e) {
				JOptionPane.showInternalMessageDialog(null, "Opa, voce digitou letras onde não deveria,"
						+ " tente denovo");
			}
		
		}
		
	}

}
