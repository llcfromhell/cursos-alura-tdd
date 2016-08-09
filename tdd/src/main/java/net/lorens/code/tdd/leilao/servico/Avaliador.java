package net.lorens.code.tdd.leilao.servico;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import net.lorens.code.tdd.leilao.dominio.Lance;
import net.lorens.code.tdd.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double media = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {

		List<Lance> lances = leilao.getLances();
		
		if (lances.isEmpty()) 
			throw new RuntimeException("Não pode avaliar leilão sem lances.");
		
		for (Lance lance : lances) {

			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}

			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}

		}
		
		calculaMedia(lances);

		pegaOsMaioresNo(leilao);

	}

	public void calculaMedia(List<Lance> lances) {
		
		if (lances != null) {

			media = ((OptionalDouble) lances.stream()
				  .mapToDouble(l -> l.getValor())
				  .average()).orElse(0);

		}
		
	}

	private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return 1;
                if(o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    // get / set
	
	public List<Lance> getTresMaiores() {
        return this.maiores;
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