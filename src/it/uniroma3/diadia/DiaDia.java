package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.*;

/**
 *  Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 *  Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 *  Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  Paolo Merialdo (da un'idea di Michael Kolling and David J. Barnes) modified by Daniele Mainella
 * @version 0.4
 */

public class DiaDia {
	private Partita partita;
	private static final String MESSAGGIO_BENVENUTO = 
					"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.";
	private static String[] elencoComandi = {"aiuto","fine","guarda","interagisci","posa","prendi","regala","saluta","vai"};
	private InterfacciaUtente interfaccia;

	public DiaDia() {
		this.partita = new Partita();
		this.interfaccia = new InterfacciaUtenteConsole();
	}
	
	public static String[] getElencoComandi(){
		return elencoComandi;
	}

	public void gioca() {
		String istruzione; 
		this.interfaccia.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.interfaccia.prendiIstruzione();
		while (!processaIstruzione(istruzione));
	}  


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		this.interfaccia.mostraMessaggio(comandoDaEseguire.esegui(this.partita));
		if (this.partita.vinta())
			this.interfaccia.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			this.interfaccia.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

}