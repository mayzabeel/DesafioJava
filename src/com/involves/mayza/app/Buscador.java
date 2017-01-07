package com.involves.mayza.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe responsável por realizar as buscas solicitadas pelo usuário.
 * @author Mayza Nunes
 */
public class Buscador {
	
	/**
	 * Método que contabiliza quantidade de valores distintos de uma
	 * propriedade.
	 * 
	 * @param propriedade
	 *            Propriedade na qual será feita a contagem de valores
	 *            distintos.
	 * @return contador Inteiro com a quantidade de valores distintos da
	 *         propriedade
	 */
	public static int countDistinct(BufferedReader bra,String propriedade, HashMap<String, Integer> propriedades) {
		int counter = 0;
		String linha = "";
		int indice = propriedades.get(propriedade);
		ArrayList<String> opcoes = new ArrayList<String>();
		try {
			while ((linha = bra.readLine()) != null) {
				String[] cidade = linha.split(",");
				if (!opcoes.contains(cidade[indice])) {
					opcoes.add(cidade[indice]);
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counter - 1;
	}
	
	/**
	 * Método que contabiliza a quantidade de linhas do arquivo
	 * 
	 * @return counter Inteiro com a quantidade de linhas do arquivo recebido.
	 */
	public static int countAll(BufferedReader bra) {
		int counter = 0;
		try {
			while (bra.readLine() != null) {
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counter - 1;
	}
	
	/**
	 * Método recupera todas as linhas do arquivo que tem o valor de entrada 
	 * na propriedade dada.
	 * 
	 * @param propriedade
	 * 	Propriedade para se verificar o valor
	 * @param valor
	 * 	Valor a ser verificado na propriedade
	 */
	public static ArrayList<String> filter(BufferedReader bra,String propriedade, String valor, HashMap<String, Integer> propriedades) {
		String linha = "";
		int indice = propriedades.get(propriedade);
		ArrayList<String> filtrados = new ArrayList<String>();
		
		try {
			while ((linha = bra.readLine()) != null) {
				String[] cidade = linha.split(",");
				if (cidade[indice].toUpperCase().equals(valor)) {
					filtrados.add(linha);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filtrados;
	}
}
