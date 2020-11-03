class IncorrectCoordinateValue extends IllegalArgumentException {
    IncorrectCoordinateValue(String errorMessage) {
        super(errorMessage);
    }
}

class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude,int height) throws IncorrectCoordinateValue {
        if (longitude <= 0 || latitude <= 0 || height <= 0) {
            throw new IncorrectCoordinateValue("The coordinate value are not good");
        }
        this.longitude = longitude;
        this.latitude = latitude;
        if (height >= 100) {
            height = 100;
        }
        this.height = height;
    }

    public int getLongitude() { return longitude; }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

}
