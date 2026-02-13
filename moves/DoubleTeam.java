package moves;

import ru.ifmo.se.pokemon.*;

public final class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 1);
    }

    @Override protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.EVASION, 1);
        p.addEffect(e);
    }

    @Override protected String describe() {
        return "быстро перемещается";
    }
}