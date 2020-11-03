public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() { }

    public static WeatherProvider getProvider() {
        if (weatherProvider== null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = (coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude() % 4);
        return weather[index];
    }
}
