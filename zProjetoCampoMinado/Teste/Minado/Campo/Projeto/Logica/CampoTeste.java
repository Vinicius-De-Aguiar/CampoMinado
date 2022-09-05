package Minado.Campo.Projeto.Logica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Minado.Campo.Projeto.Excecao.ExplosaoException;

class CampoTeste {

	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		 campo= new Campo(3,3);
	}
	
	@Test
	void testeVizinhoRealD1() {
		Campo vizinho=new Campo(3,2);
		
		boolean resultado=campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);//se der certo retorna true
	}
	@Test
	void testeVizinhoRealD2() {
		Campo vizinho=new Campo(2,2);
		
		boolean resultado=campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);//se der certo retorna true
	}
	
	@Test
	void testeAlternarPadraoMarcacao() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao2Chamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class,()->{
			campo.abrir();
		});
	}
	

}
