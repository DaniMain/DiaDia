package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;
import it.uniroma3.diadia.giocatore.Strega;

public class ComandoSalutaTest {
	
	private Partita partita;
	private Comando saluta, interagisci;
	private AbstractPersonaggio strega;
	private Stanza atrio;
	
	@Before
	public void setUp() throws Exception {
		this.partita = new Partita();
		this.saluta = new ComandoSaluta();
		this.interagisci = new ComandoInteragisci();
		this.strega = new Strega ("Amelia","Vuoi una magia?");
		this.atrio = this.partita.getStanzaCorrente();
		this.partita.getStanzaCorrente().getStanzaAdiacente("sud").addAttrezzo(new Attrezzo("spada", 5));
	}

	@Test
	public void testEsegui_StregaNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(strega);
		assertEquals("Mascalzone, non mi hai salutato.\n"+
				"Adesso ti trasferisco nella stanza adiacente con meno attrezzi",this.interagisci.esegui(partita));
		assertSame(atrio,this.partita.getStanzaCorrente().getStanzaAdiacente("ovest"));
		this.partita.setStanzaCorrente(atrio);
		assertEquals("Ciao, io sono Amelia.\nVuoi una magia?",this.saluta.esegui(partita));
		assertEquals("Sei stato gentile nel salutarmi,\n"+
				"ti trasferisco nella stanza adiacente con più attrezzi",this.interagisci.esegui(partita));
		assertSame(atrio,this.partita.getStanzaCorrente().getStanzaAdiacente("est"));
	}

}
