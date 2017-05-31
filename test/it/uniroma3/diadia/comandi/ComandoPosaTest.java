package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPosaTest {
	
	private static final String OSSO = "osso";
	private static final String SPADA = "spada";
	private Partita partita;
	private Comando posaSpada, posaOsso, nullo;

	@Before
	public void setUp() throws Exception {
		this.partita = new Partita();
		this.posaSpada = new ComandoPosa();
		this.posaSpada.setParametro(SPADA);
		this.posaOsso = new ComandoPosa();
		this.posaOsso.setParametro(OSSO);
		this.nullo = new ComandoPosa();
	}
	
	@Test
	public void testEsegui_PosaNulloNellAtrio() {
		assertEquals("Cosa vuoi posare? Devi specificare un attrezzo",this.nullo.esegui(this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}

	@Test
	public void testEsegui_PosaSpadaNellAtrioAvendoSpadaInBorsa() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(SPADA,5));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(SPADA));
		assertEquals("Hai posato l'attrezzo: spada (5kg)",this.posaSpada.esegui(this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testEsegui_PosaSpadaNellAtrioSenzaAvereSpadaInBorsa() {
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(SPADA));
		assertEquals("spada non è presente nella tua borsa",this.posaSpada.esegui(this.partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testEsegui_PosaOssoNellAulaN10DopoAverloPresoNellAtrio() {
		Comando prendi = new ComandoPrendi();
		prendi.setParametro(OSSO);
		assertEquals("Hai preso l'attrezzo: osso (1kg)",prendi.esegui(this.partita));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		assertEquals("Hai posato l'attrezzo: osso (1kg)",this.posaOsso.esegui(this.partita));		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}

}
