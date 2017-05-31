package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoFineTest {
	
	private Comando fine;

	@Test
	public void testEsegui() {
		this.fine = new ComandoFine();
		assertEquals("Grazie per aver giocato!",this.fine.esegui(new Partita()));
	}

}
