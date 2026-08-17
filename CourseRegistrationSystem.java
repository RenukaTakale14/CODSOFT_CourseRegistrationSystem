import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private final Scanner scanner;

    private final List<Course> courses;
    private final Student student;

    public CourseRegistrationSystem() {

        scanner = new Scanner(System.in);

        courses = new ArrayList<>();

        student = new Student(
                "STU101",
                "Student"
        );

        loadCourses();
    }

    private void loadCourses() {

        courses.add(new Course(
                "JAVA101",
                "Java Programming",
                "Core Java and Object-Oriented Programming",
                5,
                "Monday & Wednesday - 10:00 AM"
        ));

        courses.add(new Course(
                "DB101",
                "Database Management",
                "Introduction to databases and SQL",
                4,
                "Tuesday & Thursday - 11:00 AM"
        ));

        courses.add(new Course(
                "WEB101",
                "Web Development",
                "HTML, CSS and JavaScript fundamentals",
                6,
                "Friday - 2:00 PM"
        ));

        courses.add(new Course(
                "PY101",
                "Python Programming",
                "Python programming and problem solving",
                5,
                "Saturday - 10:00 AM"
        ));
    }

    public void start() {

        boolean running = true;

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       STUDENT COURSE REGISTRATION SYSTEM");
        System.out.println("==============================================");

        System.out.println(
                "Student ID: " + student.getStudentId()
        );

        System.out.println(
                "Student Name: " + student.getName()
        );

        while (running) {

            displayMenu();

            int choice = readChoice();

            switch (choice) {

                case 1:
                    displayCourses();
                    break;

                case 2:
                    registerCourse();
                    break;

                case 3:
                    removeCourse();
                    break;

                case 4:
                    student.displayRegisteredCourses();
                    break;

                case 5:
                    running = false;
                    System.out.println(
                            "\nThank you for using the system."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid option."
                    );
            }
        }

        scanner.close();
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Remove a Course");
        System.out.println("4. View My Courses");
        System.out.println("5. Exit");
        System.out.println("----------------------------------------------");
    }

    private int readChoice() {

        while (true) {

            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= 5) {
                    return choice;
                }

            } else {

                scanner.next();
            }

            System.out.println(
                    "Please enter a number between 1 and 5."
            );
        }
    }

    private void displayCourses() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              AVAILABLE COURSES");
        System.out.println("==============================================");

        for (Course course : courses) {

            course.displayCourse();
        }
    }

    private void registerCourse() {

        displayCourses();

        System.out.print(
                "\nEnter course code to register: "
        );

        String courseCode =
                scanner.next().trim();

        Course course =
                findCourse(courseCode);

        if (course == null) {

            System.out.println(
                    "Course not found."
            );

            return;
        }

        if (student.isRegistered(course)) {

            System.out.println(
                    "You are already registered for this course."
            );

            return;
        }

        if (!course.hasAvailableSlot()) {

            System.out.println(
                    "Sorry, this course is full."
            );

            return;
        }

        if (student.registerCourse(course)) {

            System.out.println(
                    "Course registered successfully!"
            );
        }
    }

    private void removeCourse() {

        student.displayRegisteredCourses();

        System.out.print(
                "\nEnter course code to remove: "
        );

        String courseCode =
                scanner.next().trim();

        Course course =
                findCourse(courseCode);

        if (course == null) {

            System.out.println(
                    "Course not found."
            );

            return;
        }

        if (student.removeCourse(course)) {

            System.out.println(
                    "Course removed successfully."
            );

        } else {

            System.out.println(
                    "You are not registered for this course."
            );
        }
    }

    private Course findCourse(String courseCode) {

        for (Course course : courses) {

            if (course.getCourseCode()
                    .equalsIgnoreCase(courseCode)) {

                return course;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        CourseRegistrationSystem system =
                new CourseRegistrationSystem();

        system.start();
    }
}