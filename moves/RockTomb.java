package moves;

import ru.ifmo.se.pokemon.*;

public final class RockTomb extends PhysicalMove {
    public RockTomb() {
        super(Type.ROCK, 60, 0.95);
    }

    @Override protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.SPEED, -1);
        p.addEffect(e);
    }

    @Override protected String describe() {
        return "запускает камни в противника";
    }
}
