package pokemons;

import moves.*;

public class Nidorina extends NidoranF {
    public Nidorina(String name, int level) {
        super(name, level);
        setStats(13, 6, 6, 6, 6, 6);
        addMove(new Growl());
    }
}
