package moves;

import ru.ifmo.se.pokemon.*;

public final class Headbutt extends PhysicalMove {

    public Headbutt() {
        super(Type.NORMAL, 70, 1);
    }

    @Override protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.3) {
            Effect.flinch(p);
        }
    }

    @Override protected String describe() {
        return "ударяет противника головой";
    }
}
