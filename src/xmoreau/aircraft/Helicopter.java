package src.xmoreau.aircraft;

import src.xmoreau.customexceptions.IncorrectCoordinateValue;
import src.xmoreau.flyable.Flyable;
import src.xmoreau.weather.WeatherTower;
import src.xmoreau.simulator.Simulator;

class Helicopter extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }


    public void updateConditions() {
        Simulator.WeatherChangeHelicopter c = null;
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeHelicopter.SUN;
                Simulator.logger.println(this.toString() + ": My rotor is shiny with this sun !");
                break;
            case "RAIN":
                c = Simulator.WeatherChangeHelicopter.RAIN;
                Simulator.logger.println(this.toString() + ": My rotor is cutting the rain in two.");
                break;
            case "FOG":
                c = Simulator.WeatherChangeHelicopter.FOG;
                Simulator.logger.println(this.toString() + ": Am i going up or down ?");
                break;
            case "SNOW":
                c = Simulator.WeatherChangeHelicopter.SNOW;
                Simulator.logger.println(this.toString() + ": I'm a snow fan !");
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
        return "Helicopter" + str;
    }

}

