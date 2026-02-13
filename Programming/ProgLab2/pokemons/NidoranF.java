package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

public class NidoranF extends Pokemon {
    public NidoranF(String name, int level) {
        super(name, level);
        setStats(12, 6, 6, 6, 6, 6);
        setType(Type.POISON);
        setMove(new Thunderbolt(), new Rest());
    }
}
