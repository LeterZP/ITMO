package main;

import food.*;
import environment.*;

public class Main {
    public static void main(String[] args) {
        MainCharacter character = new MainCharacter("Скуперфильд");
        try {
            while (true) {
                character.goToLocation(new Environment());
            }
        } catch (FatigueException e) {
            character.finalWords();
        }
    }
}