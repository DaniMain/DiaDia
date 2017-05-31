package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoGuardaTest {
	
	private Comando guarda;

	@Before
	public void setUp() throws Exception {
		this.guarda = new ComandoGuarda();
	}

	@Test
	public void testEsegui() {
		assertEquals("Atrio\nUscite: nord sud est ovest\nAttrezzi nella stanza: osso (1kg)\n\n"+
				"Qui c'è Amelia!\n\nStanza bloccata a nord!\nPer sbloccarla devi posare in questa stanza un attrezzo chiave\n"+
				"Borsa vuota\nHai ancora 20 CFU",this.guarda.esegui(new Partita()));
	}

}
