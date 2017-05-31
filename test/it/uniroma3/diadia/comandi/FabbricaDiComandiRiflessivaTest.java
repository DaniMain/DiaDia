package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiRiflessivaTest {
	
	private static final String REGALA = "regala";
	private static final String POSA = "posa";
	private static final String OSSO = "osso";
	private static final String PRENDI = "prendi";
	private static final String VAI = "vai";
	private FabbricaDiComandiRiflessiva factory;

	@Before
	public void setUp() throws Exception {
		this.factory = new FabbricaDiComandiRiflessiva();
	}
	
	@Test (expected = NullPointerException.class)
	public void testCostruisciComando_Null() {
		this.factory.costruisciComando(null);
	}
	
	@Test
	public void testCostruisciComando_Vuoto() {
		Comando vuoto =this.factory.costruisciComando("");
		assertEquals("non valido",vuoto.getNome());
		assertEquals("Comando inesistente",vuoto.getParametro());
	}

	@Test
	public void testCostriusciComando_Aiuto() {
		Comando aiuto = this.factory.costruisciComando("aiuto");
		assertEquals("aiuto",aiuto.getNome());
		assertNull(aiuto.getParametro());
	}
	
	@Test
	public void testCostriusciComando_Fine() {
		Comando fine = this.factory.costruisciComando("fine");
		assertEquals("fine",fine.getNome());
		assertNull(fine.getParametro());
	}
	
	@Test
	public void testCostriusciComando_Guarda() {
		Comando guarda = this.factory.costruisciComando("guarda");
		assertEquals("guarda",guarda.getNome());
		assertNull(guarda.getParametro());
	}
	
	@Test
	public void testCostriusciComando_Vai() {
		Comando vai = this.factory.costruisciComando(VAI);
		assertEquals(VAI, vai.getNome());
		assertNull(vai.getParametro());
	}
		
	@Test
	public void testCostruisciComando_VaiNord() {
		Comando vaiNord = this.factory.costruisciComando("vai nord");
		assertEquals(VAI, vaiNord.getNome());
		assertEquals("nord", vaiNord.getParametro());
	}
	
	@Test
	public void testCostruisciComando_VaiSud() {
		Comando vaiSud = this.factory.costruisciComando("vai sud");
		assertEquals(VAI, vaiSud.getNome());
		assertEquals("sud", vaiSud.getParametro());
	}
	
	@Test
	public void testCostruisciComando_VaiEst() {
		Comando vaiEst = this.factory.costruisciComando("vai est");
		assertEquals(VAI, vaiEst.getNome());
		assertEquals("est", vaiEst.getParametro());
	}
	
	@Test
	public void testCostruisciComando_VaiOvest() {
		Comando vaiOvest = this.factory.costruisciComando("vai ovest");
		assertEquals(VAI, vaiOvest.getNome());
		assertEquals("ovest", vaiOvest.getParametro());
	}
	
	@Test
	public void testCostriusciComando_Prendi() {
		Comando prendi = this.factory.costruisciComando(PRENDI);
		assertEquals(PRENDI,prendi.getNome());
		assertNull(prendi.getParametro());
	}
	
	@Test
	public void testCostriusciComando_PrendiOsso() {
		Comando prendiOsso = this.factory.costruisciComando("prendi osso");
		assertEquals(PRENDI,prendiOsso.getNome());
		assertEquals(OSSO,prendiOsso.getParametro());
	}
	
	@Test
	public void testCostriusciComando_Posa() {
		Comando posa = this.factory.costruisciComando(POSA);
		assertEquals(POSA,posa.getNome());
		assertNull(posa.getParametro());
	}
	
	@Test
	public void testCostruisciComando_PosaOsso() {
		Comando posaOsso = this.factory.costruisciComando("posa osso");
		assertEquals(POSA,posaOsso.getNome());
		assertEquals(OSSO,posaOsso.getParametro());
	}
	
	@Test
	public void testCostriusciComando_NonValido() {
		Comando invalido = this.factory.costruisciComando("invalido");
		assertEquals("non valido",invalido.getNome());
		assertEquals("Comando inesistente",invalido.getParametro());
	}
	
	@Test
	public void testCostruisciComando_Interagisci() {
		Comando interagisci = this.factory.costruisciComando("interagisci");
		assertEquals("interagisci",interagisci.getNome());
		assertNull(interagisci.getParametro());
	}
	
	@Test
	public void testCostruisciComando_Saluta() {
		Comando saluta = this.factory.costruisciComando("saluta");
		assertEquals("saluta",saluta.getNome());
		assertNull(saluta.getParametro());
	}
	
	@Test
	public void testCostruisciComando_Regala() {
		Comando regala = this.factory.costruisciComando(REGALA);
		assertEquals(REGALA,regala.getNome());
		assertNull(regala.getParametro());
	}
	
	@Test
	public void testCostruisciComando_RegalaOsso() {
		Comando regalaOsso = this.factory.costruisciComando("regala osso");
		assertEquals(REGALA,regalaOsso.getNome());
		assertEquals(OSSO,regalaOsso.getParametro());
	}
	
}
