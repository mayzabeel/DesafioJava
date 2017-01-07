package com.involves.mayza.app;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe respons�vel pela itera��o com o usu�rio. 
 * Aplica��o recebe a localiza��o de um arquivo csv e permite que o usu�rio fa�a algumas consultas no arquivo.
 * @author Mayza Nunes
 */
public class LeiaCSV {
	HashMap<String, Integer> propriedades = new HashMap<String, Integer>();
	private Scanner input;

	public static void main(String[] args) {
		LeiaCSV obj = new LeiaCSV();
		obj.run();
	}

	/**
	 * M�todo que solicita entrada do usu�rio com o caminho de localiza��o do
	 * arquivo
	 * 
	 * @return String com caminho de localiza��o do arquivo.
	 */
	private String inputPath() {
		System.out.println("Insira o caminho completo do seu arquivo csv. "
				+ "(Exemplo:C:/Users/Mayza Nunes/arquivo.csv)");
		String arquivoCSV = new Scanner(System.in).nextLine();
		return arquivoCSV;
	}

	public void run() {
		String caminho = "";
		BufferedReader br = null;
		while (br == null) {
			caminho = inputPath();
			br = Utils.leArquivo(caminho);
		}
		String linhaPropriedades = Utils.getPrimeiraLinha(br).toUpperCase();
		Utils.preencheMapa(propriedades, linhaPropriedades);
		System.out.println("Arquivo carregado. Agora voc� pode realizar as consultas: count *, count distinct [propriedade] ou filter [propriedade] [valor]");

		while (true) {
			br = Utils.leArquivo(caminho);

			input = new Scanner(System.in);
			String comando = input.nextLine().toUpperCase();
			String[] comandList = comando.split(" ");

			switch (comandList[0]) {
			case "COUNT":
				count(comandList, br);
				break;
			case "FILTER":
				filtra(comandList, br, linhaPropriedades);
				break;
			default:
				System.out.println("N�o reconhe�o esse comando. Comandos aceitos: count *, count distinct [propriedade] ou filter [propriedade] [valor]");
				break;
			}
		}
	}

	/**
	 * M�todo que trata comandos count
	 * @param comandList 
	 * 	array com palavras contidas no comando
	 * @param br 
	 * 	BufferedReader no qual ser� feita a busca.
	 */
	private void count(String[] comandList, BufferedReader br) {
		if (comandList.length == 3 && comandList[1].equals("DISTINCT") && Utils.isChave(propriedades, comandList[2])) {
			int num = Buscador.countDistinct(br, comandList[2], propriedades);
			System.out.println(num);
		} else if (comandList.length == 2 && comandList[1].equals("*")) {
			System.out.println(Buscador.countAll(br));
		} else {
			System.out.println("Sintaxe do count incorreta. Tente count * ou count distinct [propriedade]");
		}
	}

	/**
	 * M�todo que trata o comando filter
	 * @param comandList
	 * 	array com palavras contidas no comando
	 * @param br
	 * 	BufferedReader no qual ser� feita a busca.
	 * @param linhaPropriedades
	 * 	String com propriedades do arquivo.
	 */
	private void filtra(String[] comandList, BufferedReader br, String linhaPropriedades) {
		if (comandList.length == 3 && Utils.isChave(propriedades, comandList[1])) {
			ArrayList<String> filtrados = Buscador.filter(br, comandList[1],
					comandList[2], propriedades);
			if (filtrados.isEmpty()) {
				System.out.println("Nenhum registro foi encontrado");
			} else {
				System.out.println(linhaPropriedades);
				for (String cidade : filtrados) {
					System.out.println(cidade);
				}
			}
		} else {
			System.out.println("Sintaxe do filter incorreta. Tente: filter [propriedade] [valor]");
		}
	}
}
