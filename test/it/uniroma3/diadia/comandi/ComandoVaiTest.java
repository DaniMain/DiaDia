package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	
	private Comando vai;
	private Partita partita;

	@Before
	public void setUp() throws Exception {
		this.vai = new ComandoVai();
		this.partita = new Partita();
	}

	@Test
	public void testEsegui_NullNellAtrio() {
		assertEquals("Dove vuoi andare? Devi specificare una direzione\n"+
				"Uscite: nord sud est ovest",this.vai.esegui(partita));
	}
	
	@Test
	public void testEsegui_NordDellAtrio() {
		vai.setParametro("nord");
		assertEquals("Non si può andare a nord",this.vai.esegui(partita));
	}
	
	@Test
	public void testEsegui_SudDellAtrio() {
		Stanza sud = this.partita.getStanzaCorrente().getStanzaAdiacente("sud");
		vai.setParametro("sud");
		assertEquals("Sei entrato in: Aula N10",this.vai.esegui(partita));
		assertSame(sud,this.partita.getStanzaCorrente());
		assertEquals(19,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testEsegui_EstDellAtrio() {
		Stanza est = this.partita.getStanzaCorrente().getStanzaAdiacente("est");
		vai.setParametro("est");
		assertEquals("Sei entrato in: Aula N11",this.vai.esegui(partita));
		assertSame(est,this.partita.getStanzaCorrente());
		assertEquals(19,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testEsegui_OvestDellAtrio() {
		Stanza ovest = this.partita.getStanzaCorrente().getStanzaAdiacente("ovest");
		vai.setParametro("ovest");
		assertEquals("Sei entrato in: Segreteria",this.vai.esegui(partita));
		assertSame(ovest,this.partita.getStanzaCorrente());
		assertEquals(19,this.partita.getGiocatore().getCfu());
	}

}
