package main;

import util.*;

import java.util.Scanner;

public class Main {
    //The main entry point of your program.
    //Manages the overall flow of the grade book management system.
    //Handles input from the user, including student information and commands.
    //Uses various methods to perform operations on the grade book data.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gradebook gradebook = new Gradebook(); // Create an instance of Gradebook

        // Input Handling Phase
        System.out.println("Welcome to my grade book!");
        System.out.println("Please enter the information of the first student using the following format:");
        System.out.println("\"First Name - Last Name - PID - Grade\".");
        System.out.println("Press Enter when you are done.");

        //Takes user input for student information (first name, last name, PID, and grade).
        //Validates and stores the input in the grade book.
        while (true) {

            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 1 && parts[0].equalsIgnoreCase("DONE")) {
                break;
            } else if (parts.length != 4) {
                System.out.println("Invalid input format. Please try again.");
                continue;
            }

            String firstName = parts[0];
            String lastName = parts[1];
            int pid;
            int gradeScore;

            // Validate firstName
            if (!isValidName(firstName)) {
                System.out.println("Invalid first name format. Please try again.");
                continue;
            }

            // Validate lastName
            if (!isValidName(lastName)) {
                System.out.println("Invalid last name format. Please try again.");
                continue;
            }

            // Validate PID
            try {
                pid = Integer.parseInt(parts[2]);
                if (!isValidPid(parts[2])) {
                    System.out.println("Invalid PID format. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid PID. Please enter a valid 7-digit integer.");
                continue;
            }
            // Validate grade
            try {
                gradeScore = Integer.parseInt(parts[3]);
                if (!isValidGrade(gradeScore)) {
                    System.out.println("Invalid grade. Please enter a non-negative integer that doesn't exceed 100.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade. Please enter a valid numeric grade.");
                continue;
            }


            Grade grade = new Grade(gradeScore);
            Student student = new Student(firstName, lastName, pid, grade);
            gradebook.getListOfStudents().add(student);

            System.out.println("Please enter the information of the next student using the same format.");
            System.out.println("If there are no more students, please enter the keyword “DONE”.");
            System.out.println("Press Enter when you are done.");
        }

        // Command Handling Phase
        displayCommands();//Calls displayCommands method to print out list of available commands


        //Takes user commands to perform various operations on the grade book data.
        //Executes the corresponding methods to calculate and display information as requested.
        while (true) {
            System.out.println("Please enter a new command:");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("min score")) {
                // Calculate and print the minimum score
                int minScore = gradebook.calculateMinScore();
                System.out.println("Minimum score: " + minScore);
            } else if (command.equalsIgnoreCase("min letter")) {
                // Calculate and print the minimum letter grade
                String minLetter = gradebook.calculateMinLetter();
                System.out.println("Minimum letter grade: " + minLetter);
            } else if (command.equalsIgnoreCase("max score")) {
                // Calculate and print the maximum score
                int maxScore = gradebook.calculateMaxScore();
                System.out.println("Maximum score: " + maxScore);
            } else if (command.equalsIgnoreCase("max letter")) {
                // Calculate and print the maximum letter grade
                String maxLetter = gradebook.calculateMaxLetter();
                System.out.println("Maximum letter grade: " + maxLetter);
            } else if (command.toLowerCase().startsWith("letter")) {
                gradebook.letterID(command);
            } else if (command.toLowerCase().startsWith("name")) {
                gradebook.nameID(command);
            } else if (command.toLowerCase().startsWith("change")) {
                gradebook.changeGrade(command);
            } else if (command.equalsIgnoreCase("average score")) {
                // Calculate and print the average score
                double avgScore = gradebook.calculateAvgScore();
                System.out.println("Average score: " + avgScore);
            } else if (command.equalsIgnoreCase("average letter")) {
                // Calculate and print the average letter grade
                String avgLetter = gradebook.calculateAvgLetter();
                System.out.println("Average letter grade: " + avgLetter);
            } else if (command.equalsIgnoreCase("median score")) {
                // Calculate and print the median score
                float medianScore = gradebook.calculateMedianScore();
                System.out.println("Median score: " + medianScore);
            } else if (command.equalsIgnoreCase("median letter")) {
                // Calculate and print the median letter grade
                String medianLetter = gradebook.calculateMedianLetter();
                System.out.println("Median letter grade: " + medianLetter);
            } else if (command.equalsIgnoreCase("tab scores")) {
                // Print tab-separated table of scores
                gradebook.printTabSeparatedScores();
            } else if (command.equalsIgnoreCase("tab letters")) {
                // Print tab-separated table of letter grades
                gradebook.printTabSeparatedLetters();
            } else if (command.equalsIgnoreCase("help")) {
                // Display the list of available commands
                displayCommands();
            } else if (command.equalsIgnoreCase("quit")) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }
    //Checks if a given name (first name or last name) consists of alphabetical characters.
    //Used for input validation.
    private static boolean isValidName(String name) {
        return name.matches("[A-Z][a-z]*");
    }
    //Checks if a given PID (Student ID) consists of only numerical characters and is 7 digits long.
    //Used for input validation.
    private static boolean isValidPid(String pid) {
        String pidStr = String.valueOf(pid);
        return pidStr.matches("[1-9]\\d{6}"); // Ensures no leading zeros and is a 7-digit integer
    }
    //Checks if a given grade is between 0 and 100.
    //Used for input validation.
    private static boolean isValidGrade(int grade) {
        return grade >= 0 && grade <= 100;
    }
    //Prints a list of available commands for the user.
    //Helps users understand which commands are available in the program.
    public static void displayCommands(){
        System.out.println("==================================================");
        System.out.println("Available Commands:");
        System.out.println("==================================================");
        System.out.printf("%-20s%s%n", "- min score", "- Calculate and print the minimum score");
        System.out.printf("%-20s%s%n", "- min letter", "- Calculate and print the minimum letter-grade");
        System.out.printf("%-20s%s%n", "- max score", "- Calculate and print the maximum score");
        System.out.printf("%-20s%s%n", "- max letter", "- Calculate and print the maximum letter-grade");
        System.out.printf("%-20s%s%n", "- id letter", "- Find and print the letter-grade by PID");
        System.out.printf("%-20s%s%n", "- id name", "- Find and print the full name by PID");
        System.out.printf("%-20s%s%n", "- id change", "- Change the grade of a student by PID");
        System.out.printf("%-20s%s%n", "- average score", "- Calculate and print the average score");
        System.out.printf("%-20s%s%n", "- average letter", "- Calculate and print the average letter-grade");
        System.out.printf("%-20s%s%n", "- median score", "- Calculate and print the median score");
        System.out.printf("%-20s%s%n", "- median letter", "- Calculate and print the median letter-grade");
        System.out.printf("%-20s%s%n", "- tab scores", "- Print a tab-separated table of scores");
        System.out.printf("%-20s%s%n", "- tab letters", "- Print a tab-separated table of letter-grades");
        System.out.printf("%-20s%s%n", "- help", "- Display available commands");
        System.out.printf("%-20s%s%n", "- quit", "- Exit the program");
    }
}
