package moves;

import ru.ifmo.se.pokemon.*;

public final class Growl extends StatusMove {
    public Growl() {
        super(Type.NORMAL, 0, 1);
    }

    @Override protected void applyOppEffects(Pokemon p) {
        p.addEffect(new Effect().stat(Stat.ATTACK, -1));
    }

    @Override protected String describe() {
        return "рычит на противника";
    }
}
