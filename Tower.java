import java.util.ArrayList;

abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
            System.out.println(String.format("Tower says: %s registered to weather tower"));
        } else {
            System.out.println(String.format("%s: already registered", flyable));
        }
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
            System.out.println(String.format("Tower says: %s unregistered from weather tower"));
        } else {
            System.out.println(String.format("%s: is not registered yet", flyable));
        }
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}

