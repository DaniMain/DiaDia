package it.uniroma3.diadia;

import java.util.Scanner;

public class InterfacciaUtenteConsole implements InterfacciaUtente {

	private Scanner scanner;

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String prendiIstruzione() {
		scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		return s;
	}

}
