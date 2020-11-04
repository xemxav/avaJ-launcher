package simulator;

import simulator.aircraft.AircraftFactory;
import simulator.customexceptions.IncorrectAircraftTypeValue;
import simulator.flyable.Flyable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {

    public static WeatherTower weatherTower = new WeatherTower();

    public enum WeatherChangeBaloon {
        SUN(new int[]{2, 0, 4}),
        RAIN(new int[]{0, 0, -5}),
        FOG(new int[]{0, 0, -3}),
        SNOW(new int[]{0, 0, -15});

        public final int[] changes;
        WeatherChangeBaloon(int[] changes) {
            this.changes = changes;
        }

    }

    public enum WeatherChangeJetPlane {
        SUN(new int[]{0, 10, 2}),
        RAIN(new int[]{0, 5, 0}),
        FOG(new int[]{0, 1, 0}),
        SNOW(new int[]{0, 0, -7});

        public final int[] changes;
        WeatherChangeJetPlane(int[] changes) {
            this.changes = changes;
        }

    }

    public enum WeatherChangeHelicopter {
        SUN(new int[]{10, 0, 2}),
        RAIN(new int[]{5, 0, 0}),
        FOG(new int[]{1, 0, 0}),
        SNOW(new int[]{0, 0, -12});

        public final int[] changes;
        WeatherChangeHelicopter(int[] changes) {
            this.changes = changes;
        }

    }

    public enum ErrorCode {
        MISSINGFILE,
        PARSEERROR,
        EXCEPTIONHANDLING
    }


    public static void main (String[] argv) {

        int simulations = 0;
        if (argv.length != 1) {
            System.exit(ErrorCode.MISSINGFILE.ordinal());
        }
        File scenario = new File(argv[0]);
        boolean first = true;
        try (Scanner scanner = new Scanner(scenario)) {
            while (scanner.hasNextLine()) {
                if (first) {
                    simulations = checkFirstLine(scanner.nextLine());
                    if (simulations < 0) {
                        System.exit(ErrorCode.PARSEERROR.ordinal());
                    }
                    first = false;
                } else {
                    callFactory(scanner.nextLine().split(" "));
                }
            }
        } catch(FileNotFoundException e){
            System.exit(ErrorCode.MISSINGFILE.ordinal());
        }
        for (int simulation = 0; simulation < simulations ; simulation++) {
            weatherTower.changeWeather();
        }
    }

    private static int checkFirstLine(String line) {
        int ret = -1;
        try {
            ret = Integer.parseInt(line);
        } catch (Exception e) {
            System.exit(ErrorCode.PARSEERROR.ordinal());
        }
        return ret;
    }

    static void callFactory(String[] line) {
        if (line.length != 5) {
            System.exit(ErrorCode.PARSEERROR.ordinal());
        }
        int[] coordinates = new int[3];
        try {
            coordinates[0] = Integer.parseInt(line[2]);
            coordinates[1] = Integer.parseInt(line[3]);
            coordinates[2] = Integer.parseInt(line[4]);
        } catch (Exception e) {
            System.exit(ErrorCode.PARSEERROR.ordinal());
        }
        Flyable newAircraft = null;
        try{
            newAircraft= AircraftFactory.newAircraft(line[0], line[1], coordinates[0], coordinates[1], coordinates[2]);
        } catch (IncorrectAircraftTypeValue e) {
            System.exit(ErrorCode.PARSEERROR.ordinal());
        }
        newAircraft.registerTower(weatherTower);
    }
}
