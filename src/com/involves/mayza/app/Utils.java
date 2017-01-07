package com.involves.mayza.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Classe com métodos auxiliares.
 * @author Mayza Nunes
 */
public class Utils {
	public Utils() {
	}

	/**
	 * Método que preenche um mapa com chaves e valores.
	 * @param propriedades
	 * 	Mapa a ser preenchido.
	 * @param chaves
	 * 	String com as chaves do mapa separadas por vírgula
	 */
	public static void preencheMapa(HashMap<String, Integer> mapa , String chaves) {
		String[] props = chaves.split(",");
		for (int i = 0; i < props.length; i++) {
			mapa.put(props[i], i);
		}
	}
	
	/**
	 * Método que recupera a primeira linha de um BufferedReader
	 * @param br
	 * 	BufferedReader a ser recuperada primeira linha.
	 * @return
	 * 	String com a primeira linha do arquivo.
	 */
	public static String getPrimeiraLinha(BufferedReader br){
		String primeiraLinha = "";
		try {
			primeiraLinha = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return primeiraLinha;
	}
	
	/**
	 * Metodo que verifica se string é uma das chaves do mapa.
	 * @param mapa
	 * 	Mapa a ser verificada a chave.
	 * @param chave
	 * 	Chave a ser verificada existência no mapa.
	 * @return 
	 * 	True caso a chave pertença ao mapa e False caso contrário.
	 */
	public static boolean isChave(HashMap<String, Integer> mapa , String chave) {
		return mapa.keySet().contains(chave);
	}
	
	/**
	 * Método que faz a leitura do arquivo a partir do seu path.
	 * @param caminhoArquivo
	 * 	Path completo de localização do arquivo
	 * @return {{BufferedReader}} no padrão UTF-8
	 * @throws UnsupportedEncodingException 
	 */
	public static BufferedReader leArquivo(String caminhoArquivo) {
		BufferedReader bra = null;
		try {		
			bra = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoArquivo), "UTF-8"));		
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possível encontrar o seu arquivo, por favor tente novamente.");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Houve um um problema ao carregar o arquivo, tente novamente");
		}
		return bra;
	}
}