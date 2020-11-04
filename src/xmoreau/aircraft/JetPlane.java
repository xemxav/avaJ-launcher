package src.xmoreau.aircraft;

import src.xmoreau.weather.WeatherTower;
import src.xmoreau.customexceptions.IncorrectCoordinateValue;
import src.xmoreau.flyable.Flyable;
import src.xmoreau.simulator.Simulator;

class JetPlane extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Simulator.WeatherChangeJetPlane c = null;
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeJetPlane.SUN;
                Simulator.logger.println(this.toString() + ": To infinity and beyond !");
                break;
            case "RAIN":
                c = Simulator.WeatherChangeJetPlane.RAIN;
                Simulator.logger.println(this.toString() + ": Highway to the danger zone!");
                break;
            case "FOG":
                c = Simulator.WeatherChangeJetPlane.FOG;
                Simulator.logger.println(this.toString() + ": I have some goo on my windshield !");
                break;
            case "SNOW":
                c = Simulator.WeatherChangeJetPlane.SNOW;
                Simulator.logger.println(this.toString() + ": I'm ski patrol.");
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
        this.weatherTower.register(this);    }

    public String toString() {
        String str = super.toString();
        return "JetPlane" + str;
    }
}