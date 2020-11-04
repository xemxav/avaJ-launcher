package simulator;

import simulator.aircraft.Coordinates;
import simulator.weatherprovider.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        return weatherProvider.getCurrentWeather(coordinates);
    }

    void changeWeather() {
        this.conditionsChanged();
    }
}