package it.uniroma3.it.diadia.comandi;

import java.util.Scanner;

/**
 * Fisarmonica di tutti i comandi presenti nel gioco
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	private Scanner scannerDiParole;

	public Comando costruisciComando(String istruzione) {
		
		this.scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (this.scannerDiParole.hasNext())
			nomeComando = this.scannerDiParole.next(); // prima parola: nome del comando
		if (this.scannerDiParole.hasNext())
			parametro = this.scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null)
			comando = new ComandoNonValido(parametro);
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(parametro);
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine(parametro);
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda(parametro);
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(parametro);
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(parametro);
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(parametro);
		else
			comando = new ComandoNonValido(parametro);
		return comando;
		
	}

}
