abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates; // must be destroyed if aircraft destroyed
    private static long idCounter = 1;

    //set the id of each aircraf with an Instance initialization block ?
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




}
//todo : all of this class must implement the interface Flyable and should register to tower in constructor ?

// can those classes be the subject of the observer class flyable ? or flyable is the subect of yower ?


class Helicopter extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        //register to tower ?
    }


    public void updateConditions() {

    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

}

class JetPlane extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        // switch stateent
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("registered to tower");
    }
}

class Baloon extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {

    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}


