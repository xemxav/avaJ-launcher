class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Flyable product = null;
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
        }
        return product;
    }
}
