package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.*;

public class ComandoInteragisciTest {
	
	private Comando interagisci;
	private Partita partita;
	private Attrezzo spada;
	private AbstractPersonaggio cane,mago,strega;
	
	@Before
	public void setUp() throws Exception { 
		this.interagisci = new ComandoInteragisci();
		
		this.partita = new Partita();
		
		this.spada = new Attrezzo("spada", 5);
		
		this.cane = new Cane("fido","Bau bau","osso",null);
		this.mago = new Mago("merlino", "ciao", this.spada);
		this.strega = new Strega("amelia", "ciao");
		
	}
	
	@Test
	public void testEsegui_NessunPersonaggioAEst() {
		assertNull(this.partita.getStanzaCorrente().getStanzaAdiacente("est").getPersonaggio());
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("est"));
		assertEquals("Con chi vuoi interagire?",this.interagisci.esegui(partita));
	}

	@Test
	public void testEsegui_CaneNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		assertEquals("BAU BAU! Ma quanti bei CFU, "+
				"me ne mangio giusto un paio! BAU BAU!",this.interagisci.esegui(partita));
		assertEquals(18,this.partita.getGiocatore().getCfu());
		assertEquals("BAU BAU! Ma quanti bei CFU, "+
				"me ne mangio giusto un paio! BAU BAU!",this.interagisci.esegui(partita));
		assertEquals(16,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testEsegui_MagoNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(mago);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals("Sei un vero simpaticone, " +
				"con una mia magica azione,\ntroverai un nuovo oggetto " +
				"per il tuo bel borsone!",this.interagisci.esegui(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals("Mi spiace, ma non ho piu' nulla...",this.interagisci.esegui(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testEsegui_StregaNellAtrio_NoSalutato() {
		this.partita.getStanzaCorrente().setPersonaggio(strega);
		Stanza atrio = this.partita.getStanzaCorrente();
		assertEquals("Mascalzone, non mi hai salutato.\n"+
				"Adesso ti trasferisco nella stanza adiacente con meno attrezzi",this.interagisci.esegui(partita));
		assertSame(atrio,this.partita.getStanzaCorrente().getStanzaAdiacente("ovest"));
	}
	
	@Test
	public void testEsegui_StregaNellAtrio_HaSalutato() {
		this.partita.getStanzaCorrente().setPersonaggio(strega);
		Stanza atrio = this.partita.getStanzaCorrente();
		this.strega.setHaSalutato(true);
		atrio.getStanzaAdiacente("sud").addAttrezzo(spada);
		assertEquals("Sei stato gentile nel salutarmi,\n"+
				"ti trasferisco nella stanza adiacente con più attrezzi",this.interagisci.esegui(partita));
		assertSame(atrio,this.partita.getStanzaCorrente().getStanzaAdiacente("est"));
	}

}
