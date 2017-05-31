package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Fisarmonica di tutti i comandi presenti nel gioco
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

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
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (Comando)Class.forName(nomeClasse).newInstance();
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
			comando.setParametro("Comando inesistente");
		}
		return comando;

	}

}
