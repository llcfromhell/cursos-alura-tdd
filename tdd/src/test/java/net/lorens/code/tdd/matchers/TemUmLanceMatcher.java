package net.lorens.code.tdd.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import net.lorens.code.tdd.leilao.dominio.Leilao;

public class TemUmLanceMatcher extends TypeSafeMatcher<Leilao> {

	Leilao leilao;

	public TemUmLanceMatcher(Leilao leilao) {
		this.leilao = leilao;
	}

	@Override
	protected boolean matchesSafely(Leilao leilao) {
		return leilao.getLances().size() > 0;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("o leilão deveria ter ao menos 1 lance");

	}
	
	@Override 
	protected void describeMismatchSafely(Leilao item, Description mismatchDescription) {
		
		mismatchDescription.appendText("na verdade tinha " + item.getLances().size());
		
	};

	@Factory
	public static TypeSafeMatcher<Leilao> temUmLance(Leilao leilao) {
		return new TemUmLanceMatcher(leilao);
	}

}
