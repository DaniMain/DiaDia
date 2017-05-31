package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StregaTest {
	
	private Partita partita;
	private Strega strega;
	private Attrezzo spada, lancia;
	private Stanza atrio, est, ovest;
	
	@Before
	public void setUp() throws Exception {
		
		this.strega = new Strega("strega","Ciao, sono una strega");
		
		this.partita = new Partita();
		
		this.spada = new Attrezzo("spada",5);
		this.lancia = new Attrezzo("lancia",4);
		
		this.atrio = this.partita.getStanzaCorrente();
		
		this.est = this.partita.getStanzaCorrente().getStanzaAdiacente("est");
		this.est.addAttrezzo(spada);
		this.est.addAttrezzo(lancia);
		
		this.ovest = this.partita.getStanzaCorrente().getStanzaAdiacente("ovest");
		/* a ovest dell'atrio c'è una stanza già con l'attrezzo piediporco */
		this.ovest.addAttrezzo(spada);
		this.ovest.addAttrezzo(lancia);
		
	}

	@Test
	public void testAgisci_StregaNellAtrio_NoSalutato() {
		/* a nord c'è la biblioteca ma essendo bloccata non viene presa in considerazione */
		/* a est c'è una stanza con 2 attrezzi */
		/* a ovest una stanza già con 3 attrezzi */
		/* a sud dell'atrio c'è una stanza già con l'attrezzo lanterna, quindi con 1 attrezzo soltanto */
		assertEquals("Mascalzone, non mi hai salutato.\n"+
				"Adesso ti trasferisco nella stanza adiacente con meno attrezzi",
				this.strega.agisci(partita));
		assertSame(atrio,this.partita.getStanzaCorrente().getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testAgisci_StregaNellAtrio_HaSalutato() {
		/* a nord c'è la biblioteca ma essendo bloccata non viene presa in considerazione */
		/* a est c'è una stanza con 2 attrezzi */
		/* a ovest una stanza già con 3 attrezzi */
		/* a sud dell'atrio c'è una stanza già con l'attrezzo lanterna, quindi con 1 attrezzo soltanto */
		this.strega.setHaSalutato(true);
		assertEquals("Sei stato gentile nel salutarmi,\n"+
			"ti trasferisco nella stanza adiacente con più attrezzi",this.strega.agisci(partita));
		assertSame(atrio,this.partita.getStanzaCorrente().getStanzaAdiacente("est"));
	}
	
	@Test
	public void testRiceviRegalo_SpadaELancia() {
		Giocatore giocatore = this.partita.getGiocatore();
		giocatore.getBorsa().addAttrezzo(lancia);
		giocatore.getBorsa().addAttrezzo(spada);
		assertEquals("AH AH AH AH AH! "+
				"Sciocco, l'attrezzo è mio e non te lo darò mai più!",this.strega.riceviRegalo(lancia, partita));
		assertFalse(giocatore.getBorsa().hasAttrezzo("lancia"));
		assertTrue(giocatore.getBorsa().hasAttrezzo("spada"));
		assertEquals(1,giocatore.getBorsa().getNumeroAttrezzi());
		assertEquals("AH AH AH AH AH! "+
				"Sciocco, l'attrezzo è mio e non te lo darò mai più!",this.strega.riceviRegalo(spada, partita));
		assertFalse(giocatore.getBorsa().hasAttrezzo("spada"));
		assertEquals(0,giocatore.getBorsa().getNumeroAttrezzi());
	}

}
