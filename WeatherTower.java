public class WeatherTower extends Tower {

    //getweather
    public String getWeather(Coordinates coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        return weatherProvider.getCurrentWeather(coordinates);
    }

    //set weather
    private void changeWeather() {
        this.conditionsChanged();
    }
}