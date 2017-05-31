package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaneTest {
	
	private static final String OSSO = "osso";
	private Cane cane;
	private Partita partita;
	private Attrezzo lanterna;
	
	@Before
	public void setUp() throws Exception {
		this.lanterna = new Attrezzo("lanterna", 3);
		this.cane = new Cane("fido","BAU BAU",OSSO,this.lanterna);
		this.partita = new Partita();
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		this.partita.getStanzaCorrente().removeAttrezzo(new Attrezzo(OSSO,1));
	}

	@Test
	public void testAgisci_CaneNellAtrio() {
		assertEquals(20,this.partita.getGiocatore().getCfu());
		assertEquals("BAU BAU! Ma quanti bei CFU, me ne mangio giusto un paio! BAU BAU!",this.cane.agisci(this.partita));
		assertEquals(18,this.partita.getGiocatore().getCfu());
	}
	
	@Test (expected = NullPointerException.class)
	public void testRiceviRegalo_CaneNellAtrio_RegaloNull() {
		this.cane.riceviRegalo(null, partita);
	}
	
	@Test
	public void testRiceviRegalo_CaneNellAtrio_RegaloTorta() {
		Attrezzo torta = new Attrezzo("torta", 2);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("torta"));
		assertTrue(this.partita.getGiocatore().getBorsa().addAttrezzo(torta));
		assertEquals("GRR! Questo non è il mio cibo preferito!",this.cane.riceviRegalo(torta, partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("torta"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("torta"));
	}
	
	@Test
	public void testRiceviRegalo_CaneNellAtrio_RegaloOsso() {
		Attrezzo osso = new Attrezzo(OSSO, 3);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertTrue(this.partita.getGiocatore().getBorsa().addAttrezzo(osso));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		assertEquals("GNAM GNAM! Ecco per te un bel regalo! "+
				"Grazie! CIOMP CIOMP!",this.cane.riceviRegalo(osso, partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}
	
	@Test
	public void testRiceviRegalo_CaneNellAtrio_RegaloOsso_MaCaneNonHaNulla() {
		Attrezzo osso = new Attrezzo(OSSO, 3);
		this.cane.setRegalo(null);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertTrue(this.partita.getGiocatore().getBorsa().addAttrezzo(osso));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		assertEquals("GNAM GNAM! Grazie del regalo, "+
				"ma non ho nulla da darti...",this.cane.riceviRegalo(osso, partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(OSSO));
	}
	
}
