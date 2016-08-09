package net.lorens.code.tdd.builder;

import net.lorens.code.tdd.leilao.dominio.Lance;
import net.lorens.code.tdd.leilao.dominio.Leilao;
import net.lorens.code.tdd.leilao.dominio.Usuario;

public class CriadorDeLeilao {

	private Leilao leilao;

	public CriadorDeLeilao() {};
	
	public CriadorDeLeilao para(String descricao) {
		leilao = new Leilao(descricao);
		return this;
	}

	public CriadorDeLeilao lance(Usuario usuario, double valor) {
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}

	public Leilao constroi() {
		return leilao;
	}

	
	
}
