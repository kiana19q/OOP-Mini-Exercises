import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private String studentId;
    private String name;
    private ArrayList<Double> grades;
    private String department;
    
    public Student(String studentId, String name, String department) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty!");
        }
        
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.grades = new ArrayList<>(); // Initialize empty grades list
    }
    
    public String addGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            return "Error: Grade must be between 0.0 and 100.0!";
        }
        
        grades.add(grade);
        return "Grade " + grade + " added successfully!";
    }
    
    public String removeGrade(int index) {
        if (grades.isEmpty()) {
            return "Error: No grades to remove!";
        }
        
        if (index < 0 || index >= grades.size()) {
            return "Error: Invalid index! Available range: 0 to " + (grades.size() - 1);
        }
        
        double removedGrade = grades.remove(index);
        return "Grade " + removedGrade + " at index " + index + " removed successfully!";
    }
    
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        
        return sum / grades.size();
    }
    
    public double getHighestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        return Collections.max(grades);
    }
    
    public double getLowestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        return Collections.min(grades);
    }
    
    public int getGradeCount() {
        return grades.size();
    }
    
    public String getLetterGrade() {
        if (grades.isEmpty()) {
            return "No grades available";
        }
        
        double average = calculateAverage();
        
        if (average >= 90.0) return "A";
        else if (average >= 80.0) return "B";
        else if (average >= 70.0) return "C";
        else if (average >= 60.0) return "D";
        else return "F";
    }
    
    public String getStudentInfo() {
        StringBuilder info = new StringBuilder();
        info.append("STUDENT INFORMATION\n");
        info.append("ID: ").append(studentId).append("\n");
        info.append("Name: ").append(name).append("\n");
        info.append("Department: ").append(department).append("\n");
        info.append("Grade Count: ").append(getGradeCount()).append("\n");
        
        if (!grades.isEmpty()) {
            info.append("Grades: ").append(grades).append("\n");
            info.append("Average: ").append(String.format("%.2f", calculateAverage())).append("\n");
            info.append("Highest: ").append(getHighestGrade()).append("\n");
            info.append("Lowest: ").append(getLowestGrade()).append("\n");
            info.append("Letter Grade: ").append(getLetterGrade()).append("\n");
        } else {
            info.append("No grades available\n");
        }
        
        return info.toString();
    }
    
    //Getter
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public ArrayList<Double> getGrades() { return new ArrayList<>(grades); }
    
    //Setter 
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    
    public static void main(String[] args) {
        System.out.println("STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("===================================\n");
        
        // ایجاد دانشجویان
        Student student1 = new Student("STU001", "Ali Mohammadi", "Computer Science");
        Student student2 = new Student("STU002", "Sara Ahmadi", "Electrical Engineering");
        
        System.out.println("ADDING GRADES TO STUDENT 1:");
        System.out.println(student1.addGrade(85.5));
        System.out.println(student1.addGrade(92.0));
        System.out.println(student1.addGrade(78.5));
        System.out.println(student1.addGrade(88.0));
        System.out.println(student1.addGrade(105.0)); // نمره نامعتبر
        System.out.println(student1.addGrade(-5.0));  // نمره نامعتبر
        
        System.out.println("\nADDING GRADES TO STUDENT 2:");
        System.out.println(student2.addGrade(95.0));
        System.out.println(student2.addGrade(87.5));
        System.out.println(student2.addGrade(76.0));
        
        System.out.println("\nSTUDENT 1 INFORMATION:");
        System.out.println(student1.getStudentInfo());
        
        System.out.println("STUDENT 2 INFORMATION:");
        System.out.println(student2.getStudentInfo());
        
        System.out.println("DETAILED STATISTICS:");
        System.out.println("Student 1 - Average: " + student1.calculateAverage() + 
                          ", Highest: " + student1.getHighestGrade() + 
                          ", Lowest: " + student1.getLowestGrade() +
                          ", Letter: " + student1.getLetterGrade());
        
        System.out.println("Student 2 - Average: " + student2.calculateAverage() + 
                          ", Highest: " + student2.getHighestGrade() + 
                          ", Lowest: " + student2.getLowestGrade() +
                          ", Letter: " + student2.getLetterGrade());
        
        System.out.println("\nREMOVING A GRADE:");
        System.out.println(student1.removeGrade(1)); // حذف نمره دوم
        
        System.out.println("\n🔄 UPDATED STUDENT 1 INFORMATION:");
        System.out.println(student1.getStudentInfo());
        
        System.out.println("TESTING EDGE CASES:");
        Student emptyStudent = new Student("STU003", "Empty Student", "Mathematics");
        System.out.println(emptyStudent.getStudentInfo());
        System.out.println("Remove from empty: " + emptyStudent.removeGrade(0));
    }
}