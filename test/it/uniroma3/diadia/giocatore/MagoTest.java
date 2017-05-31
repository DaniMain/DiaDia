package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class MagoTest {
	
	private static final String SPADA = "spada";
	private Mago mago;
	private Partita partita;
	private Attrezzo spada;
	
	@Before
	public void setUp() throws Exception {
		this.spada = new Attrezzo(SPADA, 2);
		this.mago = new Mago("mago","Ciao, sono un mago",this.spada);
		this.partita = new Partita();
	}

	@Test
	public void testAgisci_MagoNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(mago);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertEquals("Sei un vero simpaticone, con una mia magica azione,\n"
				+ "troverai un nuovo oggetto per il tuo bel borsone!",
				this.mago.agisci(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertEquals("Mi spiace, ma non ho piu' nulla...",this.mago.agisci(partita));
	}
	
	@Test (expected = NullPointerException.class)
	public void testRiceviRegalo_MagoNellAtrio_RegaloNull() {
		this.mago.riceviRegalo(null, partita);
	}
	
	@Test
	public void testRiceviRegalo_MagoNellAtrio_RegaloSpada_1Dimezza() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertEquals("Ecco fatto, il peso è stato dimezzato!",this.mago.riceviRegalo(this.spada, partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertEquals(1,this.spada.getPeso());
		assertEquals("Mi spiace, ma non posso più dimezzare nulla...",this.mago.riceviRegalo(this.spada, partita));
		assertEquals(1,this.spada.getPeso());
	}
	
	@Test
	public void testRiceviRegalo_MagoNellAtrio_RegaloSpada_2Dimezza() {
		this.mago.setDimezza(2);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertEquals("Ecco fatto, il peso è stato dimezzato!",this.mago.riceviRegalo(this.spada, partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertEquals(1,this.spada.getPeso());
		assertEquals("Ecco fatto, il peso è stato dimezzato!",this.mago.riceviRegalo(this.spada, partita));
		assertEquals(0,this.spada.getPeso());
		assertEquals("Mi spiace, ma non posso più dimezzare nulla...",this.mago.riceviRegalo(this.spada, partita));
		assertEquals(0,this.spada.getPeso());
	}
	
}
