package src.aircraft;

import src.Simulator;
import src.customexceptions.IncorrectCoordinateValue;
import src.Flyable;
import src.WeatherTower;

class Baloon extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Simulator.WeatherChangeBaloon c = null;
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeBaloon.SUN;
                System.out.println(this.toString() + ": The sun increases the vapors");
                break;
            case "RAIN":
                c = Simulator.WeatherChangeBaloon.RAIN;
                System.out.println(this.toString() + ": From where those 'plops' are coming from ?");
                break;
            case "FOG":
                c = Simulator.WeatherChangeBaloon.FOG;
                System.out.println(this.toString() + ": Is is fog or vapor ?");
                break;
            case "SNOW":
                c = Simulator.WeatherChangeBaloon.SNOW;
                System.out.println(this.toString() + ": I'm a snowball !");
                break;
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