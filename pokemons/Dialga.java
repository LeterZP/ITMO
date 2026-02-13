package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

public final class Dialga extends Pokemon {
    public Dialga(String name, int level) {
        super(name, level);
        setStats(13, 8, 8, 8, 7, 7);
        setType(Type.STEEL, Type.DRAGON);
        setMove(new Confide(), new Thunder(), new DoubleTeam(), new AuraSphere());
    }
}
