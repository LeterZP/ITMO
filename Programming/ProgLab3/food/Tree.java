package food;

import java.util.ArrayList;

public class Tree {
    private ArrayList<Fruit> fruits = new ArrayList<>();

    public Tree() {
        int rand = (int) (Math.random()*3) + 1;
        for (int i = 0; i < (int) (Math.random() * 11) + 1; i++) {
            switch (rand) {
                case 1: addFruit(new Apple()); break;
                case 2: addFruit(new Banana()); break;
                case 3: addFruit(new Pear()); break;
            }
        }
    }

    @Override
    public String toString() {
        return "дерево с " + this.getFruits().get(0).toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() == object.getClass()) {
            Tree tree = (Tree) object;
            if (this.getFruitsAmount() != tree.getFruitsAmount()) return false;
            for (int i = 0; i < this.getFruitsAmount(); i++) {
                if (this.getFruits().get(i) != tree.getFruits().get(i)) return false;
            }
            return true;
        } return false;
    }

    @Override
    public int hashCode() {
        int total = 0;
        for (int i = 0; i < this.getFruitsAmount(); i++) {
            total = 31 * total + this.getFruits().get(i).hashCode();
        }
        return total;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public int getFruitsAmount() {
        return fruits.size();
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }
}