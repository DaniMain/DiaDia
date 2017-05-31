package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoNonValidoTest {

	@Test
	public void testEsegui() {
		Comando nonValido = new ComandoNonValido();
		assertEquals("Comando non valido",nonValido.esegui(new Partita()));
	}

}
