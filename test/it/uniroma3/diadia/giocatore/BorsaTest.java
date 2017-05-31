package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;
import java.util.*;

public class BorsaTest {
	
	private static final String SPADA = "spada";
	private static final String LANCIA = "lancia";
	private Borsa vuota, pienaConSpada;
	private Attrezzo emptyString, spada, lancia, piombo5, ps2, piuma1, libro2;
	private List<Attrezzo> ordinata;
	private Set<Attrezzo> ordinato;
	private Map<Integer,Set<Attrezzo>> mappa;

	@Before
	public void setUp() throws Exception {
		
		this.emptyString = new Attrezzo ("",0);
		this.spada = new Attrezzo (SPADA,10);
		this.lancia = new Attrezzo (LANCIA,5);
		this.piombo5 = new Attrezzo("piombo",5);
		this.ps2 = new Attrezzo("ps",2);
		this.piuma1 = new Attrezzo("piuma",1);
		this.libro2 = new Attrezzo("libro",2);
		
		this.vuota = new Borsa();
		
		this.pienaConSpada = new Borsa();
		this.pienaConSpada.addAttrezzo(this.spada);
		
		this.ordinata = new ArrayList<Attrezzo>();
		
		this.ordinato = new TreeSet<Attrezzo>();
		
		this.mappa = new HashMap<Integer,Set<Attrezzo>>();
				
	}

	@Test
	public void testAddAttrezzo_NullIn_BorsaVuota() {
		assertFalse(this.vuota.addAttrezzo(null));
	}
	
	@Test
	public void testAddAttrezzo_EmptyStringIn_BorsaVuota() {
		assertTrue(this.vuota.addAttrezzo(this.emptyString));
	}
	
	@Test
	public void testAddAttrezzo_SpadaIn_BorsaVuota() {
		assertTrue(this.vuota.addAttrezzo(this.spada));
	}
	
	@Test
	public void testAddAttrezzo_LanciaIn_BorsaPienaConSpada() {
		assertFalse(this.pienaConSpada.addAttrezzo(this.lancia));
	}
	
	@Test
	public void testAddAttrezzo_LanciaInUna_BorsaSemiPiena() {
		Borsa semiPiena = new Borsa();
		assertTrue(semiPiena.addAttrezzo(new Attrezzo ("zappa",5)));
		assertTrue(semiPiena.addAttrezzo(this.lancia));
	}
	
	@Test
	public void testHasAttrezzo_NullIn_BorsaVuota() {
		assertFalse(this.vuota.hasAttrezzo(null));
	}
	
	@Test
	public void testHasAttrezzo_EmptyStringIn_BorsaVuota() {
		assertFalse(this.vuota.hasAttrezzo(""));
	}
	
	@Test
	public void testHasAttrezzo_SpadaIn_BorsaVuota() {
		assertFalse(this.vuota.hasAttrezzo(SPADA));
	}
	
	@Test
	public void testHasAttrezzo_SpadaIn_BorsaPienaConSpada() {
		assertTrue(this.pienaConSpada.hasAttrezzo(SPADA));
	}
	
	@Test
	public void testHasAttrezzo_LanciaIn_BorsaVuota_DopoAverloAggiunto() {
		assertTrue(this.vuota.addAttrezzo(this.lancia));
		assertTrue(this.vuota.hasAttrezzo(LANCIA));
	}
	
