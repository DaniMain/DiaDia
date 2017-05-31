package it.uniroma3.it.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class ComandoPosaTest {
	
	private static final String OSSO = "osso";
	private static final String SPADA = "spada";
	private Partita partita;
	private Comando spada, osso, nullo;

	@Before
	public void setUp() throws Exception {
		
		this.partita = new Partita();
		this.spada = new ComandoPosa(SPADA);
		this.osso = new ComandoPosa(OSSO);
		this.nullo = new ComandoPosa(null);
		
	}
	
	@Test
	public void testEsegui_PosaNulloNellAtrio() {
		this.nullo.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}

	@Test
	public void testEsegui_PosaSpadaNellAtrioAvendoSpadaInBorsa() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(SPADA,5));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(SPADA));
		this.spada.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testEsegui_PosaSpadaNellAtrioSenzaAvereSpadaInBorsa() {
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(SPADA));
		this.spada.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testEsegui_PosaOssoNellAulaN10DopoAverloPresoNellAtrio() {
		Comando prendi = new ComandoPrendi(OSSO);
		prendi.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		this.osso.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}

}
