package src.xmoreau.aircraft;

import src.xmoreau.customexceptions.IncorrectCoordinateValue;
import src.xmoreau.flyable.Flyable;
import src.xmoreau.weather.WeatherTower;
import src.xmoreau.simulator.Simulator;

public class Baloon extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Simulator.WeatherChangeBaloon c = null;
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeBaloon.SUN;
                Simulator.logger.println(this.toString() + ": The sun increases the vapors");
                break;
            case "RAIN":
                c = Simulator.WeatherChangeBaloon.RAIN;
                Simulator.logger.println(this.toString() + ": From where those 'plops' are coming from ?");
                break;
            case "FOG":
                c = Simulator.WeatherChangeBaloon.FOG;
                Simulator.logger.println(this.toString() + ": Is is fog or vapor ?");
                break;
            case "SNOW":
                c = Simulator.WeatherChangeBaloon.SNOW;
                Simulator.logger.println(this.toString() + ": I'm a snowball !");
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