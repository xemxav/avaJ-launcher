package src.xmoreau.aircraft;

import src.xmoreau.customexceptions.IncorrectAircraftTypeValue;
import src.xmoreau.customexceptions.IncorrectCoordinateValue;
import src.xmoreau.flyable.Flyable;
import src.xmoreau.simulator.Simulator;


public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IncorrectAircraftTypeValue {
        Flyable product = null;
        try {
            switch (type) {
                case "Baloon":
                    product = new Baloon(name, new Coordinates(longitude, latitude, height));
                    break;
                case "Helicopter":
                    product = new Helicopter(name, new Coordinates(longitude, latitude, height));
                    break;
                case "JetPlane":
                    product = new JetPlane(name, new Coordinates(longitude, latitude, height));
                    break;
                default:
                    throw new IncorrectAircraftTypeValue(type +": is not a proper Aicraft type");
            }
        } catch (IncorrectCoordinateValue e) {
            Simulator.logger.close();
            System.exit(Simulator.ErrorCode.EXCEPTIONHANDLING.ordinal());
        }
        return product;
    }
}
