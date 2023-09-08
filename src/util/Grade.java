package util;

public class Grade {
    private int score;
    private String letterGrade;
    //Initializes a new Grade object with the provided numerical score.
    //Calculates the initial letter grade based on the provided score.
    public Grade(int score) {
        this.score = score;
        this.letterGrade = calculateLetterGrade(score);
    }
    //Returns the numerical score associated with the grade.
    public int getScore() {
        return score;
    }
    //Returns the letter grade associated with the grade.
    public String getLetterGrade() {
        return letterGrade;
    }
    //Sets a new numerical score for the grade.
    //Recalculates the letter grade based on the new score using predefined grading criteria.
    public void setScore(int score) {
        this.score = score;
        if (score >= 95.00) {
            letterGrade = "A";
        } else if (score >= 90.00) {
            letterGrade = "A-";
        } else if (score >= 87.00) {
            letterGrade = "B+";
        } else if (score >= 83.00) {
            letterGrade = "B";
        } else if (score >= 80.00) {
            letterGrade = "B-";
        } else if (score >= 77.00) {
            letterGrade = "C+";
        } else if (score >= 70.00) {
            letterGrade = "C";
        } else if (score >= 60.00) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }
    }
    //Calculates and returns the letter grade based on the provided numerical score according to a predefined grading scale.
    private String calculateLetterGrade(int score) {
        if (score >= 95.00) {
            return "A";
        } else if (score >= 90.00) {
            return "A-";
        } else if (score >= 87.00) {
            return "B+";
        } else if (score >= 83.00) {
            return "B";
        } else if (score >= 80.00) {
            return "B-";
        } else if (score >= 77.00) {
            return "C+";
        } else if (score >= 70.00) {
            return "C";
        } else if (score >= 60.00) {
            return "D";
        } else {
            return "F";
        }
    }
}
