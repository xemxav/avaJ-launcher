public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        return weatherProvider.getCurrentWeather(coordinates);
    }

    private void changeWeather() {
        this.conditionsChanged();
    }
}