package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;

public class PartitaTest {

	private Partita partita;
	private Labirinto labirinto;
	private Stanza biblioteca;

	@Before
	public void setUp() throws Exception {

		this.partita = new Partita();
		this.labirinto = new Labirinto();
		this.biblioteca = this.labirinto.getStanzaVincente();

	}

	@Test
	public void testVinta_StoInBiblioteca() {
		this.partita.setStanzaCorrente(this.biblioteca);
		assertTrue(this.partita.vinta());
	}

	@Test
	public void testVinta_NonStoInBiblioteca() {
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testIsFinita_StoInBibliotecaEHoCfuPositivi() {
		this.partita.setStanzaCorrente(this.biblioteca);
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testIsFinita_NonStoInBibliotecaECfuFiniti() {
		this.partita.getGiocatore().setCfu(0);
		assertEquals(0, this.partita.getGiocatore().getCfu());
		assertFalse(this.partita.vinta());
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testIsFinita_NonStoInBibliotecaECfuPositivi() {
		assertFalse(this.partita.vinta());
		assertFalse(this.partita.isFinita());
	}

	@Test
	public void testSetStanzaCorrente_MiSpostoInAulaN10() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
	}

}

