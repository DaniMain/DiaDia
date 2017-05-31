package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoAiutoTest {
	
	private Comando aiuto;
	private Partita partita;
	
	@Before
	public void setUp() throws Exception {
		this.aiuto = new ComandoAiuto();
		this.partita = new Partita();
	}

	@Test
	public void testEsegui() {
		assertEquals("aiuto fine guarda interagisci posa prendi regala saluta vai ",this.aiuto.esegui(partita));
	}

}
