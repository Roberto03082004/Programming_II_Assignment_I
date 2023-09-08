package util;

public class Student {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;
    //Initializes a new Student object with the provided first name, last name, PID (student ID), and grade.
    public Student(String firstName, String lastName, int pid, Grade grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pid = pid;
        this.grade = grade;
    }
    //Returns the first name of the student.
    public String getFirstName() {
        return firstName;
    }
    //Returns the last name of the student.
    public String getLastName() {
        return lastName;
    }
    //Returns the PID (student ID) of the student.
    public int getPid() {
        return pid;
    }
    //Returns the Grade object associated with the student, which contains the numerical score and letter grade.
    public Grade getGrade() {
        return grade;
    }
}
