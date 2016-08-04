package net.lorens.code.tdd.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import net.lorens.code.tdd.leilao.dominio.*;

public class AvaliadorTest {

    Usuario joao = new Usuario("Joao");
    Usuario jose = new Usuario("José");
    Usuario maria = new Usuario("Maria");
    
    @Test
    public void validaResultadosEmQualquerOrdem() {
        
        double maiorEsperado = 400;
        double menorEsperado = 250;

        double mediaEsperada = 316.66;
        
        Leilao leilao = new Leilao("GTX 1060");
        
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(jose, 250));
        leilao.propoe(new Lance(joao, 300.0));
        
        Avaliador leiloeiro = new Avaliador();
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
        
        Leilao leilao = new Leilao("GTX 1060");
        
        leilao.propoe(new Lance(maria, menorEsperado));
        
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0);
        
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0);
        
        assertEquals(mediaEsperada, leiloeiro.getMediaLances(), 0.01);
                
    }
    

    @Test
    public void validaMaiorMenorEmOrdemDecrescente() {
        
        double maiorEsperado = 400;
        double menorEsperado = 250;

        Leilao leilao = new Leilao("GTX 1060");
        
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(joao, 300));
        leilao.propoe(new Lance(joao, 299));
        leilao.propoe(new Lance(joao, 289));
        leilao.propoe(new Lance(joao, 255));
        leilao.propoe(new Lance(jose, 250));
        
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0);
        
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0);
                
    }

    @Test
    public void valida3MaioresDe5Lances() {
        
        List<Double> maiorEsperados = new ArrayList<Double>(){{ add(500.0); add(400.0); add(300.0); }};

        Leilao leilao = new Leilao("GTX 1060");
        
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(joao, 300));
        leilao.propoe(new Lance(joao, 289));
        leilao.propoe(new Lance(joao, 500));
        leilao.propoe(new Lance(joao, 255));
        
        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);


        for (int i = 0; i < 3; i++) {

            assertEquals(maiorEsperados.get(i), leiloeiro.getTresMaiores().get(i).getValor(), 0.01);

        }
        
                
    }

    @Test
    public void valida3MaioresDe2Lances() {

        Leilao leilao = new Leilao("GTX 1060");
        
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(joao, 500));

        
        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        assertEquals(leiloeiro.getTresMaiores().size(), 2);
                
    }

    @Test
    public void valida3MaioresDeLeilaoVazio() {

        Leilao leilao = new Leilao("GTX 1060");
        
        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        assertTrue(leiloeiro.getTresMaiores().isEmpty());
                
    }
    
    
}