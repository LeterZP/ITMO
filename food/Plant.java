package food;

import java.util.ArrayList;

public class Plant {
    private ArrayList<Vegetable> vegetables = new ArrayList<>();

    public Plant() {
        int rand = (int) (Math.random() * 5) + 1;
        for (int i = 0; i < (int) (Math.random() * 11) + 1; i++) {
            switch (rand) {
                case 1: addVegetable(new Potato()); break;
                case 2: addVegetable(new Carrot()); break;
                case 3: addVegetable(new Beetroot()); break;
                case 4: addVegetable(new Garlic()); break;
                case 5: addVegetable(new Radish()); break;
            }
        }
    }

    @Override
    public String toString() {
        return "растение с " + this.getVegetables().get(0).toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() == object.getClass()) {
            Plant plant = (Plant) object;
            if (this.getVegetablesAmount() != plant.getVegetablesAmount()) return false;
            for (int i = 0; i < this.getVegetablesAmount(); i++) {
                if (this.getVegetables().get(i) != plant.getVegetables().get(i)) return false;
            }
            return true;
        } return false;
    }

    @Override
    public int hashCode() {
        int total = 0;
        for (int i = 0; i < this.getVegetablesAmount(); i++) {
            total = 31 * total + this.getVegetables().get(i).hashCode();
        }
        return total;
    }

    public ArrayList<Vegetable> getVegetables() {
        return vegetables;
    }

    public int getVegetablesAmount() {
        return vegetables.size();
    }

    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }
}
