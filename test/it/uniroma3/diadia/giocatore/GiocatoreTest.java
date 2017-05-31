package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

public class GiocatoreTest {

	private static final String SPADA = "spada";
	private static final String LANCIA = "lancia";
	private static final String PISTOLA = "pistola";
	private Stanza vuota, stanzaDieciAttrezzi, stanzaConLancia;
	private Attrezzo spada, lancia, scudo, pugnale, elmo, osso, lanterna, zappa, forcone, carro, pistola, emptyString;
	private Giocatore giocatoreBorsaVuota, giocatoreConPistola;

	@Before
	public void setUp() throws Exception {

		this.spada = new Attrezzo (SPADA,10);
		this.lancia = new Attrezzo (LANCIA,5);
		this.scudo = new Attrezzo ("scudo",4);
		this.pugnale = new Attrezzo ("pugnale",3);
		this.elmo = new Attrezzo ("elmo",4);
		this.osso = new Attrezzo ("osso",3);
		this.lanterna = new Attrezzo ("lanterna",10);
		this.zappa = new Attrezzo ("zappa",6);
		this.forcone = new Attrezzo ("forcone",6);
		this.carro = new Attrezzo ("carro",40);
		
		this.pistola = new Attrezzo (PISTOLA, 1);
		
		this.emptyString = new Attrezzo("",1);

		
		this.vuota = new Stanza ("vuota");

		this.stanzaConLancia = new Stanza("stanzaConLancia");
		this.stanzaConLancia.addAttrezzo(this.lancia);

		this.stanzaDieciAttrezzi = new Stanza("stanzaDieciAttrezzi");
		this.stanzaDieciAttrezzi.addAttrezzo(this.spada);
		this.stanzaDieciAttrezzi.addAttrezzo(this.lancia);
		this.stanzaDieciAttrezzi.addAttrezzo(this.scudo);
		this.stanzaDieciAttrezzi.addAttrezzo(this.pugnale);
		this.stanzaDieciAttrezzi.addAttrezzo(this.elmo);
		this.stanzaDieciAttrezzi.addAttrezzo(this.osso);
		this.stanzaDieciAttrezzi.addAttrezzo(this.lanterna);
		this.stanzaDieciAttrezzi.addAttrezzo(this.zappa);
		this.stanzaDieciAttrezzi.addAttrezzo(this.forcone);
		this.stanzaDieciAttrezzi.addAttrezzo(this.carro);
		
		this.giocatoreBorsaVuota = new Giocatore();
		
		this.giocatoreConPistola = new Giocatore();
		this.giocatoreConPistola.getBorsa().addAttrezzo(this.pistola);

	}
	
	@Test
	public void testPrendi_NullDa_StanzaDieciAttrezziPer_GiocatoreBorsaVuota() {
		assertFalse(this.giocatoreBorsaVuota.prendi(null, this.stanzaDieciAttrezzi));
	}
	
	@Test (expected = NullPointerException.class)
	public void testPrendi_SpadaDa_StanzaNullPer_GiocatoreBorsaVuota() {
		this.giocatoreBorsaVuota.prendi(this.spada, null);
	}

	@Test
	public void testPrendi_AttrezzoEmptyStringDa_StanzaDieciAttrezziPer_GiocatoreBorsaVuota() {
		assertFalse(this.stanzaDieciAttrezzi.hasAttrezzo(""));
		assertFalse(this.giocatoreBorsaVuota.prendi(this.emptyString, this.stanzaDieciAttrezzi));
		assertFalse(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo(""));
	}
	
