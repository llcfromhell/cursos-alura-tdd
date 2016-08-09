package net.lorens.code.tdd.leilao.servico;

public class MatematicaMaluca {

    public long contaMaluca(long numero) {
        if (numero > 30)
            return numero * 4;
        else if (numero > 10)
            return numero * 3;
        else
            return numero * 2;
    }
}