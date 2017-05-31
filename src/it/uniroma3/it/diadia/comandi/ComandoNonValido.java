package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione di un comando non valido cioè non presente nel gioco
 * Stampa semplicemente su schermo che il comando non è valido
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class ComandoNonValido implements Comando {

	private String parametro;
	
	public ComandoNonValido(String parametro){
		this.parametro = parametro;
	}
	
	public void esegui(Partita partita){
		System.out.println("Comando non valido");
	}
	
	public void setParametro(String parametro){
		this.parametro = parametro;
	}
	
	public String getNome(){
		return "non valido";
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
}
