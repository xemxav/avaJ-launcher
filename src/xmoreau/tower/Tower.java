package src.xmoreau.tower;

import src.xmoreau.flyable.Flyable;
import src.xmoreau.simulator.Simulator;

import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
            Simulator.logger.printf("Tower says: %s registered to weather tower.%n", flyable);
        } else {
            Simulator.logger.printf("%s: already registered.%n", flyable);
        }
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
            Simulator.logger.printf("Tower says: %s unregistered from weather tower.%n", flyable);
        } else {
            Simulator.logger.printf("%s: is not registered yet.%n", flyable);
        }
    }

    protected void conditionsChanged() {
        for(int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}

