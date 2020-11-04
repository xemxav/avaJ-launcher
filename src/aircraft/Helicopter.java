package src.aircraft;

import src.customexceptions.IncorrectCoordinateValue;
import src.Flyable;
import src.Simulator;
import src.WeatherTower;

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
                System.out.println(this.toString() + ": My rotor is shiny with this sun !");
                break;
            case "RAIN":
                c = Simulator.WeatherChangeHelicopter.RAIN;
                System.out.println(this.toString() + ": My rotor is cutting the rain in two.");
                break;
            case "FOG":
                c = Simulator.WeatherChangeHelicopter.FOG;
                System.out.println(this.toString() + ": Am i going up or down ?");
                break;
            case "SNOW":
                c = Simulator.WeatherChangeHelicopter.SNOW;
                System.out.println(this.toString() + ": I'm a snow fan !");
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

