package pokemons;

import moves.*;

public final class Rampardos extends Cranidos {
    public Rampardos(String name, int level) {
        super(name, level);
        setStats(13, 8, 6, 6, 6, 6);
        addMove(new FocusBlast());
    }
}
