package net.lorens.code.tdd.datas;

import org.junit.Assert;
import org.junit.Test;

public class AnoBissextoTest {

	AnoBissexto ano = new AnoBissexto();
	
	
	@Test
	public void testa4Em4Anos() {
		
		
		Assert.assertTrue(ano.ehBissexto(8));
		Assert.assertTrue(ano.ehBissexto(1996));
		Assert.assertTrue(ano.ehBissexto(2004));
		
	}
	
	@Test
	public void testa100Em100Anos() {
		
		Assert.assertFalse(ano.ehBissexto(100));
		Assert.assertFalse(ano.ehBissexto(200));
		Assert.assertFalse(ano.ehBissexto(3000));
		
	}
	
	@Test
	public void testa400Em400Anos() {
		
		Assert.assertTrue(ano.ehBissexto(400));
		Assert.assertTrue(ano.ehBissexto(800));
		Assert.assertTrue(ano.ehBissexto(2000));
		
	}
	
	@Test
	public void testaAnoNaoBissextoQualquer() {
		
		Assert.assertFalse(ano.ehBissexto(666));
		Assert.assertFalse(ano.ehBissexto(1666));
		Assert.assertFalse(ano.ehBissexto(1987));
		
	}
	
}
