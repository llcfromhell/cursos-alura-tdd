package net.lorens.code.tdd.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LeilaoTest {
	
	
	Usuario bill = new Usuario("Bill Gates");
	Usuario steve = new Usuario("Steve Jobs");
	Usuario lenovo = new Usuario("Lenovo");
	
	Leilao leilao;
	
	@Before
	public void criaLeilao(){
		
		leilao = new Leilao("Macbook Pro 15");
		
	};
	
	@Test
	public void deveReceberUmLance() {
		
		assertEquals(0, leilao.getLances().size());		
		
		leilao.propoe(new Lance(bill, 1000));
		
		assertEquals(1, leilao.getLances().size());
		
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		assertEquals(0, leilao.getLances().size());		
		
		leilao.propoe(new Lance(bill, 1000));
		leilao.propoe(new Lance(bill, 2000));
		
		assertEquals(1, leilao.getLances().size());
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		
		assertEquals(0, leilao.getLances().size());		
		
		leilao.propoe(new Lance(bill, 1000));
		leilao.propoe(new Lance(steve, 1000));
		
		leilao.propoe(new Lance(bill, 2000));
		leilao.propoe(new Lance(steve, 2000));
		
		leilao.propoe(new Lance(bill, 3000));
		leilao.propoe(new Lance(steve, 3000));
		
		leilao.propoe(new Lance(bill, 4000));
		leilao.propoe(new Lance(steve, 4000));
		
		leilao.propoe(new Lance(bill, 5000));
		leilao.propoe(new Lance(steve, 5000));
		
		leilao.propoe(new Lance(bill, 6000));
		
		assertEquals(10, leilao.getLances().size());
		
	}
	
	@Test
	public void dobrarOUltimoLanceDoUsuarioSemSerOUltimo() {
		
		leilao.propoe(new Lance(bill, 200));
		leilao.propoe(new Lance(steve, 300));
		
		assertEquals(300, leilao.getUltimoLance().getValor(), 0.01);
		
		leilao.dobrarLance(bill);
		
		assertEquals(400, leilao.getUltimoLance().getValor(), 0.01);
		
	}

	@Test
	public void naoDeveDobrarLanceSeFoiOUltimo() {
		
		leilao.propoe(new Lance(bill, 200));
		assertEquals(200, leilao.getUltimoLance().getValor(), 0.01);
		
		leilao.dobrarLance(bill);
		
		assertEquals(200, leilao.getUltimoLance().getValor(), 0.01);
		
	}
	
	@Test
	public void naoDeveDobrarLanceSeJaTem5() {
		
		leilao.propoe(new Lance(bill, 200));
		leilao.propoe(new Lance(steve, 300));
		
		leilao.propoe(new Lance(bill, 310));
		leilao.propoe(new Lance(steve, 320));
		
		leilao.propoe(new Lance(bill, 330));
		leilao.propoe(new Lance(steve, 340));
		
		leilao.propoe(new Lance(bill, 350));
		leilao.propoe(new Lance(steve, 360));
		
		leilao.dobrarLance(bill);
		assertEquals(700, leilao.getUltimoLance().getValor(), 0.01);
		
		leilao.propoe(new Lance(steve, 400));
		
		leilao.dobrarLance(bill);
		assertEquals(400, leilao.getUltimoLance().getValor(), 0.01);
		
	}

}
