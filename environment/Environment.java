package environment;

import food.*;
import java.util.ArrayList;

public record Environment(ArrayList<Tree> trees,
                          ArrayList<Plant> plants,
                          Weather weather,
                          Ground ground) {
    public Environment {
        if (ground != Ground.ASPHALT && ground != Ground.SAND) {
            if (weather != Weather.WIND) {
                int rand = (int) (Math.random() * 8);
                for (int i = 0; i < rand; i++) {
                    trees.add(new Tree());
                }
            }
            if (weather != Weather.SNOW) {
                int rand = (int) (Math.random() * 8);
                for (int i = 0; i < rand; i++) {
                    plants.add(new Plant());
                }
            }
        }
    }

    public Environment() {
        this(new ArrayList<>(),
                new ArrayList<>(),
                Weather.values()[(int) (Math.random() * Weather.values().length)],
                Ground.values()[(int) (Math.random() * Ground.values().length)]);
    }
}
