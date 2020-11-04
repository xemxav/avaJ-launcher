package simulator.customexceptions;

public class IncorrectAircraftTypeValue extends IllegalArgumentException {
    public IncorrectAircraftTypeValue(String errorMessage) {
        super(errorMessage);
    }
}
