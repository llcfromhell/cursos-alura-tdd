package net.lorens.code.tdd.leilao.servico;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import net.lorens.code.tdd.leilao.dominio.Lance;
import net.lorens.code.tdd.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double media = Double.POSITIVE_INFINITY;

	public void avalia(Leilao leilao) {

		List<Lance> lances = leilao.getLances();
		
		for (Lance lance : lances) {

			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}

			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}

		}
		
		if (lances != null) calculaMedia(lances.stream());

	}

	public void calculaMedia(Stream<Lance> lances) {
		
		media = ((OptionalDouble) lances
				  .mapToDouble(l -> l.getValor())
				  .average()).orElse(0);
		
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}

	public double getMediaLances() {
		return media;
	}

}