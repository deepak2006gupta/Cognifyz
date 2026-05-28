import java.util.Scanner;

class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Grade Calculator-----");
        System.out.print("Enter the number of subjects: ");
        int subjects = scanner.nextInt();
        if(subjects <=0) {
            System.out.println("Number of subjects must be greater than zero.");
            scanner.close();
            return;
        }

        double totalMarks = 0;
        double[] marks = new double[subjects];

        for(int i=0;i<subjects;i++) {
            System.out.print("Enter marks for subject " + (i+1) + ": ");
            marks[i] = scanner.nextDouble();
            totalMarks += marks[i];
        }

        double average = totalMarks / subjects;

        System.out.printf("Average marks: %.2f\n", average);

        scanner.close();
        
    }
}