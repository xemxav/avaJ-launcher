package simulator.customexceptions;

public class IncorrectCoordinateValue extends IllegalArgumentException {
    public IncorrectCoordinateValue(String errorMessage) {
        super(errorMessage);
    }
}
