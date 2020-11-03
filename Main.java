import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.CheckedInputStream;

public class Main {

    public static void main (String[] argv) throws FileNotFoundException {

        int changeNb = 0;
        if (argv.length != 1) {
            System.out.println("You must provide a scenario file");
            return;
        }
        File scenario = new File(argv[0]);
        AircraftFactory factory = new AircraftFactory();
        boolean first = true;
        try (Scanner scanner = new Scanner(scenario)) {
            while (scanner.hasNextLine()) {
                if (first) {
                    changeNb = checkFirstLine(scanner.nextLine());
                    if (changeNb < 0) {
                        return; // print error
                    }
                    first = false;
                }
                if (!callFactory(scanner.nextLine().split(" "))){
                    System.exit(2);
                }
            }
        } catch(FileNotFoundException e){
                System.out.println("No file found: " + argv[0]);
            }
    }

    private static int checkFirstLine(String line) {
        try (int ret = Integer.parseInt(line)){
            return ret;
        } catch (Exception e) {
            System.out.println("First line is not good");
        }
        return (0);
    }

    static boolean callFactory(String[] line, AircraftFactory factory) {
        if (line.length != 5)
            return false;
        String[] airplaneNames = {"Baloon", "JetPlane", "Helicopter"};
        boolean validAirplaneName = false;
        boolean validName = false;
        boolean validCoordinates = false;
        int[] coordinates = new int[3];
        for (int i = 0; i < 3; i++){
            if (airplaneNames[i].equals(line[0])) {
                validAirplaneName = true;
            }
        }
        try {
            coordinates[0] = Integer.parseInt(line[2]);
            coordinates[1] = Integer.parseInt(line[3]);
            coordinates[2] = Integer.parseInt(line[4]);
        } catch (Exception e) {
            System.out.println("Scenario file is not formated correctly");
            System.exit(2);
        }
        validCoordinates = (coordinates[0]> 0 && coordinates[1] > 0 && coordinates[2] > 0 && coordinates[2] <= 100);
        if (validAirplaneName && validName && validCoordinates) {
            factory.
            return true;
        } else {
            return false;
        }
    }
}
