package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	
	private StanzaMagica magica;
	private Attrezzo attrezzo;

	@Before
	public void setUp() throws Exception {
		this.magica = new StanzaMagica("magica",1);
		this.attrezzo = new Attrezzo("attrezzo",1);
	}

	@Test
	public void testModificaAttrezzo_Attrezzo() {
		Attrezzo daTrattare = new Attrezzo("abc",1);
		this.magica.addAttrezzo(this.attrezzo);
		this.magica.addAttrezzo(daTrattare);
		assertEquals(new Attrezzo("attrezzo",1), this.magica.getAttrezzo("attrezzo"));
		assertEquals(new Attrezzo("cba",2), this.magica.getAttrezzo("cba"));
		assertFalse(this.magica.hasAttrezzo("abc"));
	}
	
	@Test
	public void testAddAttrezzo_Attrezzo() {
		this.magica.addAttrezzo(this.attrezzo);
		assertEquals(new Attrezzo("attrezzo",1), this.magica.getAttrezzo("attrezzo"));
	}

}
