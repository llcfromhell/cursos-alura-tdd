package net.lorens.code.tdd.leilao.servico;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.lorens.code.tdd.builder.CriadorDeLeilao;
import net.lorens.code.tdd.leilao.dominio.Lance;
import net.lorens.code.tdd.leilao.dominio.Leilao;
import net.lorens.code.tdd.leilao.dominio.Usuario;
import net.lorens.code.tdd.matchers.TemUmLanceMatcher;

public class AvaliadorTest {

	Usuario joao = new Usuario("Joao");
	Usuario jose = new Usuario("José");
	Usuario maria = new Usuario("Maria");
	private Avaliador leiloeiro;

	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}

	@Before
	public void criaAvaliador() {
		System.out.println("inicializando teste!");
		leiloeiro = new Avaliador();
	}

	@After
	public void finaliza() {
		System.out.println("fim");
	}

	@Test
	public void validaResultadosEmQualquerOrdem() {

		double maiorEsperado = 400;
		double menorEsperado = 250;

		double mediaEsperada = 316.66;

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").lance(maria, 400).lance(jose, 250).lance(joao, 300)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0);

		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0);

		assertEquals(mediaEsperada, leiloeiro.getMediaLances(), 0.01);

	}

	@Test
	public void validaResultadosCom1LanceApenas() {

		double maiorEsperado = 200;
		double menorEsperado = 200;

		double mediaEsperada = 200;

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").lance(maria, menorEsperado).constroi();

		leiloeiro.avalia(leilao);

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0);

		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0);

		assertEquals(mediaEsperada, leiloeiro.getMediaLances(), 0.01);

	}

	@Test
	public void validaMaiorMenorEmOrdemDecrescente() {

		double maiorEsperado = 400;
		double menorEsperado = 250;

		double mediaEsperada = 316.66;

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").lance(maria, 400).lance(joao, 300).lance(jose, 250)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0);

		assertEquals(mediaEsperada, leiloeiro.getMediaLances(), 0.01);

	}

	@Test
	public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").lance(maria, 1000).lance(joao, 2000).lance(jose, 3000)
				.constroi();

		leiloeiro.avalia(leilao);

		Assert.assertEquals(3000, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);

	}

	@Test
	public void valida3MaioresDe5Lances() {

		List<Double> maiorEsperados = new ArrayList<Double>() {
			{
				add(500.0);
				add(400.0);
				add(300.0);
			}
		};

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").lance(maria, 400).lance(joao, 300).lance(maria, 289)
				.lance(joao, 500).lance(maria, 255).constroi();

		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getTresMaiores(), hasItems(
				new Lance(maria, 400),
				new Lance(joao, 300),
				new Lance(joao, 500)));
		

	}

	@Test
	public void valida3MaioresDe2Lances() {

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").lance(maria, 400).lance(joao, 500).constroi();

		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getTresMaiores(), hasSize(2));

	}

	@Test(expected=RuntimeException.class)
	public void valida3MaioresDeLeilaoVazioEsperaExcecao() {

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060").constroi();

		leiloeiro.avalia(leilao);

		assertTrue(leiloeiro.getTresMaiores().isEmpty());

	}
	
	@Test
	public void verificaSeLeilaoTemUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("GTX 1060")
				//.lance(maria, 200)
				.constroi();

		assertThat(leilao, TemUmLanceMatcher.temUmLance(leilao));
		

	}

}