import java.util.Scanner;

public class PasswordAnalysis {
    public static void main (String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("-----Password Analyser-----");
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    
        int length = password.length();
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for(char ch : password.toCharArray()) { 
            if(Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if(Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if(Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }

        int strengthScore = 0;
        if(length >= 6) strengthScore++;
        if(length >= 10) strengthScore++;
        if(hasUpperCase) strengthScore++;
        if(hasLowerCase) strengthScore++;
        if(hasDigit) strengthScore++;
        if(hasSpecialChar) strengthScore++;

        System.out.println("\nPassword Analysis:");
        System.out.println("Length: " + length);
        System.out.println("Has Upper Case: " + hasUpperCase);
        System.out.println("Has Lower Case: " + hasLowerCase);
        System.out.println("Has Digit: " + hasDigit);
        System.out.println("Has Special Character: " + hasSpecialChar);
        System.out.println("Strength Score: " + strengthScore);

        System.out.print("\nPassword Strength: ");
        if(strengthScore <= 2) {
            System.out.println("Weak");
        } else if(strengthScore <= 4) {
            System.out.println("Moderate");
        } else {
            System.out.println("Strong");
        }
        System.out.println("Thank you for using the Password Analyser!\n");

        scanner.close();

    }
}
