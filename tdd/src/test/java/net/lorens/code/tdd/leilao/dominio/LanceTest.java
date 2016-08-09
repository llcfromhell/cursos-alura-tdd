package net.lorens.code.tdd.leilao.dominio;

import org.junit.Test;

public class LanceTest {

	@Test(expected=IllegalArgumentException.class)
	public void naoPermiteValorNegativo() {
		Lance lance = new Lance(new Usuario("ted"), -200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPermiteValorIgualAZero() {
		Lance lance = new Lance(new Usuario("ted"), 0);
	}
	
}
