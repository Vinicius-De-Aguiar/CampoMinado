package Minado.Campo.Projeto.Logica;

import java.util.ArrayList;
import java.util.List;

import Minado.Campo.Projeto.Excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto;// por padrao começa falso
	private boolean minado;
	private boolean marcado;// campo aberto

	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;

	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;// se true significa q tem diagonal //diferença=1
		boolean colunaDiferente = coluna != vizinho.coluna;// se true significa q tem diagonal //diferença=1
		boolean diagonal = linhaDiferente && colunaDiferente;// se tiver na diagonal os 2 sao diferentes //diferença=2

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {// diagonal false
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {// diagonal true
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}

	}

	void alternarMarcacao() {// protege um campo, para nao ser aberto
		if (!aberto) {
			marcado = !marcado;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			if (minado) {
				throw new ExplosaoException();// explode
			}
			if (vizinhancaSegura()) {// enqaunto vizinhacaSegura continue expandido
				vizinhos.forEach(v -> v.abrir());// abre enquanto a vizinhança for segura
			}
			return true;

		} else {
			return false;
		}
	}

	boolean vizinhancaSegura() {

		return vizinhos.stream().noneMatch(v -> v.minado);// se falso, pelo menso 1 vizinho tem bomba
	}

	void minar() {
		minado = true;
	}

	public boolean isMinado() {
		return minado;
	}
		
	public boolean isMarcado() {
		return marcado;
	}

	
		
	 void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	// linhas e colunas
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v->v.minado).count();
	}
	
	void reiniciar() {
		aberto=false;
		minado=false;
		marcado=false;
	}
	
	public String toString() {
		if(marcado) {
			return "x";
		}else if(aberto && minado) {
			return "*";
		} else if(aberto && minasNaVizinhanca()>0) {
			return Long.toString(minasNaVizinhanca());
		} else if(aberto) {
			return " ";
		} else {
			return "?";
		}
	}
	

}
