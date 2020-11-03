class IncorrectAircraftTypeValue extends IllegalArgumentException {
    IncorrectAircraftTypeValue(String errorMessage) {
        super(errorMessage);
    }
}

class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
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
            System.exit(-2);
        }
        return product;
    }
}
