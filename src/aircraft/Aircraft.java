package src.aircraft;

abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    {
        id = nextId();
    }

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    private static long nextId() {
        return idCounter++;
    }

    public String toString() {
        return String.format("#%s(%d)", name, id);
    }
}
