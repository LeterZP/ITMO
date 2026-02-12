package environment;

import java.util.ArrayList;
import food.*;

public class MainCharacter {
    private final String name;
    private double mood;
    private double energy;
    private ArrayList<Eatable> inventory;
    private Environment location;

    public MainCharacter(String name) {
        this.name = name;
        this.mood = Math.random();
        this.energy = Math.random();
        this.inventory = new ArrayList<>();
    }

    public String toString() {
        return this.getName();
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() == object.getClass()) {
            MainCharacter character = (MainCharacter) object;
            return this.getName().equals(character.getName());
        } return false;
    }

    public int hashCode() {
        return this.getName().hashCode();
    }

    public String getName() {
        return this.name;
    }

    public double getMood() {
        return this.mood;
    }

    public double getEnergy() {
        return this.energy;
    }

    public ArrayList<Eatable> getInventory() {
        return this.inventory;
    }

    public Environment getLocation() {
        return this.location;
    }

    public void setMood(double mood) {
        this.mood = mood;
        if (this.getMood() > 1) this.mood = 1;
        if (this.getMood() < 0) this.mood = 0;
    }

    public void setEnergy(double energy) throws FatigueException {
        this.energy = energy;
        if (this.getEnergy() > 1) this.energy = 1;
        if (this.getEnergy() < 0) {
            System.out.println(this + " устал.");
            throw new FatigueException();
        }
    }

    public void changeMood(double change) {
        this.setMood(this.getMood() + change);
    }

    public void changeEnergy(double change) {
        this.setEnergy(this.getEnergy() + change);
    }

    public void addToInventory(Eatable eatable) {
        this.getInventory().add(eatable);
    }

    public void finalWords() {
        if (this.getMood() < 0.25) {
            System.out.println(this + " проклинает всех, кто выращивал эти посевы.");
        } else if (this.getMood() < 0.5) {
            System.out.println(this + " слегка недоволен своим небольшим путешествием.");
        } else if (this.getMood() < 0.75) {
            System.out.println(this + " остался доволен и сыт.");
        } else {
            System.out.println(this + " боготворит тех, кто посадил такие вкусные посевы.");
        }
    }

    public void goToLocation(Environment env) {
        System.out.println(this + " перемещается в новую локацию.");
        this.location = env;
        try {
            this.lookAround(env);
        } catch (BadVisibilityException e) {
            this.goToLocation(new Environment());
        }

    }

    public void lookAround(Environment env) throws BadVisibilityException {
        System.out.println(this + " осматривается.");
        this.changeEnergy(-0.1);
        System.out.println(this + " видит " + env.weather() + " и " + env.ground() + ".");
        switch (env.weather()) {
            case SUN:
                this.changeMood(0.08);
                System.out.println(this + " радуется " + env.weather() + ".");
                break;
            case FOG:
                System.out.println(env.weather() + " блокирует видимость, поэтому " + this + " идет дальше.");
                throw new BadVisibilityException();
        }
        this.changeMood(env.ground().getComfort() - 0.5);
        if (env.ground().getComfort() < 0.5) {
            System.out.println(this + " не нравится идти по " + env.ground() + ".");
        } else {
            System.out.println(this + " нравится идти по " + env.ground() + ".");
        }
        try {
            if (Math.random() < 0.5 && !env.trees().isEmpty()) {
                Tree tree = env.trees().get((int) (Math.random() * env.trees().size()));
                System.out.println(this + " замечает " + tree + ".");
                this.snatchCrop(tree);
            } else if (!env.plants().isEmpty()) {
                Plant plant = env.plants().get((int) (Math.random() * env.plants().size()));
                System.out.println(this + " замечает " + plant + ".");
                this.snatchCrop(plant);
            } else {
                System.out.println(this + " не замечает никаких посевов.");
            }
        } catch (EmptyCropException e) {
            System.out.println(this + " не находит посевов, поэтому теряет интерес.");
        }
    }

    public void snatchCrop(Plant plant) throws EmptyCropException {
        this.changeEnergy(-0.03);
        if (plant.getVegetablesAmount() == 0) throw new EmptyCropException();
        int rand = (int) (Math.random()*plant.getVegetablesAmount());
        System.out.println(this + " срывает " + plant.getVegetables().get(rand) + ".");
        addToInventory(plant.getVegetables().get(rand));
        plant.getVegetables().remove(rand);
        if (Math.random() < 0.8) {
            this.analyseCrop(this.getInventory().get(this.getInventory().size()-1));
        }
        if (Math.random() < 0.4 && plant.getVegetablesAmount() != 0) {
            System.out.println(this + " решает собрать больше посевов.");
            rand = (int) (Math.random() * plant.getVegetablesAmount());
            snatchCrop(plant, rand);
        }
    }

    public void snatchCrop(Tree tree) throws EmptyCropException {
        this.changeEnergy(-0.03);
        if (tree.getFruitsAmount() == 0) throw new EmptyCropException();
        int rand = (int) (Math.random()*tree.getFruitsAmount());
        System.out.println(this + " срывает " + tree.getFruits().get(rand) + ".");
        addToInventory(tree.getFruits().get(rand));
        tree.getFruits().remove(rand);
        if (Math.random() < 0.8) {
            this.analyseCrop(this.getInventory().get(this.getInventory().size()-1));
        }
        if (Math.random() < 0.4 && tree.getFruitsAmount() != 0) {
            System.out.println(this + " решает собрать больше посевов.");
            rand = (int) (Math.random() * tree.getFruitsAmount() + 1);
            snatchCrop(tree, rand);
        }
    }

    public void snatchCrop(Plant plant, int count) {
        for (int i = 0; i < count; i++) {
            addToInventory(plant.getVegetables().get(plant.getVegetablesAmount()-1));
            plant.getVegetables().remove(plant.getVegetablesAmount()-1);
        }
        System.out.println(this + " собрал " + count + " " + this.getInventory().get(this.getInventory().size()-1) + ".");
    }

    public void snatchCrop(Tree tree, int count) {
        for (int i = 0; i < count; i++) {
            addToInventory(tree.getFruits().get(tree.getFruitsAmount()-1));
            tree.getFruits().remove(tree.getFruitsAmount()-1);
        }
        System.out.println(this + " собрал " + count + " " + this.getInventory().get(this.getInventory().size()-1) + ".");
    }

    public void analyseCrop(Eatable eatable) {
        if (Math.random() < 0.5) {
            this.guessFoodType();
        }
        if (Math.random() < 0.7) {
            this.guessCrop();
        }
        if (Math.random() < 0.9) {
            tasteCrop(eatable);
        }
    }

    public void guessFoodType() {
        this.changeEnergy(-0.01);
        if (Math.random() < 0.5) {
            this.changeMood(0.05);
            System.out.println(this + " угадывает место произростания посева.");
        } else {
            this.changeMood(-0.05);
            System.out.println(this + " не угадывает место произростания посева.");
        }
    }

    public void guessCrop() {
        this.changeEnergy(-0.01);
        if (Math.random() < 0.5) {
            this.changeMood(0.08);
            System.out.println(this + " угадывает вид посева.");
        } else {
            this.changeMood(-0.08);
            System.out.println(this + " не угадывает вид посева.");
        }
    }

    public void tasteCrop(Eatable eatable) {
        if (eatable instanceof Vegetable) {
            Vegetable vegetable = (Vegetable) eatable;
            if (Math.random() < 0.1) {
                vegetable.setCookedState(State.values()[(int) (Math.random() * 3 + 1)]);
                System.out.println(this + " готовит " + vegetable + "," +
                        " получая " + vegetable.getCookedState() + " " + vegetable + ".");
            }
            if (Math.random() < 0.1) {
                vegetable.peel();
                System.out.println(this + " чистит " + vegetable + ".");
            }
        }
        System.out.println(this + " пробует " + eatable + ".");
        if (Math.random() < eatable.getTaste()) {
            this.changeMood(0.3);
            System.out.println(this + " доволен.");
        } else {
            this.changeMood(-0.3);
            System.out.println(this + " недоволен.");
        }
    }

    public void stumble() {
        this.changeMood(-0.1);
        System.out.println(this + " споткнулся.");
    }
}