	@Test
	public void testPrendi_SpadaDa_StanzaVuotaPer_GiocatoreBorsaVuota() {
		assertFalse(this.vuota.hasAttrezzo(SPADA));
		assertFalse(this.giocatoreBorsaVuota.prendi(this.spada, this.vuota));
		assertFalse(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testPrendi_SpadaDa_StanzaDieciAttrezziPer_GiocatoreBorsaVuota() {
		assertTrue(this.giocatoreBorsaVuota.prendi(this.spada, this.stanzaDieciAttrezzi));
		assertFalse(this.stanzaDieciAttrezzi.hasAttrezzo(SPADA));
		assertTrue(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo(SPADA));
	}
	
	@Test
	public void testPrendi_CarroDa_StanzaDieciAttrezziPer_GiocatoreBorsaVuota() {
		assertFalse(this.giocatoreBorsaVuota.prendi(this.carro, this.stanzaDieciAttrezzi));
		assertTrue(this.stanzaDieciAttrezzi.hasAttrezzo("carro"));
		assertFalse(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo("carro"));
	}
	
	@Test
	public void testPrendi_ElmoDa_StanzaConLanciaPer_GiocatoreBorsaVuota() {
		assertFalse(this.stanzaConLancia.hasAttrezzo(this.elmo.getNome()));
		assertFalse(this.giocatoreBorsaVuota.prendi(this.elmo, this.stanzaConLancia));
		assertFalse(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo("elmo"));
	}
	
	@Test
	public void testPrendi_Lancia_MaPrimaAvevoGiaPreso_SpadaDa_StanzaDieciAttrezziPer_GiocatoreBorsaVuota() {
		assertTrue(this.giocatoreBorsaVuota.prendi(this.spada, this.stanzaDieciAttrezzi));
		assertFalse(this.stanzaDieciAttrezzi.hasAttrezzo(SPADA));
		assertTrue(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo(SPADA));
		assertFalse(this.giocatoreBorsaVuota.prendi(this.lancia, this.stanzaDieciAttrezzi));
		assertTrue(this.stanzaDieciAttrezzi.hasAttrezzo(LANCIA));
		assertFalse(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo(LANCIA));
	}
	
	@Test
	public void testPosa_StringaNullIn_StanzaConLanciaDa_GiocatoreConPistola() {
		assertFalse(this.giocatoreConPistola.posa(null, stanzaConLancia));
		assertFalse(this.stanzaConLancia.hasAttrezzo(null));
	}
	
	@Test (expected = NullPointerException.class)
	public void testPosa_PistolaIn_StanzaNullDa_GiocatoreConPistola() {
		this.giocatoreConPistola.posa(PISTOLA, null);
	}
	
	@Test
	public void testPosa_AttrezzoEmptyStringIn_StanzaVuotaDa_GiocatoreConPistola() {
		assertFalse(this.giocatoreConPistola.getBorsa().hasAttrezzo(""));
		assertFalse(this.giocatoreConPistola.posa("", this.vuota));
		assertFalse(this.vuota.hasAttrezzo(""));
	}
	
	@Test
	public void testPosa_PistolaIn_StanzaVuota_GiocatoreConPistola() {
		assertTrue(this.giocatoreConPistola.posa(PISTOLA, this.vuota));
		assertTrue(this.vuota.hasAttrezzo(PISTOLA));
		assertFalse(this.giocatoreConPistola.getBorsa().hasAttrezzo(PISTOLA));
	}
	
	@Test
	public void testPosa_PistolaIn_StanzaConLancia_GiocatoreConPistola() {
		assertTrue(this.giocatoreConPistola.posa(PISTOLA, this.stanzaConLancia));
		assertTrue(this.stanzaConLancia.hasAttrezzo(PISTOLA));
		assertFalse(this.giocatoreConPistola.getBorsa().hasAttrezzo(PISTOLA));
	}
	
	@Test
	public void testPosa_PistolaIn_StanzaDieciAttrezziDa_GiocatoreConPistola() {
		assertFalse(this.giocatoreConPistola.posa(PISTOLA, this.stanzaDieciAttrezzi));
		assertFalse(this.stanzaDieciAttrezzi.hasAttrezzo(PISTOLA));
		assertTrue(this.giocatoreConPistola.getBorsa().hasAttrezzo(PISTOLA));
	}
	
	@Test
	public void testPosa_SpadaIn_StanzaConLanciaDa_GiocatoreBorsaVuota() {
		assertFalse(this.giocatoreBorsaVuota.getBorsa().hasAttrezzo(SPADA));
		assertFalse(this.giocatoreBorsaVuota.posa(SPADA, this.stanzaConLancia));
		assertFalse(this.stanzaConLancia.hasAttrezzo(SPADA));
	}

}
