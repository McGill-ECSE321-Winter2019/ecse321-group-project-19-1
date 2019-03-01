package ca.mcgill.ecse321.cooperator;

public class Utilities {
    public static Boolean CheckNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }
}
