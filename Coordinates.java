class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude,int height) {

        if ((longitude < 0 || latitude < 0) || (height < 0 || height > 100)) {
            System.out.println("Coordinates are not valid");
//            throw Exception;
        }
        this.longitude = longitude;
        this.latitude = latitude;
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
