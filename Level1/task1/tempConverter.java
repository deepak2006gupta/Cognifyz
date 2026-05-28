import java.util.Scanner;

class TempConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Temperature Convertor-----");
        System.out.print("Enter the temperature: ");
        double temp = scanner.nextDouble();
        System.out.print("Enter the unit (C for Celsius, F for Farenheit): ");
        char unit = scanner.next().charAt(0);

        if(unit == 'C' || unit == 'c') {
            double farenheit = (temp * 9/5) + 32;
            System.out.printf("%.2f °C is equal to %.2f °F \n", temp, farenheit);
        }else if( unit == 'F' || unit == 'f') {
            double celsius = (temp - 32) * 5/9;
            System.out.printf("%.2f °F is equal to %.2f °C \n", temp, celsius);
        } else {
            System.out.println("Invalid unit entered. Please enter 'C' for Celsius or 'F' for Farenheit.");
        }
        scanner.close();

    }
}