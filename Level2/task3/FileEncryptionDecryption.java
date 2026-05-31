import java.io.*;

public class FileEncryptionDecryption {

    public static String processFileText(String fileText, int shift, boolean encrypt) {
        StringBuilder result = new StringBuilder();

        for (char ch : fileText.toCharArray()) {
            if (encrypt) {
                result.append((char) (ch + shift));
            } else {
                result.append((char) (ch - shift));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("----- File Encryption/Decryption -----");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Enter the file path: ");
            String filePath = reader.readLine();

            System.out.print("Do you want to encrypt or decrypt? (e/d): ");
            String choice = reader.readLine();

            System.out.print("Enter the encryption/decryption shift value (integer): ");
            int shift = Integer.parseInt(reader.readLine());

            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File not found. Please check the path and try again.");
                return;
            }

            StringBuilder fileContent = new StringBuilder();

            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = fileReader.readLine()) != null) {
                    fileContent.append(line)
                               .append(System.lineSeparator());
                }
            }

            String processedContent;
            String outputFile;

            if (choice.equalsIgnoreCase("e")) {
                processedContent = processFileText(fileContent.toString(), shift, true);
                outputFile = "encrypted_" + file.getName();

            } else if (choice.equalsIgnoreCase("d")) {
                processedContent = processFileText(fileContent.toString(), shift, false);
                outputFile = "decrypted_" + file.getName();

            } else {
                System.out.println("Invalid choice. Enter 'e' for encryption or 'd' for decryption.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(processedContent);
            }

            System.out.println("Operation completed successfully.");
            System.out.println("Output file: " + outputFile);

        } catch (NumberFormatException e) {
            System.out.println("Invalid shift value. Please enter a valid integer.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}