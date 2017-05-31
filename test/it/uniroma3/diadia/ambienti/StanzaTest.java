package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	
	private static final String NORD = "nord";

	private static final String LIBRO = "libro";
	
	private Stanza vuota, stanzaConLibro;
	private Attrezzo libro, ps;

	@Before
	public void setUp() throws Exception {
		this.vuota = new Stanza("vuota");
		
		this.libro = new Attrezzo(LIBRO, 1);
		this.stanzaConLibro = new Stanza("piena");
		this.stanzaConLibro.addAttrezzo(this.libro);
		
		this.ps = new Attrezzo("PlayStation", 3);
	}

	@Test
	public void testRemoveAttrezzo_LibroDa_StanzaVuota() {
		assertFalse(this.vuota.removeAttrezzo(this.libro));
	}
	
	@Test(expected = NullPointerException.class)
	public void testRemoveAttrezzo_NullDa_StanzaVuota() {
		this.vuota.removeAttrezzo(null);
	}
	
	@Test
	public void testRemoveAttrezzo_EmptyStringDa_StanzaVuota() {
		assertFalse(this.vuota.removeAttrezzo(new Attrezzo("",1)));
	}
	
	@Test
	public void testRemoveAttrezzo_LibroDa_StanzaConLibro() {
		assertTrue(this.stanzaConLibro.removeAttrezzo(this.libro));
	}
	
	@Test
	public void testRemoveAttrezzo_InesistenteDa_StanzaConLibro() {
		assertFalse(this.stanzaConLibro.removeAttrezzo(new Attrezzo("inesistente",0)));
	}
	
	@Test
	public void testRemoveAttrezzo_AttrezzoEsistente_DuplicatoDa_StanzaConLibro() {
		Attrezzo copiaDiLibro = new Attrezzo(new String(LIBRO),1);
		assertFalse(copiaDiLibro == this.libro);
		assertEquals(copiaDiLibro.getNome(), this.libro.getNome());
		assertTrue(this.stanzaConLibro.removeAttrezzo(copiaDiLibro));
	}
	
	@Test
	public void testRemoveAttrezzo_RimozioneInMezzo() {
		Stanza stanzaDueAttrezzi = new Stanza("stanzaDueAttrezzi");
		stanzaDueAttrezzi.addAttrezzo(this.ps);
		stanzaDueAttrezzi.addAttrezzo(this.libro);
		assertTrue(stanzaDueAttrezzi.removeAttrezzo(this.ps));
		assertTrue(stanzaDueAttrezzi.removeAttrezzo(this.libro));
	}
	
	@Test
	public void testHasAttrezzo_UltimoRimosso() {
		Stanza stanza = vuota;
		assertTrue(stanza.addAttrezzo(this.libro));
		assertTrue(stanza.removeAttrezzo(this.libro));
		assertFalse(stanza.hasAttrezzo(LIBRO));
	}
	
	@Test
	public void testGetNumeroAttrezzi_StanzaVuota() {
		assertEquals(0, this.vuota.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetNumeroAttrezzi_StanzaConLibro() {
		assertEquals(1, this.stanzaConLibro.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetNumeroAttrezzi_StanzaConTreAttrezzi() {
		Stanza stanzaTreAttrezzi = new Stanza("stanzaTreAttrezi");
		assertTrue(stanzaTreAttrezzi.addAttrezzo(this.libro));
		assertTrue(stanzaTreAttrezzi.addAttrezzo(this.ps));
		assertTrue(stanzaTreAttrezzi.addAttrezzo(new Attrezzo("xbox",3)));
		assertEquals(3, stanzaTreAttrezzi.getNumeroAttrezzi());
	}
	
	@Test
	public void testHasAttrezzo_Null() {
		assertFalse(this.stanzaConLibro.hasAttrezzo(null));
	}
	
	@Test
	public void testHasAttrezzo_LibroInStanzaConLibro() {
		assertTrue(this.stanzaConLibro.hasAttrezzo(LIBRO));
	}
	
	@Test
	public void testHasAttrezzo_LibroInStanzaVuota() {
		assertFalse(this.vuota.hasAttrezzo(LIBRO));
	}
	
	@Test
	public void testHasAttrezzo_PlayStationInStanzaConLibro() {
		assertFalse(this.stanzaConLibro.hasAttrezzo(this.ps.getNome()));
	}
	
	@Test
	public void testAddAttrezzo_Null() {
		Stanza stanza = new Stanza("stanza");
		assertTrue(stanza.addAttrezzo(libro));
		assertFalse(stanza.addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzo_LibroInStanzaVuota() {
		assertTrue(this.vuota.addAttrezzo(this.libro));
	}
	
	@Test
	public void testAddAttrezzo_EmptyString() {
		assertTrue(this.vuota.addAttrezzo(new Attrezzo("",1)));
	}
	
	@Test
	public void testAddAttrezzo_InStanzaConDieciAttrezzi() {
		Stanza stanzaConDieciAttrezzi = new Stanza("DieciAttrezzi");
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("spada",1)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("lancia",2)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("elmo",3)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("scudo",4)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("mitra",5)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("pistola",6)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("ak47",7)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("lanciafiamme",8)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("granata",9)));
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(new Attrezzo("pugnale",10)));
		
		assertTrue(stanzaConDieciAttrezzi.addAttrezzo(this.libro));
	}
	
	@Test
	public void testAddAttrezzo_LibroInStanzaConLibro() {
		assertTrue(this.stanzaConLibro.addAttrezzo(this.libro));
		assertEquals(1, this.stanzaConLibro.getNumeroAttrezzi());
	}
	
	@Test
	public void testImpostaStanzaAdiacente_nullNordVuota() {
		this.vuota.impostaStanzaAdiacente(NORD, null);
		assertNull(this.vuota.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testImpostaStanzaAdiacente_StanzaConLibroNordVuota() {
		this.vuota.impostaStanzaAdiacente(NORD, this.stanzaConLibro);
		assertNotNull(this.vuota.getStanzaAdiacente(NORD));
		assertEquals("piena",this.vuota.getStanzaAdiacente(NORD).getNome());
	}

	@Test
	public void testImpostaStanzaAdiacente_AEstDiStanzaOvestCEStanzaEstEViceversa() {
		Stanza stanzaEst = new Stanza("stanzaEst"), stanzaOvest = new Stanza("stanzaOvest");
		stanzaEst.impostaStanzaAdiacente("ovest", stanzaOvest);
		stanzaOvest.impostaStanzaAdiacente("est", stanzaEst);
		assertEquals("stanzaOvest", stanzaEst.getStanzaAdiacente("ovest").getNome());
		assertEquals("stanzaEst", stanzaOvest.getStanzaAdiacente("est").getNome());
	}
	
	@Test 
	public void testGetAttrezzo_NullInVuota() {
		assertNull(this.vuota.getAttrezzo(null));
	}
	
	@Test 
	public void testGetAttrezzo_LibroInVuota() {
		assertNull(this.vuota.getAttrezzo(LIBRO));
	}
	
	@Test 
	public void testGetAttrezzo_LibroInStanzaConLibro() {
		assertEquals(this.libro,this.stanzaConLibro.getAttrezzo(LIBRO));
	}
	
	@Test
	public void testGetAttrezzo_StanzaConTantiAttrezzi() {
		Stanza stanza = new Stanza("stanza");
		Attrezzo spada = new Attrezzo("spada",10);
		assertNull(stanza.getAttrezzo(LIBRO));
		assertTrue(stanza.addAttrezzo(this.libro));
		assertTrue(stanza.addAttrezzo(this.ps));
		assertEquals(this.libro, stanza.getAttrezzo(LIBRO));
		assertEquals(this.ps, stanza.getAttrezzo(this.ps.getNome()));
		assertNull(stanza.getAttrezzo("spada"));
		assertTrue(stanza.addAttrezzo(spada));
		assertEquals(spada, stanza.getAttrezzo("spada"));
	}
	
	@Test
	public void testDirezioniToString_vuota() {
		assertEquals("Uscite:",this.vuota.direzioniToString());
	}
	
	@Test
	public void testDirezioniToString_vuotaNordStanzaConLibro() {
		this.vuota.impostaStanzaAdiacente(NORD, this.stanzaConLibro);
		assertEquals("Uscite: nord", this.vuota.direzioniToString());
	}
	
	@Test
	public void testDirezioniToString_conStanzeAdiacentiFraLoro() {
		Stanza centro = new Stanza("centro");
		Stanza nord = new Stanza(NORD);
		Stanza sud = new Stanza("sud");
		centro.impostaStanzaAdiacente(NORD, nord);
		centro.impostaStanzaAdiacente("sud", sud);
		assertEquals("Uscite: nord sud", centro.direzioniToString());
	}
	
	@Test
	public void testToString() {
		Stanza s = new Stanza("stanza");
		assertTrue(s.addAttrezzo(this.libro));
		assertTrue(s.addAttrezzo(this.ps));
		s.impostaStanzaAdiacente(NORD, this.vuota);
		assertEquals("stanza\nUscite: nord\nAttrezzi nella stanza: libro (1kg) PlayStation (3kg)\n\nQui non c'è nessun personaggio\n",s.toString());
	}
	
	@Test
	public void tesGetStanzaAdiacenteMenoAttrezzi_GetStanzaAdiacentePiùAttrezzi() {
		Stanza nord = new Stanza(NORD);
		assertTrue(nord.addAttrezzo(this.libro));
		assertTrue(nord.addAttrezzo(this.ps));
		this.vuota.impostaStanzaAdiacente(NORD, nord);
		this.vuota.impostaStanzaAdiacente("sud", this.stanzaConLibro);
		assertSame(this.stanzaConLibro,this.vuota.getStanzaAdiacenteMenoAttrezzi()); // stanza con libro ha meno attrezzi
		assertNotSame(this.stanzaConLibro,this.vuota.getStanzaAdiacentePiùAttrezzi());
		assertNotSame(nord,this.vuota.getStanzaAdiacenteMenoAttrezzi()); 
		assertSame(nord,this.vuota.getStanzaAdiacentePiùAttrezzi()); // nord ha più attrezzi
		/* aggiungo due attrezzi a stanzaConLibro */
		assertTrue(this.stanzaConLibro.addAttrezzo(this.ps));
		assertTrue(this.stanzaConLibro.addAttrezzo(new Attrezzo("spada",5)));
		assertSame(this.stanzaConLibro,this.vuota.getStanzaAdiacentePiùAttrezzi());
		assertSame(nord,this.vuota.getStanzaAdiacenteMenoAttrezzi());
	}

}
