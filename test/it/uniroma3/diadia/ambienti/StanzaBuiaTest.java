package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class StanzaBuiaTest {
	
	private StanzaBuia buia;
	private Stanza vuota;
	private Attrezzo lanterna;
	private Giocatore giocatore;

	@Before
	public void setUp() throws Exception {
		this.buia = new StanzaBuia("buia","lanterna");
		this.vuota = new Stanza("vuota");
		this.lanterna = new Attrezzo("lanterna",3);
		this.giocatore = new Giocatore();
	}

	@Test
	public void testIsBuia_BuiaSenzaLanterna() {
		assertTrue(this.buia.isBuia());
		assertEquals("qui c'è buio pesto",this.buia.getDescrizione());
	}
	
	@Test
	public void testIsBuia_BuiaConLanterna() {
		assertTrue(this.buia.isBuia());
		assertEquals("qui c'è buio pesto",this.buia.getDescrizione());
		assertTrue(this.buia.addAttrezzo(this.lanterna));
		assertFalse(this.buia.isBuia());
		assertNotEquals("qui c'è buio pesto",this.buia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizione_VuotaNotBuia() {
		assertNotEquals("qui c'è buio pesto",this.vuota.getDescrizione());
	}
	
	@Test
	public void testEntroInVuotaDoveCELanternaCosiLaPrendoELaPosoInBuia() {
		assertTrue(this.vuota.addAttrezzo(this.lanterna));
		assertTrue(this.giocatore.prendi(this.lanterna, this.vuota));
		assertTrue(this.buia.isBuia());
		this.giocatore.posa(this.lanterna.getNome(), this.buia);
		assertFalse(this.buia.isBuia());
	}
	
	@Test
	public void testEntroInBuiaConLanternaCosiLaPrendoELaPosoInVuota() {
		assertTrue(this.buia.addAttrezzo(this.lanterna));
		assertFalse(this.buia.isBuia());
		assertTrue(this.giocatore.prendi(this.lanterna, this.buia));
		assertTrue(this.buia.isBuia());
		this.giocatore.posa(this.lanterna.getNome(), this.vuota);
	}
	
	@Test
	public void testEntroInBuiaConLanternaCosiLaPrendoELaPosoInUnAltraBuia() {
		assertTrue(this.buia.addAttrezzo(this.lanterna));
		assertFalse(this.buia.isBuia());
		assertTrue(this.giocatore.prendi(this.lanterna, this.buia));
		assertTrue(this.buia.isBuia());
		StanzaBuia altraBuia = new StanzaBuia("altra buia","lanterna");
		assertTrue(altraBuia.isBuia());
		this.giocatore.posa(this.lanterna.getNome(), altraBuia);
		assertFalse(altraBuia.isBuia());
	}
	
}
