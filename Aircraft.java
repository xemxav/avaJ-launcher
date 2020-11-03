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

class Helicopter extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }


    public void updateConditions() {
        Simulator.WeatherChangeHelicopter c = null;
        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeHelicopter.SUN;
                System.out.println(this.toString() + ": My rotor is shiny with this sun !");
            case "RAIN":
                c = Simulator.WeatherChangeHelicopter.SUN;
                System.out.println(this.toString() + ": My rotor is cutting the rain in two.");
            case "FOG":
                c = Simulator.WeatherChangeHelicopter.SUN;
                System.out.println(this.toString() + ": Am i going up or down ?");
            case "SNOW":
                c = Simulator.WeatherChangeHelicopter.SUN;
                System.out.println(this.toString() + ": I'm a snow fan !");
        }
        try {
            this.coordinates = new Coordinates(coordinates.getLongitude() + c.changes[0],
                    coordinates.getLatitude() + c.changes[1],
                    coordinates.getHeight() + c.changes[2]);
        } catch (IncorrectCoordinateValue e) {
            weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    public String toString() {
        String str = super.toString();
        return "Helicopter" + str;
    }

}

class JetPlane extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Simulator.WeatherChangeJetPlane c = null;
        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeJetPlane.SUN;
                System.out.println(this.toString() + ": To infinity and beyond !");
            case "RAIN":
                c = Simulator.WeatherChangeJetPlane.SUN;
                System.out.println(this.toString() + ": Highway to the danger zone!");
            case "FOG":
                c = Simulator.WeatherChangeJetPlane.SUN;
                System.out.println(this.toString() + ": I have some goo on my windshield !");
            case "SNOW":
                c = Simulator.WeatherChangeJetPlane.SUN;
                System.out.println(this.toString() + ": I'm ski patrol.");
        }
        try {
            this.coordinates = new Coordinates(coordinates.getLongitude() + c.changes[0],
                    coordinates.getLatitude() + c.changes[1],
                    coordinates.getHeight() + c.changes[2]);
        } catch (IncorrectCoordinateValue e) {
            weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);    }

    public String toString() {
        String str = super.toString();
        return "JetPlane" + str;
    }
}

class Baloon extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Simulator.WeatherChangeBaloon c = null;
        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeBaloon.SUN;
                System.out.println(this.toString() + ": The sun increases the vapors");
            case "RAIN":
                c = Simulator.WeatherChangeBaloon.SUN;
                System.out.println(this.toString() + ": From where those 'plops' are coming from ?");
            case "FOG":
                c = Simulator.WeatherChangeBaloon.SUN;
                System.out.println(this.toString() + ": Is is fog or vapor ?");
            case "SNOW":
                c = Simulator.WeatherChangeBaloon.SUN;
                System.out.println(this.toString() + ": I'm a snowball !");
        }
        try {
            this.coordinates = new Coordinates(coordinates.getLongitude() + c.changes[0],
                    coordinates.getLatitude() + c.changes[1],
                    coordinates.getHeight() + c.changes[2]);
        } catch (IncorrectCoordinateValue e) {
                weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    public String toString() {
        String str = super.toString();
        return "Baloon" + str;
    }
}


