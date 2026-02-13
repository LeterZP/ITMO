package moves;

import ru.ifmo.se.pokemon.*;

public final class FocusBlast extends SpecialMove {
    public FocusBlast() {
        super(Type.FIGHTING, 120, 0.7);
    }

    @Override protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, -1);
            p.addEffect(e);
        }
    }

    @Override protected String describe() {
        return "ментально атакует противника";
    }
}
