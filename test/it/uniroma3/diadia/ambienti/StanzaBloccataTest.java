package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class StanzaBloccataTest {
	
	private static final String NORD = "nord";
	private static final String VUOTA = "vuota";
	private StanzaBloccata chiaveNord;
	private Stanza vuota;
	private Attrezzo chiave;
	private Giocatore giocatore;

	@Before
	public void setUp() throws Exception {
		this.chiaveNord = new StanzaBloccata("bloccata","chiave",NORD);
		this.vuota = new Stanza(VUOTA);
		this.chiave = new Attrezzo("chiave",3);
		this.giocatore = new Giocatore();
	}

	@Test
	public void testIsBloccata_ChiaveNord() {
		assertTrue(this.chiaveNord.isBloccata());
	}
	
	@Test
	public void testIsBloccata_ChiaveNordConChiave() {
		assertTrue(this.chiaveNord.addAttrezzo(this.chiave));
		assertFalse(this.chiaveNord.isBloccata());
	}
	
	@Test
	public void testGetStanzaAdiacente_VuotaANordDiChiaveNordMaChiaveNordBloccata() {
		this.chiaveNord.impostaStanzaAdiacente(NORD, this.vuota);
		assertTrue(this.chiaveNord.isBloccata());
		assertNull(this.chiaveNord.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testGetStanzaAdiacente_VuotaAEstDiChiaveNordEChiaveNordBloccata() {
		this.chiaveNord.impostaStanzaAdiacente("est", this.vuota);
		assertTrue(this.chiaveNord.isBloccata());
		assertEquals(VUOTA,this.chiaveNord.getStanzaAdiacente("est").getNome());
	}
	
	@Test
	public void testGetStanzaAdiacente_VuotaANordDiChiaveNordEChiaveNordSbloccata() {
		this.chiaveNord.impostaStanzaAdiacente(NORD, this.vuota);
		assertTrue(this.chiaveNord.isBloccata());
		assertTrue(this.chiaveNord.addAttrezzo(this.chiave));
		assertFalse(this.chiaveNord.isBloccata());
		assertEquals(VUOTA,this.chiaveNord.getStanzaAdiacente(NORD).getNome());
	}
	
	@Test
	public void testGetStanzaAdiacente_VuotaANordDiChiaveNordEPrendoChiaveDaAltraStanzaEPosoInChiaveNord() {
		Stanza conChiave = new Stanza ("stanza della chiave");
		conChiave.addAttrezzo(this.chiave);
		this.chiaveNord.impostaStanzaAdiacente("est", conChiave);
		this.chiaveNord.impostaStanzaAdiacente(NORD, this.vuota);
		assertTrue(this.chiaveNord.isBloccata());
		assertTrue(this.giocatore.prendi(this.chiave, conChiave));
		this.giocatore.posa("chiave", this.chiaveNord);
		assertFalse(this.chiaveNord.isBloccata());
		assertEquals(VUOTA,this.chiaveNord.getStanzaAdiacente(NORD).getNome());
	}

}
