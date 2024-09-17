// Jenna Tran | Dolphin Database | Advanced Comp Sci | 9-17-24

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Dolphin Database!");

        int numberOfDolphins = 0;

        while (true) {
            System.out.println("Enter the number of dolphins you own: ");
            try {
                numberOfDolphins = Integer.parseInt(scanner.nextLine());
                if (numberOfDolphins <= 0) {
                    System.out.println("The number of dolphins must be greater than zero. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                // NumberFormatException is an exception that tells when there's an attempt to
                // convert a string into a numeric type, but the string doesn't have the
                // appropriate format for conversion
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        String[] dolphins = new String[numberOfDolphins];

        for (int i = 0; i < numberOfDolphins; i++) {
            System.out.print("Enter name for dolphin " + (i + 1) + ": ");
            dolphins[i] = scanner.nextLine();
        }

        scanner.close();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dolphinData.txt"));
            writer.write("\nI hope this works :)");
            writer.write("\nYour Dolphins: ");

            for (String dolphin : dolphins) {
                writer.write("\n" + dolphin);
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file.");
            // printStackTrace is a class that prints details like the line # and class
            // name(?) where the error/ exception occurred
            e.printStackTrace();
        }

        // Reads file + print to console
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dolphinData.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }
}
