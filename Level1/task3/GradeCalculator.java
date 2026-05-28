package Level1.task3;

import java.util.Scanner;

public class GradeCalculator{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Grade Calculator-----");

        System.out.print("Enter the number of grades: ");
        int numGrades = scanner.nextInt();

        if (numGrades <= 0) {
            System.out.print("Number of grades must be greater than 0.");
            scanner.close();
            return;
        }

        double[] grades = new double[numGrades];

        double sum = 0;

        for(int i=0;i<numGrades;i++){
            System.out.print("Enter the Grade for subject "+(i+1)+": ");
            grades[i] = scanner.nextDouble();
            sum += grades[i];
        }

        double avg = sum / numGrades;

        System.out.printf("Average Grade: %.2f%n", avg);

        scanner.close();
    }
}
