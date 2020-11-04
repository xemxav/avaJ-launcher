package simulator;

import simulator.flyable.Flyable;

import java.util.ArrayList;

abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
            System.out.printf("simulator.Tower says: %s registered to weather tower.%n", flyable);
        } else {
            System.out.printf("%s: already registered.%n", flyable);
        }
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
            System.out.printf("simulator.Tower says: %s unregistered from weather tower.%n", flyable);
        } else {
            System.out.printf("%s: is not registered yet.%n", flyable);
        }
    }

    protected void conditionsChanged() {
        for(int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
