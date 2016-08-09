package net.lorens.code.tdd.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
				
		if (lances.isEmpty() || podeDarLance(lance.getUsuario()) ) {
			lances.add(lance);
		}
	}
	
	public void dobrarLance(Usuario usuario) {
		
		if ( ! lances.isEmpty() ) {
		
			Lance ultimoLanceDoUsuario = getUltimoLanceDo(usuario);
		
			propoe(new Lance(usuario, ultimoLanceDoUsuario.getValor() * 2));
			
		}
		
	}

	private Lance getUltimoLanceDo(Usuario usuario) {
		
		List<Lance> lancesDoUsuario = getLancesDoUsuario(usuario);
		
		return getUltimoLanceDos(lancesDoUsuario);
	}

	private Lance getUltimoLanceDos(List<Lance> lancesDoUsuario) {
		
		return lancesDoUsuario.get(lancesDoUsuario.size()-1);
		
	}

	private List<Lance> getLancesDoUsuario(Usuario usuario) {
		return lances.stream().filter(l -> l.getUsuario().equals(usuario)).collect(Collectors.toList());
	}

	private boolean podeDarLance(Usuario usuario) {
		return ! getUltimoLance().getUsuario().equals(usuario)
				&& lances.stream().filter(l -> l.getUsuario().equals(usuario)).collect(Collectors.toList()).size() < 5 ;
	}


	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public Lance getUltimoLance() {
		return getLances().get(getLances().size()-1);
	}

	
	
}
