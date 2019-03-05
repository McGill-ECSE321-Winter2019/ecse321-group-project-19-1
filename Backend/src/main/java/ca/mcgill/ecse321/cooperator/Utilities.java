package ca.mcgill.ecse321.cooperator;

public class Utilities {
    public static Boolean checkNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }
}
