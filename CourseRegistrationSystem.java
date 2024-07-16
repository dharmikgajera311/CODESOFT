import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + (capacity - enrolledStudents));
    }
}

class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Course is full, registration failed.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.getTitle());
            }
        }
    }
}

public class CourseRegistrationSystem {
    private static HashMap<String, Course> courseDatabase = new HashMap<>();
    private static HashMap<String, Student> studentDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        while (true) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    listAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courseDatabase.put("CSE101", new Course("CSE101", "Introduction to Computer Science", "Basics of computer science", 30, "MWF 10-11am"));
        courseDatabase.put("MTH101", new Course("MTH101", "Calculus I", "Introduction to Calculus", 25, "TTh 9-10:30am"));
        courseDatabase.put("PHY101", new Course("PHY101", "Physics I", "Fundamentals of Physics", 20, "MWF 1-2pm"));
    }

    private static void listAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            course.displayCourseDetails();
            System.out.println();
        }
    }

    private static void registerForCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.computeIfAbsent(studentID, id -> {
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            return new Student(id, name);
        });

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);

        if (course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student != null) {
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            Course course = courseDatabase.get(courseCode);

            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void viewRegisteredCourses() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }
}
