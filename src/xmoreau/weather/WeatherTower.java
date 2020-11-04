package src.xmoreau.weather;

import src.xmoreau.tower.Tower;
import src.xmoreau.aircraft.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        return weatherProvider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionsChanged();
    }
}
