package net.lorens.code.tdd.leilao.servico;


import org.junit.Assert;
import org.junit.Test;

import net.lorens.code.tdd.leilao.dominio.*;

public class AvaliadorTest {
    
    @Test
    public void validaResultadosEmQualquerOrdem() {
        
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        
        double maiorEsperado = 400;
        double menorEsperado = 250;
        double mediaEsperada = 316.66;
        
        Leilao leilao = new Leilao("GTX 1060");
        
        leilao.propoe(new Lance(maria, menorEsperado));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, maiorEsperado));
        
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0);
        
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0);
        
        Assert.assertEquals(mediaEsperada, leiloeiro.getMediaLances(), 0.01);
                
    }
    
    
}