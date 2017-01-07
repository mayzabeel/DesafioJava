package com.involves.mayza.teste;

import java.io.FileNotFoundException;
import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import com.involves.mayza.app.Utils;

/**
 * Classe de testes referentes à classe Utils.java
 * @author Mayza Nunes
 */
public class UtilsTest extends TestCase{
	
	@Test
	public void testPreencheMapa() {
		HashMap<String, Integer> mapa = new HashMap<String, Integer>();
		String chaves = "chave1,chave2,chave3";
		Assert.assertTrue(mapa.isEmpty());
		
		Utils.preencheMapa(mapa, chaves);
		
		Assert.assertTrue(mapa.keySet().contains("chave1"));
		Assert.assertTrue(mapa.keySet().contains("chave2"));
		Assert.assertTrue(mapa.keySet().contains("chave3"));
	}
	
	@Test
	public void testisChave() throws FileNotFoundException {
		HashMap<String, Integer> mapa = new HashMap<String, Integer>();
		mapa.put("chave1", 1);
		Assert.assertTrue(Utils.isChave(mapa, "chave1"));
		Assert.assertFalse(Utils.isChave(mapa, "chave2"));
	}	
}