	@Test (expected = NullPointerException.class)
	public void testRemoveAttrezzo_NullIn_BorsaVuota() {
		this.vuota.removeAttrezzo(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testRemoveAttrezzo_NullIn_BorsaPienaConSpada() {
		this.pienaConSpada.removeAttrezzo(null);
	}
	
	@Test
	public void testRemoveAttrezzo_EmptyStringIn_BorsaVuota() {
		assertNull(this.vuota.removeAttrezzo(""));
	}
	
	@Test
	public void testRemoveAttrezzo_EmptyStringIn_BorsaPienaConSpada() {
		assertNull(this.pienaConSpada.removeAttrezzo(""));
	}
	
	@Test
	public void testRemoveAttrezzo_SpadaIn_BorsaVuota() {
		assertNull(this.vuota.removeAttrezzo(SPADA));
	}
	
	@Test
	public void testRemoveAttrezzo_SpadaIn_BorsaPienaConSpada() {
		assertTrue(this.pienaConSpada.hasAttrezzo(SPADA));
		assertEquals(this.spada, this.pienaConSpada.removeAttrezzo(SPADA));
		assertEquals(0,this.pienaConSpada.getNumeroAttrezzi());
		assertFalse(this.pienaConSpada.hasAttrezzo(SPADA));
	}
	
	@Test
	public void testRemoveAttrezzo_LanciaIn_BorsaPienaConSpada() {
		assertNull(this.pienaConSpada.removeAttrezzo(LANCIA));
	}
	
	@Test
	public void testRemoveAttrezzo_RimozioneInMezzo() {
		Borsa borsaTreAttrezzi = new Borsa();
		Attrezzo penna = new Attrezzo("penna",2);
		borsaTreAttrezzi.addAttrezzo(new Attrezzo("libro", 3));
		borsaTreAttrezzi.addAttrezzo(penna);
		borsaTreAttrezzi.addAttrezzo(new Attrezzo("matita", 1));
		assertEquals(penna, borsaTreAttrezzi.removeAttrezzo("penna"));
		assertEquals(2, borsaTreAttrezzi.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetAttrezzo_NullInVuota() {
		assertNull(this.vuota.getAttrezzo(null));
	}
	
	@Test
	public void testGetAttrezzo_SpadaInVuota() {
		assertNull(this.vuota.getAttrezzo(SPADA));
	}
	
	@Test
	public void testGetAttrezzo_SpadaInBorsaPienaConSpada() {
		assertEquals(this.spada,this.pienaConSpada.getAttrezzo(SPADA));
	}
	
	@Test
	public void testGetNumeroAttrezzi_Vuota() {
		assertEquals(0, this.vuota.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetNumeroAttrezzi_BorsaPienaConSpada() {
		assertEquals(1, this.pienaConSpada.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetNumeroAttrezzi_VuotaDiventaPienaEAggiungoSpada() {
		assertTrue(this.vuota.addAttrezzo(this.piuma1));
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.lancia));
		assertEquals(3,this.vuota.getNumeroAttrezzi());
		assertFalse(this.vuota.addAttrezzo(this.spada));
		assertEquals(3,this.vuota.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetPeso_Vuota() {
		assertEquals(0,this.vuota.getPeso());
	}
	
	@Test
	public void testGetPeso_PienaConSpada() {
		assertEquals(10,this.pienaConSpada.getPeso());
	}
	
	@Test
	public void testToString_Vuota() {
		assertEquals("Borsa vuota",this.vuota.toString());
	}
	
	@Test
	public void testToString_BorsaPienaConSpada() {
		assertEquals("Contenuto borsa (10kg/10kg): spada (10kg)",this.pienaConSpada.toString());
	}
	
	@Test
	public void testToString_VuotaConVariAttrezzi() {
		assertTrue(this.vuota.addAttrezzo(lancia));
		assertEquals("Contenuto borsa (5kg/10kg): lancia (5kg)",this.vuota.toString());
		assertTrue(this.vuota.addAttrezzo(new Attrezzo ("scudo",3)));
		assertEquals("Contenuto borsa (8kg/10kg): scudo (3kg) lancia (5kg)",this.vuota.toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaVuota() {
		this.ordinata = this.vuota.getContenutoOrdinatoPerPeso();
		assertSame(this.ordinata,this.vuota.getAttrezzi());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaPienaConSpada() {
		this.ordinata = this.pienaConSpada.getContenutoOrdinatoPerPeso();
		assertSame(this.ordinata,this.pienaConSpada.getAttrezzi());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_VuotaConDueAttrezziDiEgualPeso() {
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.ordinata = this.vuota.getContenutoOrdinatoPerPeso();
		assertEquals(this.libro2,this.ordinata.get(0));
		assertEquals(this.ps2,this.ordinata.get(1));
	}
		
	@Test
	public void testGetContenutoOrdinatoPerPeso_AttrezziConPesoUgualeENomiDiversi() {
		assertTrue(this.vuota.addAttrezzo(this.piombo5));
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.piuma1));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.ordinata = this.vuota.getContenutoOrdinatoPerPeso();
		assertEquals(this.piuma1,this.ordinata.get(0));
		assertEquals(this.libro2,this.ordinata.get(1));
		assertEquals(this.ps2,this.ordinata.get(2));
		assertEquals(this.piombo5,this.ordinata.get(3));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaPienaConSpada() {
		this.ordinato = this.pienaConSpada.getContenutoOrdinatoPerNome();
		assertEquals(this.spada,this.ordinato.iterator().next());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_VuotaConDueAttrezziIdentici() {
		assertTrue(this.vuota.addAttrezzo(lancia));
		assertTrue(this.vuota.addAttrezzo(lancia));
		assertEquals(2,this.vuota.getNumeroAttrezzi());
		this.ordinato = this.vuota.getContenutoOrdinatoPerNome();
		assertEquals(1,this.ordinato.size());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_AttrezziConNomiUgualiEPesiDiversi() {
		assertTrue(this.vuota.addAttrezzo(this.piombo5));
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.piuma1));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.ordinato = this.vuota.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = this.ordinato.iterator();
		assertEquals(this.libro2,i.next());
		assertEquals(this.piombo5,i.next());
		assertEquals(this.piuma1,i.next());
		assertEquals(this.ps2,i.next());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_BorsaPienaConSpada() {
		this.mappa = this.pienaConSpada.getContenutoRaggruppatoPerPeso();
		assertEquals(this.spada,this.mappa.get(10).iterator().next());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_VuotaConDueAttrezziDiPariPeso() {
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.mappa = this.vuota.getContenutoRaggruppatoPerPeso();
		Iterator<Attrezzo> i = this.mappa.get(2).iterator();
		assertEquals(this.libro2,i.next());
		assertEquals(this.ps2,i.next());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_VuotaConVariAttrezzi() {
		assertTrue(this.vuota.addAttrezzo(this.piombo5));
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.piuma1));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.mappa = this.vuota.getContenutoRaggruppatoPerPeso();
		assertEquals(this.piuma1,this.mappa.get(1).iterator().next());
		Iterator<Attrezzo> i = this.mappa.get(2).iterator();
		assertEquals(this.libro2,i.next());
		assertEquals(this.ps2,i.next());
		assertEquals(this.piombo5,this.mappa.get(5).iterator().next());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaPienaConSpada() {
		this.ordinato = this.pienaConSpada.getSortedSetOrdinatoPerPeso();
		assertEquals(this.spada,this.ordinato.iterator().next());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_VuotaConDueAttrezziStessoPesoNomeDiverso() {
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.ordinato = this.vuota.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = this.ordinato.iterator();
		assertEquals(this.libro2,i.next());
		assertEquals(this.ps2,i.next());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_VuotaConVariAttrezzi() {
		assertTrue(this.vuota.addAttrezzo(this.piombo5));
		assertTrue(this.vuota.addAttrezzo(this.ps2));
		assertTrue(this.vuota.addAttrezzo(this.piuma1));
		assertTrue(this.vuota.addAttrezzo(this.libro2));
		this.ordinato = this.vuota.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = this.ordinato.iterator();
		assertEquals(this.piuma1,i.next());
		assertEquals(this.libro2,i.next());
		assertEquals(this.ps2,i.next());
		assertEquals(this.piombo5,i.next());
	}

}