package src.xmoreau.customexceptions;

public class IncorrectCoordinateValue extends IllegalArgumentException {
    public IncorrectCoordinateValue(String errorMessage) {
        super(errorMessage);
    }
}
