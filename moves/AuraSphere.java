package moves;

import ru.ifmo.se.pokemon.*;

public final class AuraSphere extends SpecialMove {
    public AuraSphere() {
        super(Type.FIGHTING, 80, 1);
    }

    @Override protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }

    @Override protected String describe() {
        return "выпускает шар ауры";
    }
}