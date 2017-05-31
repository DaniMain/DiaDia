package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.attrezzi.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest extends Labirinto {
	
	private static final String ATRIO = "Atrio";
	private static final String BIBLIOTECA = "Biblioteca";
	private Stanza atrio, biblioteca;
	private Labirinto labirinto;

	@Before
	public void setUp() throws Exception {

		this.atrio = new Stanza(ATRIO);
		this.atrio.impostaStanzaAdiacente("nord", this.biblioteca);
		this.atrio.impostaStanzaAdiacente("sud", new Stanza("Aula N10"));
		this.atrio.impostaStanzaAdiacente("est", new Stanza("Aula N11"));
		this.atrio.impostaStanzaAdiacente("ovest", new Stanza("Laboratorio Campus"));
		this.atrio.addAttrezzo(new Attrezzo("osso",1));
		
		this.biblioteca = new Stanza(BIBLIOTECA);
		this.biblioteca.impostaStanzaAdiacente("sud", this.atrio);
		
		this.labirinto = new Labirinto();
		
	}
	
	@Test
	public void testGetStanzaVincente_NotNull() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}

	@Test
	public void testGetStanzaVincente_StoInBiblioteca_DunqueLeStanzaAdiacentiSonoSettateBene() {
		assertEquals(BIBLIOTECA, this.labirinto.getStanzaVincente().getNome());
		assertEquals(ATRIO, this.labirinto.getStanzaVincente().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testGetStanzaVincente_StoNellAtrio() {
		assertNotEquals(ATRIO, this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzaVincente_StoInUnaStanzaGenerica() {
		Stanza generica = new Stanza("Generica");
		assertNotEquals(generica.getNome(), this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzaIniziale_NotNull() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testGetStanzaIniziale_StoNellAtrio_DunqueLeStanzeAdiacentiSonoStettateBene() {
		assertEquals(ATRIO, this.labirinto.getStanzaIniziale().getNome());
		assertEquals("Aula N11", this.labirinto.getStanzaIniziale().getStanzaAdiacente("est").getNome());
		assertEquals("Aula N10", this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
		assertEquals("Segreteria", this.labirinto.getStanzaIniziale().getStanzaAdiacente("ovest").getNome());
	}
	
	@Test
	public void testGetStanzaIniziale_StoInBiblioteca() {
		assertNotEquals(BIBLIOTECA, this.labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testGetStanzaIniziale_StoInUnaStanzaGenerica() {
		Stanza generica = new Stanza ("Generica");
		assertNotEquals(generica.getNome(), this.labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testSetStanzaVincente_Null() {
		this.labirinto.setStanzaVincente(null);
		assertNull(this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testSetStanzaVincente_Mensa() {
		this.labirinto.setStanzaVincente(new Stanza("Mensa"));
		assertNotEquals(BIBLIOTECA, this.labirinto.getStanzaVincente().getNome());
		assertEquals("Mensa", this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testSetStanzaVincente_Atrio_DunqueControlloISettaggiDelleStanzeAdiacenti() {
		this.labirinto.setStanzaVincente(this.labirinto.getStanzaIniziale());
		assertNotEquals(BIBLIOTECA, this.labirinto.getStanzaVincente().getNome());
		assertEquals(ATRIO, this.labirinto.getStanzaVincente().getNome());
		assertEquals("Aula N11", this.labirinto.getStanzaVincente().getStanzaAdiacente("est").getNome());
		assertEquals("Aula N10", this.labirinto.getStanzaVincente().getStanzaAdiacente("sud").getNome());
		assertEquals("Segreteria", this.labirinto.getStanzaVincente().getStanzaAdiacente("ovest").getNome());
	}
	
	@Test
	public void testSetStanzaIniziale_Null() {
		this.labirinto.setStanzaIniziale(null);
		assertNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testSetStanzaIniziale_Mensa() {
		this.labirinto.setStanzaIniziale(new Stanza("Mensa"));
		assertNotEquals(ATRIO, this.labirinto.getStanzaIniziale().getNome());
		assertEquals("Mensa", this.labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testSetStanzaIniziale_Biblioteca_DunqueControlloISettaggiDelleStanzeAdiacenti() {
		this.labirinto.setStanzaIniziale(this.labirinto.getStanzaVincente());
		assertNotEquals(ATRIO, this.labirinto.getStanzaIniziale().getNome());
		assertEquals(BIBLIOTECA, this.labirinto.getStanzaIniziale().getNome());
		assertEquals(ATRIO, this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}

}
