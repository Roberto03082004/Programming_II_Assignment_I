/* This is part of the starter code!
 * You need to complete this class yourself!*/
package util;


import java.util.ArrayList;
import java.util.Arrays;

public class Gradebook {
    private ArrayList<Student> listOfStudents;
    //Initializes a new Gradebook object with an empty list of students (listOfStudents).
    public Gradebook() {
        listOfStudents = new ArrayList<>();
    }
    //Returns the list of students in the grade book.
    public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }
    //Calculates and returns the minimum numerical score among all students.
    public int calculateMinScore() {
        if (listOfStudents.isEmpty()) {
            return 0; // Return 0 if there are no students
        }

        int minScore = listOfStudents.get(0).getGrade().getScore();
        for (Student student : listOfStudents) {
            int score = student.getGrade().getScore();
            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }
    //Calculates and returns the minimum letter grade among all students.
    public String calculateMinLetter() {
        if (listOfStudents.isEmpty()) {
            return "N/A"; // Return "N/A" if there are no students
        }
        String minLetter = listOfStudents.get(0).getGrade().getLetterGrade();
        for (Student student : listOfStudents) {
            String studentLetter = student.getGrade().getLetterGrade();
            if (studentLetter.compareTo(minLetter) > 0) {
                minLetter = studentLetter;
            }
        }
        return minLetter;
    }
    //Calculates and returns the maximum numerical score among all students.
    public int calculateMaxScore() {
        if (listOfStudents.isEmpty()) {
            return 0; // Return 0 if there are no students
        }

        int maxScore = listOfStudents.get(0).getGrade().getScore();
        for (Student student : listOfStudents) {
            int score = student.getGrade().getScore();
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }
    //Calculates and returns the maximum letter grade among all students.
    public String calculateMaxLetter() {
        if (listOfStudents.isEmpty()) {
            return "N/A"; // Return "N/A" if there are no students
        }

        String maxLetter = listOfStudents.get(0).getGrade().getLetterGrade();
        for (Student student : listOfStudents) {
            String studentLetter = student.getGrade().getLetterGrade();
            if (studentLetter.compareTo(maxLetter) < 0) {
                maxLetter = studentLetter;
            }
        }
        return maxLetter;
    }
    //Retrieves and prints the letter grade for a student by their PID (Student ID)
    public void letterID(String command) {
        String pidStr = command.substring(7).trim();
        try {
            int pid = Integer.parseInt(pidStr);
            String letterGrade = findLetterGradeByPid(pid);
            System.out.println("Letter grade for PID " + pid + ": " + letterGrade);
        } catch (NumberFormatException e) {
            System.out.println("Invalid PID format.");
        }
    }
    //Retrieves and prints the full name of a student by their PID.
    public void nameID(String command) {
        String pidStr = command.substring(5).trim();
        try {
            int pid = Integer.parseInt(pidStr);
            String fullName = findFullNameByPid(pid);
            System.out.println("Full name for PID " + pid + ": " + fullName);
        } catch (NumberFormatException e) {
            System.out.println("Invalid PID format.");
        }
    }
    //Changes the grade of a student by their PID to a new grade.
    public void changeGrade(String command) {
        String[] changeParts = command.split(" ");
        if (changeParts.length != 3) {
            System.out.println("Invalid 'change' command format. Please use: change XXXXXXX newGrade");
        } else {
            int pidToChange;
            int newGradeScore;

            try {
                pidToChange = Integer.parseInt(changeParts[1]);
                newGradeScore = Integer.parseInt(changeParts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid PID or grade. Please try again.");
                return;
            }

            if (newGradeScore < 0 || newGradeScore > 100) {
                System.out.println("Invalid grade. Grade must be between 0 and 100.");
                return;
            }

            updateGradeScoreByPid(pidToChange, newGradeScore);
        }
    }

    //Finds and returns the letter grade of a student by their PID.
    public String findLetterGradeByPid(int pid) {
        for (Student student : listOfStudents) {
            if (student.getPid() == pid) {
                return student.getGrade().getLetterGrade();
            }
        }
        return "Student not found";
    }
    //Finds and returns the full name of a student by their PID.
    public String findFullNameByPid(int pid) {
        for (Student student : listOfStudents) {
            if (student.getPid() == pid) {
                return student.getFirstName() + " " + student.getLastName();
            }
        }
        return "Student not found";
    }
    //Updates the grade score of a student by their PID to a new grade.
    public void updateGradeScoreByPid(int pid, int newGradeScore) {
        for (Student student : listOfStudents) {
            if (student.getPid() == pid) {
                student.getGrade().setScore(newGradeScore);
                System.out.println("Grade updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    //Calculates and returns the average numerical score of all students.
    public double calculateAvgScore() {
        if (listOfStudents.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Student student : listOfStudents) {
            sum += student.getGrade().getScore();
        }
        return sum / listOfStudents.size();
    }
    //Calculates and returns the average letter grade based on the average numerical score.
    public String calculateAvgLetter() {
        if (listOfStudents.isEmpty()) {
            return "N/A";
        }

        int avgScore = (int) calculateAvgScore();
        return new Grade(avgScore).getLetterGrade();
    }
    //Calculates and returns the median numerical score among all students.
    public float calculateMedianScore() {
        if (listOfStudents.isEmpty()) {
            return 0;
        }

        int[] scores = new int[listOfStudents.size()];
        for (int i = 0; i < listOfStudents.size(); i++) {
            scores[i] = listOfStudents.get(i).getGrade().getScore();
        }
        Arrays.sort(scores);

        int middleIndex = scores.length / 2;
        if (scores.length % 2 == 0) {
            int median1 = scores[middleIndex - 1];
            int median2 = scores[middleIndex];
            return (int) ((median1 + median2) / 2.0f);
        } else {
            return scores[middleIndex];
        }
    }
    //Calculates and returns the median letter grade based on the median numerical score.
    public String calculateMedianLetter() {
        float medianScore = calculateMedianScore();
        // Calculate the corresponding letter grade based on the median score
        if (medianScore >= 95.00) {
            return "A";
        } else if (medianScore >= 90.00) {
            return "A-";
        } else if (medianScore >= 87.00) {
            return "B+";
        } else if (medianScore >= 83.00) {
            return "B";
        } else if (medianScore >= 80.00) {
            return "B-";
        } else if (medianScore >= 77.00) {
            return "C+";
        } else if (medianScore >= 70.00) {
            return "C";
        } else if (medianScore >= 60.00) {
            return "D";
        } else {
            return "F";
        }
    }
    //Prints a tab-separated table of student scores.
    public void printTabSeparatedScores() {
        System.out.println("First Name\tLast Name\tPID\t\t Score");
        for (Student student : listOfStudents) {
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            int pid = student.getPid();
            int score = student.getGrade().getScore();

            System.out.printf("%-12s%-12s%-9d%d%n", firstName, lastName, pid, score);
        }
    }
    //Prints a tab-separated table of student letter grades.
    public void printTabSeparatedLetters() {
        System.out.println("First Name\tLast Name\tPID\t\t Letter Grade");
        for (Student student : listOfStudents) {
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            int pid = student.getPid();
            String letterGrade = student.getGrade().getLetterGrade();

            System.out.printf("%-12s%-12s%-9d%s%n", firstName, lastName, pid, letterGrade);
        }
    }
}