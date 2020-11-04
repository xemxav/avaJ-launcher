class JetPlane extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Simulator.WeatherChangeJetPlane c = null;
        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                c = Simulator.WeatherChangeJetPlane.SUN;
                System.out.println(this.toString() + ": To infinity and beyond !");
                break;
            case "RAIN":
                c = Simulator.WeatherChangeJetPlane.RAIN;
                System.out.println(this.toString() + ": Highway to the danger zone!");
                break;
            case "FOG":
                c = Simulator.WeatherChangeJetPlane.FOG;
                System.out.println(this.toString() + ": I have some goo on my windshield !");
                break;
            case "SNOW":
                c = Simulator.WeatherChangeJetPlane.SNOW;
                System.out.println(this.toString() + ": I'm ski patrol.");
                break;
        }
        try {
            this.coordinates = new Coordinates(coordinates.getLongitude() + c.changes[0],
                    coordinates.getLatitude() + c.changes[1],
                    coordinates.getHeight() + c.changes[2]);
        } catch (IncorrectCoordinateValue e) {
            weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);    }

    public String toString() {
        String str = super.toString();
        return "JetPlane" + str;
    }
}