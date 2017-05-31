package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.*;

public class ComandoRegalaTest {
	
	private static final String OSSO = "osso";
	private static final String SPADA = "spada";
	private Comando regala;
	private Cane cane;
	private AbstractPersonaggio mago;
	private Attrezzo spada, osso;
	private Partita partita;
	private Giocatore spadaAndOsso;
	
	@Before
	public void setUp() throws Exception {
		this.spada = new Attrezzo(SPADA, 4);
		this.osso = new Attrezzo(OSSO, 1);
		this.regala = new ComandoRegala();
		this.cane = new Cane("fido", "BAU BAU", OSSO, null);
		this.mago = new Mago("merlino", "Ciao", null);
		this.partita = new Partita();
		this.partita.getStanzaCorrente().removeAttrezzo(osso); // nell'atrio è già presente l'attrezzo osso, lo leviamo per non creare confusione
		this.spadaAndOsso = this.partita.getGiocatore();
		this.spadaAndOsso.getBorsa().addAttrezzo(spada);
		this.spadaAndOsso.getBorsa().addAttrezzo(osso);
	}

	@Test
	public void testEsegui_NullANessunoNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(null);
		assertEquals("Cosa vuoi regalare? Devi specificare un attrezzo",this.regala.esegui(partita));
	}
	
	@Test
	public void testEsegui_NullACaneNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		assertEquals("Cosa vuoi regalare? Devi specificare un attrezzo",this.regala.esegui(partita));
	}
	
	@Test
	public void testEsegui_NullAMagoNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(this.mago);
		assertEquals("Cosa vuoi regalare? Devi specificare un attrezzo",this.regala.esegui(partita));
	}
	
	@Test
	public void testEsegui_NullAStregaNellAtrio() {
		assertEquals("Cosa vuoi regalare? Devi specificare un attrezzo",this.regala.esegui(partita));
	}
	
	@Test
	public void testEsegui_SpadaACaneNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		this.regala.setParametro(SPADA);
		assertEquals("GRR! Questo non è il mio cibo preferito!",this.regala.esegui(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertFalse(this.spadaAndOsso.getBorsa().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testEsegui_OssoACaneNellAtrio_CaneSenzaRegalo() {
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		this.regala.setParametro(OSSO);
		assertEquals("GNAM GNAM! Grazie del regalo, "+
				"ma non ho nulla da darti...",this.regala.esegui(partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("lancia"));
		assertFalse(this.spadaAndOsso.getBorsa().hasAttrezzo(OSSO));
	}
	
	@Test
	public void testEsegui_OssoACaneNellAtrio_CaneConRegalo() {
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		this.cane.setRegalo(new Attrezzo("lancia", 4));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("lancia"));
		this.regala.setParametro(OSSO);
		assertEquals("GNAM GNAM! Ecco per te un bel regalo! "+ 
				"Grazie! CIOMP CIOMP!",this.regala.esegui(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("lancia"));
		assertFalse(this.spadaAndOsso.getBorsa().hasAttrezzo(OSSO));
	}
	
	@Test
	public void testEsegui_SpadaAMagoNellAtrio() {
		this.partita.getStanzaCorrente().setPersonaggio(this.mago);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		this.regala.setParametro(SPADA);
		assertEquals("Ecco fatto, il peso è stato dimezzato!",this.regala.esegui(partita));
		assertEquals(2,this.spada.getPeso());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertFalse(this.spadaAndOsso.getBorsa().hasAttrezzo(SPADA));
		this.spadaAndOsso.getBorsa().addAttrezzo(spada);
		assertEquals("Mi spiace, ma non posso più dimezzare nulla...",this.regala.esegui(partita));
		assertEquals(2,this.spada.getPeso());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertTrue(this.spadaAndOsso.getBorsa().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testEsegui_OssoESpadaAStregaNellAtrio() {
		/* nell'atrio è gia presente la strega */
		this.regala.setParametro(OSSO);
		assertEquals("AH AH AH AH AH! Sciocco, "+
				"l'attrezzo è mio e non te lo darò mai più!",this.regala.esegui(partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(OSSO));
		assertFalse(this.spadaAndOsso.getBorsa().hasAttrezzo(OSSO));
		assertEquals(1,this.spadaAndOsso.getBorsa().getNumeroAttrezzi());
		this.regala.setParametro(SPADA);
		assertEquals("AH AH AH AH AH! Sciocco, "+
				"l'attrezzo è mio e non te lo darò mai più!",this.regala.esegui(partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(SPADA));
		assertFalse(this.spadaAndOsso.getBorsa().hasAttrezzo(SPADA));
		assertEquals(0,this.spadaAndOsso.getBorsa().getNumeroAttrezzi());
	}

}
