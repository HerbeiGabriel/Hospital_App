package Domain;

public class Errors {
    public static boolean isNotNumber(String department) {
        try {
            Integer.parseInt(department);
            return true;  // It's a number
        } catch (NumberFormatException e) {
            return false;   // Not a number
        }
    }

}
