package moves;

import ru.ifmo.se.pokemon.*;

public final class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 1);
    }

    @Override protected void applySelfEffects(Pokemon p) {
        p.addEffect(new Effect().stat(Stat.HP, (int) -(12 - p.getHP())).turns(1));
        p.addEffect(new Effect().attack(0).turns(2));
    }

    @Override protected String describe() {
        return "решает отдохнуть";
    }
}
