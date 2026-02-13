package moves;

import ru.ifmo.se.pokemon.*;

public final class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 1);
    }

    @Override protected void applySelfEffects(Pokemon p) {
        if (p.getCondition() == Status.PARALYZE ||
                p.getCondition() == Status.BURN ||
                p.getCondition() == Status.POISON) {
            Effect e = new Effect().stat(Stat.ATTACK, 1);
            p.addEffect(e);
        }
    }

    @Override protected String describe() {
        return "показывает силу";
    }
}
