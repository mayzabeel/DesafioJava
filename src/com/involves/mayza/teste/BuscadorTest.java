package com.involves.mayza.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.involves.mayza.app.Buscador;
import com.involves.mayza.app.Utils;

/**
 * Classe de testes referentes à classe Buscador.java
 * @author Mayza Nunes
 */
public class BuscadorTest {
	private BufferedReader in = null;
	
    @Before
    public void setup() throws IOException {
        in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/arq.csv")));
    }
    
    @Test
	public void testCountAll() {
		int total = Buscador.countAll(in);
        Assert.assertEquals("Total de linhas de dados no arquivo arq.csv deveria ser 9",total, 9);
	}
    
	@Test
	public void testCountDistinct() throws IOException {
		HashMap<String, Integer> propriedades = new HashMap<String, Integer>();
		Utils.preencheMapa(propriedades, "ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion");
		int total = Buscador.countDistinct(in, "uf", propriedades);
        
		Assert.assertEquals("Total de linhas com a propriedade UF distinta deveria ser 2",total, 2);
	}
	
	@Test
	public void testFilterUF() throws IOException {
		HashMap<String, Integer> propriedades = new HashMap<String, Integer>();
		Utils.preencheMapa(propriedades, "ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion");
		ArrayList<String> filtrados = Buscador.filter(in, "uf", "PB", propriedades);
        
		Assert.assertEquals(filtrados.size(), 2);
		Assert.assertTrue(filtrados.contains("2509701,PB,Monteiro,,-37.1226806806,-7.892217,Monteiro,,Cariri Ocidental,Borborema"));
		Assert.assertTrue(filtrados.contains("2509800,PB,Mulungu,,-35.4651434243,-7.0278860316,Mulungu,,Guarabira,Agreste Paraibano"));	
	}
	
	@Test
	public void testFilterName()  {
		HashMap<String, Integer> propriedades = new HashMap<String, Integer>();
		Utils.preencheMapa(propriedades, "ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion");
		ArrayList<String> filtrados = Buscador.filter(in, "name", "CACOAL", propriedades);
        
		Assert.assertEquals(filtrados.size(), 1);
		Assert.assertTrue(filtrados.contains("1100049,RO,Cacoal,,-61.4429442118,-11.4338650287,Cacoal,,Cacoal,Leste Rondoniense"));
	}
	
	@Test
	public void testFilterNenhumValor()  {
		HashMap<String, Integer> propriedades = new HashMap<String, Integer>();
		Utils.preencheMapa(propriedades, "ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion");
		ArrayList<String> filtrados = Buscador.filter(in, "name", "nada", propriedades);
        
		Assert.assertEquals(filtrados.size(), 0);
	}
	

}
