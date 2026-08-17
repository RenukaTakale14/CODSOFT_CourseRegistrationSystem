import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String studentId;
    private final String name;

    private final List<Course> registeredCourses;

    public Student(String studentId, String name) {

        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public boolean isRegistered(Course course) {

        return registeredCourses.contains(course);
    }

    public boolean registerCourse(Course course) {

        if (isRegistered(course)) {
            return false;
        }

        if (!course.hasAvailableSlot()) {
            return false;
        }

        course.registerStudent();
        registeredCourses.add(course);

        return true;
    }

    public boolean removeCourse(Course course) {

        if (!registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.remove(course);
        course.removeStudent();

        return true;
    }

    public void displayRegisteredCourses() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("             REGISTERED COURSES");
        System.out.println("==============================================");

        if (registeredCourses.isEmpty()) {

            System.out.println(
                    "No courses registered."
            );

            return;
        }

        for (Course course : registeredCourses) {

            System.out.println(
                    course.getCourseCode()
                            + " - "
                            + course.getTitle()
            );

            System.out.println(
                    "Schedule: "
                            + course.getSchedule()
            );
        }
    }
}