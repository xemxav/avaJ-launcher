package src;

import src.tower.Tower;
import src.aircraft.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        return weatherProvider.getCurrentWeather(coordinates);
    }

    void changeWeather() {
        conditionsChanged();
    }
}
