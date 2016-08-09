package net.lorens.code.tdd.leilao.servico;

import org.junit.Test;

import org.junit.Assert;

public class MatematicaMalucaTest {

	@Test
	public void testaValorMaiorQue30() {
		
		MatematicaMaluca maths = new MatematicaMaluca();
		
		Assert.assertEquals(160, maths.contaMaluca(40));
		
	}
	
	@Test
	public void testaValorMaiorQue10MenorQue30() {
		
		MatematicaMaluca maths = new MatematicaMaluca();
		
		Assert.assertEquals(45, maths.contaMaluca(15));
		
		Assert.assertEquals(90, maths.contaMaluca(30));
		
	}
	
	@Test
	public void testaValorMenorQue10() {
		
		MatematicaMaluca maths = new MatematicaMaluca();
		
		Assert.assertEquals(20, maths.contaMaluca(10));
		
	}
	
	
}
