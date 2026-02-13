package main;

import ru.ifmo.se.pokemon.*;
import pokemons.*;

public class Main {
    public static void main(String[] args){
        Battle b = new Battle();
        Pokemon p1 = new Dialga("Dialga", 1);
        Pokemon p2 = new Cranidos("Cranidos", 1);
        Pokemon p3 = new Rampardos("Rampardos", 1);
        Pokemon p4 = new NidoranF("NidoranF", 1);
        Pokemon p5 = new Nidorina("Nidorina", 1);
        Pokemon p6 = new Nidoqueen("Nidoqueen", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}