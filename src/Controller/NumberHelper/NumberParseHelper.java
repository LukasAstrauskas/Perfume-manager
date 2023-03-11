package Controller.NumberHelper;

public class NumberParseHelper {
    public double parseNumber(String string) {
        String replace = string.replace(",", ".");
        double number;
        try {
            number = Double.parseDouble(replace);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            number = 0;
        }

        return number;
    }
}
