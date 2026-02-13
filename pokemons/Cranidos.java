package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

public class Cranidos extends Pokemon {
    public Cranidos(String name, int level) {
        super(name, level);
        setStats(12, 8, 6, 6, 6, 6);
        setType(Type.ROCK);
        setMove(new Facade(), new Headbutt(), new RockTomb());
    }
}