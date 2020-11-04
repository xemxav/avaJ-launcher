package src.xmoreau.weather;

import src.xmoreau.aircraft.Coordinates;

public class WeatherProvider {

    private static WeatherProvider weatherProvider;

    private WeatherProvider() { }

    public static WeatherProvider getProvider() {
        if (weatherProvider== null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

        int index = (coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % 4;
        return weather[index];
    }
}
