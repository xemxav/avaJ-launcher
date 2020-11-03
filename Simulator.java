import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Simulator {

    final static ArrayList<String> aircraftType = new ArrayList<String>(Arrays.asList("Baloon", "JetPlane", "Helicopter"));
    final static WeatherTower weatherTower = new WeatherTower();

    public enum WeatherChangeBaloon {
        SUN(new int[]{2, 0, 4}),
        RAIN(new int[]{0, 0, -5}),
        FOG(new int[]{0, 0, -3}),
        SNOW(new int[]{0, 0, -15});

        final int[] changes;
        WeatherChangeBaloon(int[] changes) {
            this.changes = changes;
        }

    }

    public enum WeatherChangeJetPlane {
        SUN(new int[]{0, 10, 2}),
        RAIN(new int[]{0, 5, 0}),
        FOG(new int[]{0, 1, 0}),
        SNOW(new int[]{0, 0, -7});

        final int[] changes;
        WeatherChangeJetPlane(int[] changes) {
            this.changes = changes;
        }

    }

    public enum WeatherChangeHelicopter {
        SUN(new int[]{10, 0, 2}),
        RAIN(new int[]{5, 0, 0}),
        FOG(new int[]{1, 0, 0}),
        SNOW(new int[]{0, 0, -12});

        final int[] changes;
        WeatherChangeHelicopter(int[] changes) {
            this.changes = changes;
        }

    }

    //todo : delete error message, use exception to control flow
    private static void parsingError(String message) {
        System.out.println(message);
        System.out.println("Exiting");
        System.exit(2);
    }

    public static void main (String[] argv) throws FileNotFoundException {

        int simulations = 0;
        if (argv.length != 1) {
            parsingError("You must provide a scenario file");
        }
        File scenario = new File(argv[0]);
        boolean first = true;
        try (Scanner scanner = new Scanner(scenario)) {
            int i = 1;
            while (scanner.hasNextLine()) {
                if (first) {
                    simulations = checkFirstLine(scanner.nextLine());
                    if (simulations < 0) {
                        parsingError(String.format("The first line of %s is not correct", scenario));
                    }
                    first = false;
                } else {
                    String line = scanner.nextLine();
                    String ret = callFactory(line.split(" "));
                    if (ret.length() > 0) {
                        parsingError(String.format("Error on line %d with : %s \"%s\"", i, ret, line));
                    }
                }
                i++;
            }
        } catch(FileNotFoundException e){
                System.out.println("No file found: " + argv[0]);
        }
        for (int simulation = 0; simulation <= simulations ; simulation++) {
            weatherTower.conditionsChanged();
        }
    }

    private static int checkFirstLine(String line) {
        int ret = -1;
        try {
            ret = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("The first line doesn't contain a number");
            System.exit(2);
        }
        return ret;
    }

    static String callFactory(String[] line) {
        String ret = new String();
        if (line.length != 5) {
            ret += "number of argument";
            return ret;
        }
        if (!aircraftType.contains(line[0])) {
            ret = String.format("%s is not a valid Aircraft type", line[0]);
        }
        int[] coordinates = new int[3];
        try {
            coordinates[0] = Integer.parseInt(line[2]);
            coordinates[1] = Integer.parseInt(line[3]);
            coordinates[2] = Integer.parseInt(line[4]);
        } catch (Exception e) {
            parsingError("Scenario file is not formated correctly");
        }
        Flyable newAircraft = null;
        try{
            newAircraft= AircraftFactory.newAircraft(line[0], line[1], coordinates[0], coordinates[1], coordinates[2]);
        } catch (IncorrectAircraftTypeValue e) {
            System.exit(2);
        }
        newAircraft.registerTower(weatherTower);
        return ret;
    }
}
