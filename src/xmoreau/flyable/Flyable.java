package src.xmoreau.flyable;

import src.xmoreau.weather.WeatherTower;

public interface Flyable {
    void updateConditions() ;

    void registerTower(WeatherTower weatherTower) ;
}
