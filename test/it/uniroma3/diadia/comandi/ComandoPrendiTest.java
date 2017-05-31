package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	
	private static final String LANTERNA = "lanterna";
	private static final String OSSO = "osso";
	private Partita partita;
	private Comando prendiOsso, prendiLanterna, nullo;

	@Before
	public void setUp() throws Exception {
				
		this.partita = new Partita();
		this.prendiOsso = new ComandoPrendi();
		this.prendiOsso.setParametro(OSSO);
		this.prendiLanterna = new ComandoPrendi();
		this.prendiLanterna.setParametro(LANTERNA);
		this.nullo = new ComandoPrendi();
	}
	
	@Test
	public void testEsegui_PrendiNullNellAtrio() {
		assertEquals("Cosa vuoi prendere? Devi specificare un attrezzo",nullo.esegui(this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}

	@Test
	public void testEsegui_PrendiOssoNellAtrio() {
		assertEquals("Hai preso l'attrezzo: osso (1kg)",prendiOsso.esegui(this.partita));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
	}
	
	@Test
	public void testEsegui_PrendiLanternaNellAtrio() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(LANTERNA));
		assertEquals("Non puoi prendere l'attrezzo lanterna",prendiLanterna.esegui(this.partita));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(LANTERNA));
	}
	
	@Test
	public void testEsegui_PrendiLanternaNellAulaN10() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertTrue(this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("lanterna", 3)));
		assertEquals("Hai preso l'attrezzo: lanterna (3kg)",prendiLanterna.esegui(this.partita));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(LANTERNA));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(LANTERNA));
	}
	
	@Test
	public void testEsegui_PrendiOssoNellAulaN10() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertEquals("Non puoi prendere l'attrezzo osso",prendiOsso.esegui(this.partita));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}
	
}
