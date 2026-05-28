import java.util.Random;
import java.util.Scanner;
class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String numbers = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&*()-_=+[]{};:,.<>?";

        System.out.println("-----Password Generator-----");
        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();

        if (length <= 0) {
            System.out.println("Password length must be greater than 0.");
            scanner.close();
            return;
        }

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecialCharacters = scanner.next().equalsIgnoreCase("yes");

        String characters = "";

        if (includeNumbers) {
            characters += numbers;
        }

        if (includeLowercase) {
            characters += lowercase;
        }

        if (includeUppercase) {
            characters += uppercase;
        }

        if (includeSpecialCharacters) {
            characters += specialCharacters;
        }

        if (characters.isEmpty()) {
            System.out.println("Please select at least one character type.");
            scanner.close();
            return;
        }

        String password = "";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password += characters.charAt(index);
        }

        System.out.println("Generated password: " + password);

        scanner.close();
    }
}
