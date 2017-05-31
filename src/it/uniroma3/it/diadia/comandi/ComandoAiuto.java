package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;

public class ComandoAiuto implements Comando {
	
	private String parametro;
	
	public ComandoAiuto(String parametro){
		this.parametro = parametro;
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	public void esegui(Partita partita){
		for(int i=0; i< DiaDia.getElencoComandi().length; i++) 
			System.out.print(DiaDia.getElencoComandi()[i]+" ");
		System.out.println();
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getNome(){
		return "aiuto";
	}
	
	public String getParametro() {
		return this.parametro;
	}

}
