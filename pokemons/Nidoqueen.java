package pokemons;

import moves.*;

public final class Nidoqueen extends Nidorina {
    public Nidoqueen(String name, int level) {
        super(name, level);
        setStats(13, 7, 7, 7, 7, 7);
        addMove(new RockSlide());
    }
}
