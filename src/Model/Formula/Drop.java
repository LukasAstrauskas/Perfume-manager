package Model.Formula;

public class Drop {
    private static final double mass = 0.02;
    private static final double density = 0.79;
    private static final String massUnits = "g";
    private static final String densUnits = "g/mL";

    public static double getMass() {
        return mass;
    }

    public static double getDensity() {
        return density;
    }

    public static String getMassUnits() {
        return massUnits;
    }

    public static String getDensUnits() {
        return densUnits;
    }
}
