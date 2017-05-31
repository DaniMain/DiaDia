package it.uniroma3.it.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.*;

public class ComandoPrendiTest {
	
	private static final String LANTERNA = "lanterna";
	private static final String OSSO = "osso";
	private Partita partita;
	private Comando osso, lanterna, nullo;

	@Before
	public void setUp() throws Exception {
				
		this.partita = new Partita();
		this.osso = new ComandoPrendi(OSSO);
		this.lanterna = new ComandoPrendi(LANTERNA);
		this.nullo = new ComandoPrendi(null);
	}
	
	@Test
	public void testEsegui_PrendiNullNellAtrio() {
		nullo.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}

	@Test
	public void testEsegui_PrendiOssoNellAtrio() {
		osso.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
	}
	
	@Test (expected = NullPointerException.class)
	public void testEsegui_PrendiLanternaNellAtrio() {
		lanterna.esegui(this.partita);
	}
	
	@Test
	public void testEsegui_PrendiLanternaNellAulaN10() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		lanterna.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(LANTERNA));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(LANTERNA));
	}
	
	@Test (expected = NullPointerException.class)
	public void testEsegui_PrendiOssoNellAulaN10() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		osso.esegui(this.partita);
	}
	
}
